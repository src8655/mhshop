package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.CategoryDao;
import com.cafe24.mhmall.vo.CategoryVo;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	private Tracer tracer;
	public void addTag(String queryId, Object parameter) {
		String query = sqlSession.getConfiguration().getMappedStatement(queryId).getSqlSource().getBoundSql(parameter).getSql();
		tracer.addTag("query", query);
	}

	@Autowired
	SqlSession sqlSession;

	
	// 카테고리 리스트 조회
	@Override
	public List<CategoryVo> getList() {
		String queryId = "category.selectList";
		addTag(queryId, null);
		List<CategoryVo> list =  sqlSession.selectList(queryId);
		return list;
	}


	// 카테고리 추가
	@Override
	public Integer insert(CategoryVo categoryVo) {
		String queryId = "category.insert";
		addTag(queryId, categoryVo);
		Integer result =  sqlSession.insert(queryId, categoryVo);
		return result;
	}


	// 카테고리 수정
	@Override
	public Integer update(CategoryVo categoryVo) {
		String queryId = "category.update";
		addTag(queryId, categoryVo);
		Integer result = sqlSession.update(queryId, categoryVo);
		return result;
	}


	// 카테고리 삭제
	@Override
	public Integer delete(Long no) {
		String queryId = "category.delete";
		addTag(queryId, no);
		Integer result = sqlSession.update(queryId, no);
		return result;
	}


	// 카테고리 번호별 개수
	@Override
	public Integer countByNo(Long categoryNo) {
		String queryId = "category.countbyno";
		addTag(queryId, categoryNo);
		Integer result = (Integer)sqlSession.selectOne(queryId, categoryNo);
		return result;
	}
	
	
	
}
