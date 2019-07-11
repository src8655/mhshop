package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.mhshop.service.PayBankService;
import com.cafe24.mhshop.vo.PayBankVo;

@Service
public class PayBankServiceImpl implements PayBankService {

	
	// 가짜DB
	private List<PayBankVo> getPayBankTable() {
		List<PayBankVo> payBankTable = new ArrayList<PayBankVo>();
		payBankTable.add(new PayBankVo("2019-07-11_000256", "국민은행", "10000-00-000000", "2019-07-11"));
		
		return payBankTable;
	}
	
	
	
	
	// 무통장결제상세
	@Override
	public PayBankVo getByOrdersNo(String ordersNo) {
		
		// DAO에 요청
		
		
		// 가짜
		List<PayBankVo> payBankTable = getPayBankTable();
		for(PayBankVo vo : payBankTable) if(ordersNo.equals(vo.getOrdersNo())) return vo;
		return null;
	}


	// 날짜 갱신
	@Override
	public boolean updateDate(String ordersNo) {

		// DAO에 요청
		
		// 가짜
		return true;
	}

}
