package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.OrdersItemDao;
import com.cafe24.mhshop.service.OrdersItemService;
import com.cafe24.mhshop.vo.OrdersItemVo;

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
