package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.ItemDao;
import com.cafe24.mhmall.service.ItemService;
import com.cafe24.mhmall.vo.ItemVo;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemDao itemDao;
	
	
	// 카테고리번호에 해당하는 아이템이 있는지?
	@Override
	public boolean hasItemByCategory(Long categoryNo) {
		Integer count = itemDao.countByCategory(categoryNo);
		return count != 0;
	}

	
	// 상품 리스트
	@Override
	public List<ItemVo> getList(ItemVo itemVo) {
		if(itemVo.getCategoryNo() != null && itemVo.getCategoryNo() == -1) itemVo.setCategoryNo(null);
		return itemDao.selectList(itemVo);
	}


	// 상품 등록
	@Override
	public boolean add(ItemVo itemVo) {
		itemVo.setDisplay("FALSE");
		Integer result = itemDao.insert(itemVo);
		return result == 1;
	}


	// 상품 삭제
	@Override
	public boolean delete(Long no) {
		Integer result = itemDao.delete(no);
		return result == 1;
	}


	// 상품번호로 상품정보
	@Override
	public ItemVo getByNo(Long no) {
		return itemDao.selectOne(no);
	}


	// 상품 수정
	@Override
	public boolean edit(ItemVo itemVo) {
		Integer result = itemDao.update(itemVo);
		return result == 1;
	}


	// 상품진열여부 수정
	@Override
	public boolean editDisplay(Long no, String display) {
		ItemVo itemVo = new ItemVo();
		itemVo.setNo(no);
		itemVo.setDisplay(display);
		Integer result = itemDao.updateDisplay(itemVo);
		return result == 1;
	}


	// 사용자 상품리스트
	@Override
	public List<ItemVo> getListU(ItemVo itemVo) {
		return itemDao.selectListU(itemVo);
	}


	// 최근 상품리스트
	@Override
	public List<ItemVo> getNewList(ItemVo vo) {
		return itemDao.selectNewList(vo);
	}

}
