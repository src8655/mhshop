package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.OrdersItemDao;
import com.cafe24.mhmall.service.OrdersItemService;
import com.cafe24.mhmall.vo.OrdersItemVo;

@Service
public class OrdersItemServiceImpl implements OrdersItemService {

	@Autowired
	OrdersItemDao ordersItemDao;
	
	// 주문번호로 주문상품 리스트
	@Override
	public List<OrdersItemVo> getListByOrdersNo(String ordersNo) {
		return ordersItemDao.selectListByOrdersNo(ordersNo);
	}

}
