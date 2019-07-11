package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.mhshop.service.PayKakaoService;
import com.cafe24.mhshop.vo.PayKakaoVo;

@Service
public class PayKakaoServiceImpl implements PayKakaoService {

	
	// 가짜DB
	private List<PayKakaoVo> getPayKakaoTable() {
		List<PayKakaoVo> payKakaoTable = new ArrayList<PayKakaoVo>();
		payKakaoTable.add(new PayKakaoVo("2019-07-11_000257", "DI201022333", "AP32292938", "2019-07-11"));
		payKakaoTable.add(new PayKakaoVo("2019-07-11_000258", "DI202231226", "AP98755653", "2019-07-11"));
		
		return payKakaoTable;
	}
	
	
	// 카카오결제 상세
	@Override
	public PayKakaoVo getByOrdersNo(String ordersNo) {
		
		// DAO에 요청
		
		
		// 가짜
		List<PayKakaoVo> payKakaoTable = getPayKakaoTable();
		for(PayKakaoVo vo : payKakaoTable) if(ordersNo.equals(vo.getOrdersNo())) return vo;
		return null;
	}

}
