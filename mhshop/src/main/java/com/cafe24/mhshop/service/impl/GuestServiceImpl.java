package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.mhshop.service.GuestService;
import com.cafe24.mhshop.vo.GuestVo;

@Service
public class GuestServiceImpl implements GuestService {

	
	
	// 가짜DB
	private List<GuestVo> getGuestTable() {
		List<GuestVo> guestTable = new ArrayList<GuestVo>();
		guestTable.add(new GuestVo("2019-07-11_000257", "test_guest1", "01000000001", "guestpw1!"));
		guestTable.add(new GuestVo("2019-07-11_000258", "test_guest2", "01000000002", "guestpw2!"));
		
		return guestTable;
	}
	
	
	
	// 비회원 상세
	@Override
	public GuestVo getByOrdersNo(String ordersNo) {
		
		// DAO에 요청
		
		
		// 가짜
		List<GuestVo> guestTable = getGuestTable();
		for(GuestVo vo : guestTable) if(ordersNo.equals(vo.getOrdersNo())) return vo;
		return null;
	}

}
