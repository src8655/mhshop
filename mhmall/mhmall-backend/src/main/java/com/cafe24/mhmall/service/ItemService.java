package com.cafe24.mhmall.service;

import java.util.List;

import com.cafe24.mhmall.vo.ItemVo;

public interface ItemService {

	boolean hasItemByCategory(Long categoryNo);		// 카테고리번호에 해당하는 아이템이 있는지?
	List<ItemVo> getList(ItemVo itemVo);			// 상품 리스트
	boolean add(ItemVo itemVo);						// 상품 등록
	boolean delete(Long no);						// 상품 삭제
	ItemVo getByNo(Long no);						// 상품번호로 상품정보
	boolean edit(ItemVo itemVo);					// 상품 수정
	boolean editDisplay(Long no, String display);	// 상품진열여부 수정
	List<ItemVo> getListU(ItemVo itemVo);			// 사용자 상품리스트

}
