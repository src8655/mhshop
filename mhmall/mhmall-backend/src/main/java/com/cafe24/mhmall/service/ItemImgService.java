package com.cafe24.mhmall.service;

import java.util.List;

import com.cafe24.mhmall.vo.CategoryVo;
import com.cafe24.mhmall.vo.ItemImgVo;

public interface ItemImgService {

	List<ItemImgVo> getListByItemNo(Long itemNo);	// 상품번호에 속한 상품이미지 리스트
	boolean add(ItemImgVo itemImgVo);				// 상품이미지 추가
	boolean delete(Long no);						// 상품이미지 삭제

}
