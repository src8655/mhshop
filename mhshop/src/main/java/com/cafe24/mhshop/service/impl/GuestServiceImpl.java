package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.GuestDao;
import com.cafe24.mhshop.service.GuestService;
import com.cafe24.mhshop.vo.GuestVo;

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

}
