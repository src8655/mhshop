package com.cafe24.mhshop.service;

import java.util.List;

import com.cafe24.mhshop.vo.CategoryVo;
import com.cafe24.mhshop.vo.ItemImgVo;

public interface ItemImgService {

	List<ItemImgVo> getListByItemNo(Long itemNo);	// 상품번호에 속한 상품이미지 리스트
	boolean add(ItemImgVo itemImgVo);				// 상품이미지 추가
	boolean delete(Long no);						// 상품이미지 삭제

}
