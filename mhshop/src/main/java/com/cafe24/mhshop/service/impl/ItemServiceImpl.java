package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.ItemDao;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.vo.ItemVo;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemDao itemDao;
	
	// 가짜DB
	private List<ItemVo> getItemTable() {
		List<ItemVo> itemTable = new ArrayList<ItemVo>();
		itemTable.add(new ItemVo(1L, "test_item1", "test_description1", 10000L, "test_thumbnail1", "FALSE", 1L, ""));
		itemTable.add(new ItemVo(2L, "test_item2", "test_description2", 20000L, "test_thumbnail2", "FALSE", 1L, ""));
		
		return itemTable;
	}
	

	
	// 카테고리번호에 해당하는 아이템이 있는지?
	@Override
	public boolean hasItemByCategory(Long categoryNo) {
		Integer count = itemDao.countByCategory(categoryNo);
		return count != 0;
	}

	
	// 상품 리스트
	@Override
	public List<ItemVo> getList() {
		return itemDao.selectList();
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


}
