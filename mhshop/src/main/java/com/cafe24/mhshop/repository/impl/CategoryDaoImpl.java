package com.cafe24.mhshop.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.CategoryDao;
import com.cafe24.mhshop.vo.CategoryVo;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	SqlSession sqlSession;

	
	// 카테고리 리스트 조회
	@Override
	public List<CategoryVo> getList() {
		return sqlSession.selectList("category.selectList");
	}


	// 카테고리 추가
	@Override
	public Integer insert(CategoryVo categoryVo) {
		return sqlSession.insert("category.insert", categoryVo);
	}
	
	
}
