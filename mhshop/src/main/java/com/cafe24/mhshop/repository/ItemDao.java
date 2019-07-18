package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.ItemVo;

public interface ItemDao {

	Integer countByCategory(Long categoryNo);	// 카테고리번호에 해당하는 아이템 개수
	List<ItemVo> selectList();					// 상품 리스트
	Integer insert(ItemVo itemVo);				// 상품 등록
	Integer delete(Long no);					// 상품 삭제
	ItemVo selectOne(Long no);					// 상품번호로 상품정보

}
