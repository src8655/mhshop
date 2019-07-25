package com.cafe24.mhmall.repository;

import java.util.List;

import com.cafe24.mhmall.vo.ItemVo;

public interface ItemDao {

	Integer countByCategory(Long categoryNo);	// 카테고리번호에 해당하는 아이템 개수
	List<ItemVo> selectList(ItemVo itemVo);		// 상품 리스트
	Integer insert(ItemVo itemVo);				// 상품 등록
	Integer delete(Long no);					// 상품 삭제
	ItemVo selectOne(Long no);					// 상품번호로 상품정보
	Integer update(ItemVo itemVo);				// 상품 수정
	Integer updateDisplay(ItemVo itemVo);		// 상품진열여부 수정
	List<ItemVo> selectListU(ItemVo itemVo);	// 사용자 상품리스트
	List<ItemVo> selectNewList(ItemVo vo);		// 최근 상품리스트

}
