package com.cafe24.mhmall.repository;

import java.util.List;

import com.cafe24.mhmall.vo.ItemImgVo;

public interface ItemImgDao {

	List<ItemImgVo> selectList(Long itemNo);		// 상품번호에 속한 상품이미지 리스트
	Integer insert(ItemImgVo itemImgVo);			// 상품이미지 추가
	Integer delete(Long no);						// 상품이미지 삭제

}
