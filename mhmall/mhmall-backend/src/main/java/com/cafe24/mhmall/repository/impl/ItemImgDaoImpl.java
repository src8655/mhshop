package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.ItemImgDao;
import com.cafe24.mhmall.vo.ItemImgVo;

@Repository
public class ItemImgDaoImpl implements ItemImgDao {
	
	@Autowired
	private Tracer tracer;
	public void addTag(String queryId, Object parameter) {
		String query = sqlSession.getConfiguration().getMappedStatement(queryId).getSqlSource().getBoundSql(parameter).getSql();
		tracer.addTag("basket.query", query);
	}

	@Autowired
	SqlSession sqlSession;

	
	// 상품번호에 속한 상품이미지 리스트
	@Override
	public List<ItemImgVo> selectList(Long itemNo) {
		addTag("itemimg.selectList", itemNo);
		return sqlSession.selectList("itemimg.selectList", itemNo);
	}


	// 상품이미지 추가
	@Override
	public Integer insert(ItemImgVo itemImgVo) {
		addTag("itemimg.insert", itemImgVo);
		return sqlSession.insert("itemimg.insert", itemImgVo);
	}


	// 상품이미지 삭제
	@Override
	public Integer delete(Long no) {
		addTag("itemimg.delete", no);
		return sqlSession.delete("itemimg.delete", no);
	}


	
	
}
