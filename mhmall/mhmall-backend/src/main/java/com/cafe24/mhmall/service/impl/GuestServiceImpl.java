package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.GuestDao;
import com.cafe24.mhmall.service.GuestService;
import com.cafe24.mhmall.vo.GuestVo;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	GuestDao guestDao;
	
	
	
	// 비회원 상세
	@Override
	public GuestVo getByOrdersNo(String ordersNo) {
		GuestVo guestVo = new GuestVo();
		guestVo.setOrdersNo(ordersNo);
		
		return guestDao.selectOne(guestVo);
	}


	// 비회원 데이터 추가
	@Override
	public boolean add(String ordersNo, GuestVo vo) {
		vo.setOrdersNo(ordersNo);
		Integer result = guestDao.insert(vo);
		return result == 1;
	}

	
}
