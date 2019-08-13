package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;
import java.util.Optional;

import com.cafe24.mhmall.frontend.dto.RequestJoinDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.MemberVo;

public interface MemberService {
	public static class ListMemberVo extends ArrayList<MemberVo> {}
	
	ResponseJSONResult<MemberVo> login(String id, String password);										// 로그인
	ResponseJSONResult<Boolean> add(RequestJoinDto dto);												// 회원가입
	ResponseJSONResult<Boolean> idcheck(String id);														// 아이디 중복확인
	ResponseJSONResult<MemberVo> get(String id);														// 아이디로 회원정보(로그인)
	ResponseJSONResult<ListMemberVo> getMemberList(String authorization, Optional<String> search);		// 관리자 회원목록
	ResponseJSONResult<Boolean> delete(String mockToken, String id);										// 회원 삭제요청
}
