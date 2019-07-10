package com.cafe24.mhshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.ItemDao;
import com.cafe24.mhshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemDao itemDao;

	
	// 카테고리번호에 해당하는 아이템이 있는지?
	@Override
	public boolean hasItemByCategory(Long categoryNo) {
		
		// DAO에 요청
		
		return false;
	}

}
