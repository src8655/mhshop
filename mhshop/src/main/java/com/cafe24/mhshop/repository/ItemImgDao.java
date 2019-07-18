package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.ItemImgVo;

public interface ItemImgDao {

	List<ItemImgVo> selectList(Long itemNo);		// 상품번호에 속한 상품이미지 리스트
	Integer insert(ItemImgVo itemImgVo);			// 상품이미지 추가

}
