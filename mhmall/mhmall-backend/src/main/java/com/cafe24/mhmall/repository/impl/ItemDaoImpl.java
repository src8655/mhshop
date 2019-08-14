package com.cafe24.mhmall.repository.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.ItemDao;
import com.cafe24.mhmall.vo.ItemVo;
import com.cafe24.mhmall.vo.MainImgVo;

@Repository
public class ItemDaoImpl implements ItemDao {
	
	@Autowired
	private Tracer tracer;
	public void addTag(String queryId, Object parameter) {
		String query = sqlSession.getConfiguration().getMappedStatement(queryId).getSqlSource().getBoundSql(parameter).getSql();
		tracer.addTag("basket.query", query);
	}
	
	@Autowired
	SqlSession sqlSession;

	
	// 카테고리번호에 해당하는 아이템 개수
	@Override
	public Integer countByCategory(Long categoryNo) {
		addTag("item.countByCategory", categoryNo);
		return (Integer)sqlSession.selectOne("item.countByCategory", categoryNo);
	}

	
	// 상품 리스트
	@Override
	public List<ItemVo> selectList(ItemVo itemVo) {
		addTag("item.selectList", itemVo);
		return sqlSession.selectList("item.selectList", itemVo);
	}


	// 상품 등록
	@Override
	public Integer insert(ItemVo itemVo) {
		addTag("item.insert", itemVo);
		return sqlSession.insert("item.insert", itemVo);
	}


	// 상품 삭제
	@Override
	public Integer delete(Long no) {
		addTag("item.delete", no);
		return sqlSession.delete("item.delete", no);
	}


	// 상품번호로 상품정보
	@Override
	public ItemVo selectOne(Long no) {
		addTag("item.selectByNo", no);
		return (ItemVo)sqlSession.selectOne("item.selectByNo", no);
	}


	// 상품 수정
	@Override
	public Integer update(ItemVo itemVo) {
		addTag("item.update", itemVo);
		return sqlSession.update("item.update", itemVo);
	}


	// 상품진열여부 수정
	@Override
	public Integer updateDisplay(ItemVo itemVo) {
		addTag("item.updateDisplay", itemVo);
		return sqlSession.update("item.updateDisplay", itemVo);
	}


	// 사용자 상품리스트
	@Override
	public List<ItemVo> selectListU(Map<String, Object> daoMap) {
		addTag("item.selectListU", daoMap);
		return sqlSession.selectList("item.selectListU", daoMap);
	}


	// 최근 상품리스트
	@Override
	public List<ItemVo> selectNewList(ItemVo vo) {
		addTag("item.selectNewList", vo);
		return sqlSession.selectList("item.selectNewList", vo);
	}


	// 최근 메인 이미지 리스트 요청
	@Override
	public List<MainImgVo> getNewItemList(Integer showCnt) {
		addTag("item.getNewItemList", showCnt);
		return sqlSession.selectList("item.getNewItemList", showCnt);
	}


	// 회원 총 상품 개수
	@Override
	public Integer countU(Map<String, Object> mapCnt) {
		addTag("item.selectCountU", mapCnt);
		return sqlSession.selectOne("item.selectCountU", mapCnt);
	}
	
	
	

}
