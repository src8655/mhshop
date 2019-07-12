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


	
	
	// 아이디 중복확인
	@Override
	public boolean idCheck(String id) {
		
		int cnt = memberDao.countById(id);
		if(cnt == 0) return false;
		else return true;
	}


	// 회원등록
	@Override
	public Boolean add(MemberVo memberVo) {

		memberVo.setRole("USER");
		
		// 중복확인
		int cnt = memberDao.countById(memberVo.getId());
		if(cnt != 0) return false;
		
		int result = memberDao.insert(memberVo);
		return result == 1;
	}


	// 로그인
	@Override
	public MemberVo login(String id, String password) {
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPassword(password);
		MemberVo memberVo = memberDao.selectByIdAndPassword(vo);
		return memberVo;
	}


	// 회원 리스트
	@Override
	public List<MemberVo> getList() {
		
		return memberDao.selectList();
	}


	// 아이디로 회원조회
	@Override
	public MemberVo getById(String id) {
		
		return memberDao.selectOneById(id);
	}


	// 회원삭제
	@Override
	public boolean delete(String id) {

		int result = memberDao.delete(id);
		return result == 1;
	}

	
}
