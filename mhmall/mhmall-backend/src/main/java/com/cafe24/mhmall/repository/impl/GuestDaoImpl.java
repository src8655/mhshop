package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.GuestDao;
import com.cafe24.mhmall.vo.GuestVo;
import com.cafe24.mhmall.vo.OrdersVo;

@Repository
public class GuestDaoImpl implements GuestDao {

	@Autowired
	SqlSession sqlSession;

	// AES키 관리
	private final String aesKey = "mhshop_key";
	
	
	// 비회원 상세
	@Override
	public GuestVo selectOne(GuestVo guestVo) {
		guestVo.setAesKey(aesKey);
		return (GuestVo)sqlSession.selectOne("guest.selectOne", guestVo);
	}


	// 비회원 데이터 추가
	@Override
	public Integer insert(GuestVo vo) {
		vo.setAesKey(aesKey);
		return sqlSession.insert("guest.insert", vo);
	}


	// 비회원의 주문리스트 찾기(주문번호, 주문일, 상태)
	@Override
	public List<OrdersVo> findOrdersNo(GuestVo vo) {
		vo.setAesKey(aesKey);
		return sqlSession.selectList("guest.findOrdersNo", vo);
	}


	// 조건에 맞는 주문번호가 존재하는지?
	@Override
	public Integer findPw(GuestVo vo) {
		vo.setAesKey(aesKey);
		return (Integer)sqlSession.selectOne("guest.findPw", vo);
	}


	// 비회원 주문 비밀번호 변경
	@Override
	public Integer changePw(GuestVo vo) {
		vo.setAesKey(aesKey);
		return sqlSession.update("guest.updatePw", vo);
	}
	
	
	
}
