package com.cafe24.mhshop.repository.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.ItemDao;

@Repository
public class ItemDaoImpl implements ItemDao {

	@Autowired
	SqlSession session;
	
}
