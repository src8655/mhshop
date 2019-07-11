package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.MemberDao;
import com.cafe24.mhshop.service.MemberService;
import com.cafe24.mhshop.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;

	// 가짜DB
	private List<MemberVo> getMemberTable() {
		List<MemberVo> memberTable = new ArrayList<MemberVo>();
		memberTable.add(new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER"));
		memberTable.add(new MemberVo("test_id2", "testpassword2!", "test2", "01000000002", "test_email2@naver.com", "test_zipcode2", "test_addr2", "2019-07-11", "ADMIN"));
		
		return memberTable;
	}
	
	
	// 아이디 중복확인
	@Override
	public boolean idCheck(String id) {

		// 가짜DB
		List<MemberVo> memberTable = getMemberTable();
		
		
		// DAO에 요청
		
		
		//가짜
		int cnt = 0;
		for(MemberVo vo : memberTable) if(vo.getId().equals(id)) cnt++;
		if(cnt == 0) return false;
		else return true;
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
		
		// 가짜DB
		List<MemberVo> memberTable = getMemberTable();
		
		
		
		// DAO에 요청
		

		// 가짜
		MemberVo memberVo = null;
		for(MemberVo vo : memberTable) {
			if(vo.getId().equals(id) && vo.getPassword().equals(password)) memberVo = vo;
		}
		
		
		return memberVo;
	}
	
	
}
