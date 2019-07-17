package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.ItemVo;

public interface ItemDao {

	Integer countByCategory(Long categoryNo);	// 카테고리번호에 해당하는 아이템 개수
	List<ItemVo> selectList();					// 상품 리스트

}
