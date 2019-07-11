package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.vo.CategoryVo;
import com.cafe24.mhshop.vo.ItemImgVo;

@Service
public class ItemImgServiceImpl implements ItemImgService {

	@Autowired
	ItemImgService itemImgService;
	
	
	// 가짜DB
	private List<ItemImgVo> getItemImgTable() {
		List<ItemImgVo> itemImgTable = new ArrayList<ItemImgVo>();
		itemImgTable.add(new ItemImgVo(1L, 1L, "test_img1"));
		itemImgTable.add(new ItemImgVo(2L, 1L, "test_img2"));
		
		return itemImgTable;
	}
	

	// 상품번호에 속한 상품이미지 모두 삭제
	@Override
	public boolean deleteAllByItemNo(Long itemNo) {
		
		// DAO에 요청
		
		return true;
	}


	// 상품번호에 속한 상품이미지 리스트
	@Override
	public List<ItemImgVo> getListByItemNo(Long itemNo) {
		
		// DAO에 요청
		
		
		// 가짜
		List<ItemImgVo> itemImgTable = getItemImgTable();
		List<ItemImgVo> itemImgList = new ArrayList<ItemImgVo>();
		for(ItemImgVo vo : itemImgTable) if(vo.getItemNo() == itemNo) itemImgList.add(vo);
		if(itemImgList.size() == 0) return null;
		return itemImgList;
	}


	// 상품이미지 추가
	@Override
	public boolean add(Long itemNo, String itemImg) {

		// DAO에 요청
		
		return true;
	}


	// 상품이미지 삭제
	@Override
	public boolean delete(Long no) {

		// DAO에 요청
		
		return true;
	}
	
}
