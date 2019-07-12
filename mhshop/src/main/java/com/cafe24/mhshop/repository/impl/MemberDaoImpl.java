package com.cafe24.mhshop.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.MemberDao;
import com.cafe24.mhshop.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	SqlSession session;

	// AES키 관리
	private final String aesKey = "mhshop_key";
	

	// 아이디 중복확인
	@Override
	public Integer countById(String id) {
		
		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setAesKey(aesKey);
		return (Integer)session.selectOne("member.countById", memberVo);
	}


	// 입력
	@Override
	public Integer insert(MemberVo memberVo) {
		memberVo.setAesKey(aesKey);
		return (Integer)session.insert("member.inserts", memberVo);
	}


	// 로그인
	@Override
	public MemberVo selectByIdAndPassword(MemberVo memberVo) {

		memberVo.setAesKey(aesKey);
		return (MemberVo)session.selectOne("member.selectbyidandpassword", memberVo);
	}


	// 회원 리스트
	@Override
	public List<MemberVo> selectList() {

		Map map = new HashMap();
		map.put("aesKey", aesKey);
		return session.selectList("member.selectlist", map);
	}


	// 아이디로 회원조회
	@Override
	public MemberVo selectOneById(String id) {

		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setAesKey(aesKey);
		return (MemberVo)session.selectOne("member.selectonebyid", memberVo);
	}


	// 회원삭제
	@Override
	public Integer delete(String id) {

		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setAesKey(aesKey);
		return (Integer)session.delete("member.delete", memberVo);
	}


	// 회원번호로 찾기
	@Override
	public MemberVo selectOneByNo(Long no) {
		
		return (MemberVo)session.selectOne("member.selectonebyno", no);
	}
	
	

}
