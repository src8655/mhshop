package com.cafe24.mhmall.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.MemberDao;
import com.cafe24.mhmall.vo.MemberVo;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private Tracer tracer;
	public void addTag(String queryId, Object parameter) {
		String query = sqlSession.getConfiguration().getMappedStatement(queryId).getSqlSource().getBoundSql(parameter).getSql();
		tracer.addTag("basket.query", query);
	}
	
	@Autowired
	SqlSession sqlSession;

	// AES키 관리
	private final String aesKey = "mhshop_key";
	

	// 아이디 중복확인
	@Override
	public Integer countById(String id) {
		
		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setAesKey(aesKey);
		addTag("member.countById", memberVo);
		return (Integer)sqlSession.selectOne("member.countById", memberVo);
	}


	// 입력
	@Override
	public Integer insert(MemberVo memberVo) {
		memberVo.setAesKey(aesKey);
		addTag("member.inserts", memberVo);
		return (Integer)sqlSession.insert("member.inserts", memberVo);
	}


	// 로그인
	@Override
	public MemberVo selectByIdAndPassword(MemberVo memberVo) {

		memberVo.setAesKey(aesKey);
		addTag("member.selectbyidandpassword", memberVo);
		return (MemberVo)sqlSession.selectOne("member.selectbyidandpassword", memberVo);
	}


	// 회원 리스트
	@Override
	public List<MemberVo> selectList(String search) {
		//System.out.println(search);
		Map map = new HashMap();
		map.put("aesKey", aesKey);
		map.put("search", search);
		addTag("member.selectlist", map);
		return sqlSession.selectList("member.selectlist", map);
	}


	// 아이디로 회원조회
	@Override
	public MemberVo selectOneById(MemberVo memberVo) {
		memberVo.setAesKey(aesKey);
		addTag("member.selectonebyid", memberVo);
		return (MemberVo)sqlSession.selectOne("member.selectonebyid", memberVo);
	}


	// 회원삭제
	@Override
	public Integer delete(String id) {

		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);
		memberVo.setAesKey(aesKey);
		addTag("member.delete", memberVo);
		return (Integer)sqlSession.delete("member.delete", memberVo);
	}


	// 회원번호로 찾기
	@Override
	public MemberVo selectOneByNo(Long no) {

		addTag("member.selectonebyno", no);
		return (MemberVo)sqlSession.selectOne("member.selectonebyno", no);
	}


	// 회원수정
	@Override
	public Integer update(MemberVo memberVo) {
		
		memberVo.setAesKey(aesKey);
		addTag("member.update", memberVo);
		return (Integer)sqlSession.delete("member.update", memberVo);
	}


	// 인증
	@Override
	public MemberVo selectByMockToken(String mockToken) {
		MemberVo memberVo = new MemberVo();
		memberVo.setMockToken(mockToken);
		memberVo.setAesKey(aesKey);
		addTag("member.selectbymocktoken", memberVo);
		return (MemberVo)sqlSession.selectOne("member.selectbymocktoken", memberVo);
	}
	
	

}
