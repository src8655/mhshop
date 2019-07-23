package com.cafe24.mhmall.service;

import java.util.List;

import com.cafe24.mhmall.vo.GuestVo;
import com.cafe24.mhmall.vo.OrdersItemVo;
import com.cafe24.mhmall.vo.OrdersVo;

public interface OrdersService {

	List<OrdersVo> getList();													// 주문리스트
	OrdersVo getByOrdersNo(String ordersNo);									// 주문상세
	boolean changeStatus(String ordersNo, String status);						// 상태 변경
	boolean changeTrackingNum(String ordersNo, String trackingNum);				// 운송장번호 변경
	String guestOrdersAdd(Long money, String memberId);							// 주문 데이터 추가
	boolean isExistAndValid(GuestVo vo);										// 존재하는 주문이고 상태가 "주문대기"인지 확인
	boolean ordersPost(String ordersNo, OrdersVo vo);							// 주문에 받는사람 정보를 변경하고 상태를 "입금대기"로 변경
	boolean isExistAndValidMember(String ordersNo, String id);					// 존재하는 주문이고 상태가 "주문대기"인지 확인(회원)
	boolean isExistAndEnable(GuestVo vo);										// 존재하고 주문대기 상태가 아닌 것이 존재하는지
	List<OrdersVo> getListByMemberId(String id);								// 회원 주문 리스트
	boolean isExistAndEnableMember(OrdersVo vo, String id);						// 존재하고 주문대기 상태가 아닌 것(회원)
	boolean equalsStatus(GuestVo vo, String string);							// 상태가 입금 대기중인지 확인
	
}
