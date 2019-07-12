package com.cafe24.mhshop.service;

import java.util.List;

import com.cafe24.mhshop.vo.MemberVo;

public interface MemberService {

	boolean idCheck(String id);					// 아이디 중복확인
	Boolean add(MemberVo memberVo);				// 회원등록
	MemberVo login(String id, String password);	// 로그인
	List<MemberVo> getList();					// 회원 리스트
	MemberVo getById(String id);				// 아이디로 회원조회
	boolean delete(String id);					// 회원삭제
	boolean edit(MemberVo memberVo);			// 회원수정

}
