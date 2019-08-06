package com.cafe24.mhmall.security;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mhmall.service.MemberService;
import com.cafe24.mhmall.service.OptionService;
import com.cafe24.mhmall.vo.MemberVo;


public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
		//1. handler종류 확인 HanlderMethod, DefaultHandlerServlet
		if(handler instanceof HandlerMethod == false) {		// images, css, js
			return true;
		}
		
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		//5. @Auth가 안 붇는 경우
		if(auth == null) {
			return true;
		}
		
		
		/*
		
		
		
		//6. @Auth가 붙는 경우
		//   인증 여부 체크
		// 헤더정보
		String authorization = request.getHeader("Authorization");
		
		// 인증받을 정보가 없으면 실패
		if(authorization == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setHeader("WWW-Authenticate", "Basic realm=\"id:password\"");
			return false;
		}

		// 잘못된 인증 정보일 때
		String[] basic_split = authorization.split(" ");
		if(basic_split.length != 2) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setHeader("WWW-Authenticate", "Basic realm=\"id:password\"");
			return false;
		}
		String auth_decode = new String(Base64.getDecoder().decode(basic_split[1]));
		String[] auth_split = auth_decode.split(":");
		if(auth_split.length != 2) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setHeader("WWW-Authenticate", "Basic realm=\"id:password\"");
			return false;
		}
		
		// 헤더로 입력받은 id와 비밀번호
		MemberVo memberVo = new MemberVo();
		memberVo.setId(auth_split[0]);
		memberVo.setPassword(auth_split[1]);
		
		// 회원정보가 없으면 실패
		MemberVo authMember = memberService.login(memberVo);
		if(authMember == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setHeader("WWW-Authenticate", "Basic realm=\"id:password\"");
			return false;
		}
		
		
		//7. Role 가져오기
		Auth.Role role = auth.role();
		
		//8. role이 Auth.Role.USER라면
		//   인증된 모든 사용자는 접근
		if(role == Auth.Role.ROLE_USER) {
			return true;
		}
		
		//9. Admin Role 권한 체크
		if(role == Auth.Role.ROLE_ADMIN) {
			if(authMember.getRole().equals(Auth.Role.ROLE_ADMIN.toString())) {
				return true;
			}else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
		}
		
		*/
		return true;
	}

}
