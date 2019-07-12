package com.cafe24.mhshop.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.MemberDao;
import com.cafe24.mhshop.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SqlSession session;
	
	
	
	// 가짜DB
	private List<MemberVo> getMemberTable() {
		List<MemberVo> memberTable = new ArrayList<MemberVo>();
		memberTable.add(new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key"));
		memberTable.add(new MemberVo("test_id2", "testpassword2!", "test2", "01000000002", "test_email2@naver.com", "test_zipcode2", "test_addr2", "2019-07-11", "ADMIN", "mhshop_key"));
		
		return memberTable;
	}


	// 아이디 중복확인
	@Override
	public int countById(String id) {
		
		// 가짜DB
		List<MemberVo> memberTable = getMemberTable();
		
		//가짜
		int cnt = 0;
		for(MemberVo vo : memberTable) if(vo.getId().equals(id)) cnt++;
		return cnt;
	}


	// 입력
	@Override
	public int insert(MemberVo memberVo) {
		
		return 1;
	}


	// 로그인
	@Override
	public MemberVo selectByIdAndPassword(String id, String password) {

		// 가짜DB
		List<MemberVo> memberTable = getMemberTable();
		

		// 가짜
		MemberVo memberVo = null;
		for(MemberVo vo : memberTable)
			if(vo.getId().equals(id) && vo.getPassword().equals(password)) memberVo = vo;
		
		
		return memberVo;
	}


	// 회원 리스트
	@Override
	public List<MemberVo> selectList() {
		
		return getMemberTable();
	}


	// 아이디로 회원조회
	@Override
	public MemberVo selectOneById(String id) {
		// 가짜DB
		List<MemberVo> memberTable = getMemberTable();
		
		// 가짜
		for(MemberVo vo : memberTable) if(vo.getId().equals(id)) return vo;
		return null;
	}


	// 회원삭제
	@Override
	public int delete(String id) {
		// 가짜DB
		List<MemberVo> memberTable = getMemberTable();
		
		// 가짜(없으면 0 있으면 1) 
		for(MemberVo vo : memberTable) if(vo.getId().equals(id)) return 1;
		return 0;
	}
	
	

}
