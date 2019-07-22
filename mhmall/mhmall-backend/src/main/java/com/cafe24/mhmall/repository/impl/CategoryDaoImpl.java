package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.CategoryDao;
import com.cafe24.mhmall.vo.CategoryVo;

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


	// 카테고리 수정
	@Override
	public Integer update(CategoryVo categoryVo) {
		return sqlSession.update("category.update", categoryVo);
	}


	// 카테고리 삭제
	@Override
	public Integer delete(Long no) {
		return sqlSession.update("category.delete", no);
	}


	// 카테고리 번호별 개수
	@Override
	public Integer countByNo(Long categoryNo) {
		return (Integer)sqlSession.selectOne("category.countbyno", categoryNo);
	}
	
	
}
