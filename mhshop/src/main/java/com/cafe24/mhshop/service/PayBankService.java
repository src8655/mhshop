package com.cafe24.mhshop.service;

import com.cafe24.mhshop.vo.PayBankVo;

public interface PayBankService {

	PayBankVo getByOrdersNo(String ordersNo);		// 무통장결제상세
	boolean updateDate(String ordersNo);			// 날짜 갱신

}
