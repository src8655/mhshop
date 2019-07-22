package com.cafe24.mhmall.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.mhmall.service.MemberService;
import com.cafe24.mhmall.vo.MemberVo;


public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	MemberService memberService;

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		if(supportsParameter(parameter) == false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		// @AuthUser가 붙어있음
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		
		
		String mockToken = request.getParameter("mockToken");
		
		// 인증받을 정보가 없으면 실패
		if(mockToken == null) {
			return null;
		}
		
		// 회원정보
		MemberVo authMember = memberService.getByMockToken(mockToken);
		return authMember;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser 가 안붙어 있음
		if(authUser == null) {
			return false;
		}
		
		// 파라미터 타입이 UserVo가 아니면
		if(!parameter.getParameterType().equals(MemberVo.class)) {
			return false;
		}
		
		return true;
	}
}
