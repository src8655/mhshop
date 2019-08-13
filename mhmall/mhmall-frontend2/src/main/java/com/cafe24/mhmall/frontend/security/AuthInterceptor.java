package com.cafe24.mhmall.frontend.security;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mhmall.frontend.vo.MemberVo;


public class AuthInterceptor extends HandlerInterceptorAdapter {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//handler종류 확인 HanlderMethod, DefaultHandlerServlet
		if(handler instanceof HandlerMethod == false) {		// images, css, js
			return true;
		}
		
		//casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//@Auth가 안 붇는 경우
		if(auth == null) {
			return true;
		}
		
		//@Auth가 (class 또는 method에) 붙어있기 때문에
		//인증 여부 체크
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false;
		}
		MemberVo authUser = (MemberVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false;
		}
		
		request.setAttribute("authUser", authUser);
		
		//Role 가져오기
		Auth.Role role = auth.role();
		
		//role이 Auth.Role.USER라면
		//   인증된 모든 사용자는 접근
		if(role == Auth.Role.ROLE_USER) {
			return true;
		}
		
		//Admin Role 권한 체크
		if(role == Auth.Role.ROLE_ADMIN) {
			if(authUser.getRole().equals(Auth.Role.ROLE_ADMIN.toString())) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath()+"/");
				return false;
			}
		}
		
		
		return true;
	}


}
