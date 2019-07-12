package com.cafe24.mhshop.repository.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class CategoryDao implements com.cafe24.mhshop.repository.CategoryDao {
	
	@Autowired
	SqlSession sqlSession;

}
