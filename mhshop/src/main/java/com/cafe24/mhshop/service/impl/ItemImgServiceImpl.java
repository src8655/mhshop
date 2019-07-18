package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.ItemImgDao;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.vo.CategoryVo;
import com.cafe24.mhshop.vo.ItemImgVo;

@Service
public class ItemImgServiceImpl implements ItemImgService {

	@Autowired
	ItemImgDao itemImgDao;
	

	// 상품번호에 속한 상품이미지 리스트
	@Override
	public List<ItemImgVo> getListByItemNo(Long itemNo) {
		return itemImgDao.selectList(itemNo);
	}


	// 상품이미지 추가
	@Override
	public boolean add(ItemImgVo itemImgVo) {

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
