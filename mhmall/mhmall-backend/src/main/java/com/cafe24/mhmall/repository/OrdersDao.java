package com.cafe24.mhmall.repository;

import java.util.List;
import java.util.Map;

import com.cafe24.mhmall.vo.GuestVo;
import com.cafe24.mhmall.vo.OrdersVo;

public interface OrdersDao {

	List<OrdersVo> selectList();						// 주문리스트
	OrdersVo selectOne(OrdersVo ordersVo);				// 주문상세
	Integer updateStatus(Map<String, String> map);		// 상태 변경
	Integer updateTrackingNum(Map<String, String> map);	// 운송장번호 변경
	String insert(OrdersVo ordersVo);					// 주문작성
	Integer isExistAndValid(GuestVo vo);				// 존재하는 주문이고 상태가 "주문대기"인지 확인

}
