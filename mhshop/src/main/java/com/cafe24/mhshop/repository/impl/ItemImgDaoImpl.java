package com.cafe24.mhshop.repository.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.ItemImgDao;

@Repository
public class ItemImgDaoImpl implements ItemImgDao {
	
	@Autowired
	SqlSession sqlSession;

}
