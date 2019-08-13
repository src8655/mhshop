package com.cafe24.mhmall.frontend.security;

import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mhmall.frontend.vo.MemberVo;


public class GuestInterceptor extends HandlerInterceptorAdapter {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//handler종류 확인 HanlderMethod, DefaultHandlerServlet
		if(handler instanceof HandlerMethod == false) {		// images, css, js
			return true;
		}
		
		// 쿠키이름
		String cookieName = "GuestSession";


		// 사용자 쿠키에 비회원세션이 있는지 확인
		Cookie[] cookies = request.getCookies();
		boolean hasCookie = false;
		for(Cookie cookie : cookies) {
			if(cookieName.equals(cookie.getName()))
				hasCookie = true;
		}
		
		
		// 비회원세션이 없으면
		if(!hasCookie) {
			System.out.println("GuestSession 추가!!");
			// 세션ID를 받아서
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			
			// 세션을 쿠키에 추가
			Cookie cookie = new Cookie(cookieName, sessionId);
			cookie.setMaxAge(60*60*24*30);	// 30일로 설정
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		
		return true;
	}


}
