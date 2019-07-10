package com.cafe24.mhshop.service;

import com.cafe24.mhshop.vo.MemberVo;

public interface MemberService {

	boolean idCheck(String id);					// 아이디 중복확인
	Boolean add(MemberVo memberVo);			// 회원등록
	MemberVo login(String id, String password);	// 로그인

}
