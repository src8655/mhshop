package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.ItemDao;
import com.cafe24.mhmall.vo.ItemVo;

@Repository
public class ItemDaoImpl implements ItemDao {
	
	@Autowired
	SqlSession sqlSession;

	
	// 카테고리번호에 해당하는 아이템 개수
	@Override
	public Integer countByCategory(Long categoryNo) {
		return (Integer)sqlSession.selectOne("item.countByCategory", categoryNo);
	}

	
	// 상품 리스트
	@Override
	public List<ItemVo> selectList(ItemVo itemVo) {
		return sqlSession.selectList("item.selectList", itemVo);
	}


	// 상품 등록
	@Override
	public Integer insert(ItemVo itemVo) {
		return sqlSession.insert("item.insert", itemVo);
	}


	// 상품 삭제
	@Override
	public Integer delete(Long no) {
		return sqlSession.delete("item.delete", no);
	}


	// 상품번호로 상품정보
	@Override
	public ItemVo selectOne(Long no) {
		return (ItemVo)sqlSession.selectOne("item.selectByNo", no);
	}


	// 상품 수정
	@Override
	public Integer update(ItemVo itemVo) {
		return sqlSession.update("item.update", itemVo);
	}


	// 상품진열여부 수정
	@Override
	public Integer updateDisplay(ItemVo itemVo) {
		return sqlSession.update("item.updateDisplay", itemVo);
	}


	// 사용자 상품리스트
	@Override
	public List<ItemVo> selectListU(ItemVo itemVo) {
		return sqlSession.selectList("item.selectListU", itemVo);
	}


	// 최근 상품리스트
	@Override
	public List<ItemVo> selectNewList(ItemVo vo) {
		return sqlSession.selectList("item.selectNewList", vo);
	}

}
