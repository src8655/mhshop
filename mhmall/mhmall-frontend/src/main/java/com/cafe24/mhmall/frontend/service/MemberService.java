package com.cafe24.mhmall.frontend.service;

import com.cafe24.mhmall.frontend.dto.RequestJoinDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.MemberVo;

public interface MemberService {
	
	ResponseJSONResult<MemberVo> login(String id, String password);	// 로그인
	ResponseJSONResult<Boolean> add(RequestJoinDto dto);			// 회원가입
	ResponseJSONResult<Boolean> idcheck(String id);					// 아이디 중복확인

}
