package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.ItemImgDao;
import com.cafe24.mhmall.service.ItemImgService;
import com.cafe24.mhmall.vo.CategoryVo;
import com.cafe24.mhmall.vo.ItemImgVo;

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
		Integer result = itemImgDao.insert(itemImgVo);
		return result == 1;
	}


	// 상품이미지 삭제
	@Override
	public boolean delete(Long no) {
		Integer result = itemImgDao.delete(no);
		return result == 1;
	}
	
}
