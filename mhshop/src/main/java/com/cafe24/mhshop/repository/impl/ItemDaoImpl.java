package com.cafe24.mhshop.repository.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.ItemDao;

@Repository
public class ItemDaoImpl implements ItemDao {
	
	@Autowired
	SqlSession sqlSession;

	
	// 카테고리번호에 해당하는 아이템 개수
	@Override
	public Integer countByCategory(Long categoryNo) {
		return (Integer)sqlSession.selectOne("item.countByCategory", categoryNo);
	}

}
