package com.cafe24.mhshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.MemberDao;
import com.cafe24.mhshop.service.MemberService;
import com.cafe24.mhshop.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	
	// 아이디 중복확인
	@Override
	public boolean idCheck(String id) {
		
		
		// DAO에 요청
		
		return false;
	}


	// 회원등록
	@Override
	public Boolean add(MemberVo memberVo) {
		
		memberVo.setRole("USER");
		
		
		// DAO에 요청
		
		
		return true;
	}


	// 로그인
	@Override
	public MemberVo login(String id, String password) {
		
		// DAO에 요청
		
		
		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setPassword(password);
		memberVo.setName("test");
		memberVo.setPhone("01000000000");
		memberVo.setEmail("test_email@naver.com");
		memberVo.setZipcode("test_zipcode");
		memberVo.setAddr("test_addr");
		memberVo.setRole("USER");
		
		return memberVo;
	}
	
	
}