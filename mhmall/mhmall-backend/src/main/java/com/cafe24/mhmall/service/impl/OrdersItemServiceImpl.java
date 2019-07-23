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

	
	// 주문내역 일괄 추가
	@Override
	public boolean add(String ordersNo, Long[] optionNos, Integer[] optionCnts) {
		for(int i=0;i<optionNos.length;i++) {
			OrdersItemVo ordersItemVo = new OrdersItemVo();
			ordersItemVo.setOrdersNo(ordersNo);
			ordersItemVo.setOptionNo(optionNos[i]);
			ordersItemVo.setCnt(optionCnts[i].longValue());
			
			Integer result = ordersItemDao.insert(ordersItemVo);
			if(result != 1) return false;
		}
		
		return true;
	}
	

	// 초과된 주문의 번호에 해당하는 옵션번호와 수량 리스트를 받음
	@Override
	public List<OrdersItemVo> getTimeOverList(Long ordersTime) {
		return ordersItemDao.getTimeOverList(ordersTime);
	}


}
