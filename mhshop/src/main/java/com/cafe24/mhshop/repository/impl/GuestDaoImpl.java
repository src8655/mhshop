package com.cafe24.mhshop.repository.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.GuestDao;
import com.cafe24.mhshop.vo.GuestVo;

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
	
	
	
}
