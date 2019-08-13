package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;

import com.cafe24.mhmall.frontend.dto.RequestGuestOrdersStartDto;
import com.cafe24.mhmall.frontend.dto.RequestOrdersWriteGuestDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.dto.ResponseOrdersDto;
import com.cafe24.mhmall.frontend.dto.ResponseOrdersViewDto;
import com.cafe24.mhmall.frontend.vo.GuestVo;
import com.cafe24.mhmall.frontend.vo.OrdersVo;

public interface OrdersService {
	
	public static class OrdersVoList extends ArrayList<OrdersVo> {}

	ResponseJSONResult<ResponseOrdersDto> guestOrders(RequestGuestOrdersStartDto dto, String guestSession);		// 비회원 주문 시작
	ResponseJSONResult<OrdersVo> guestOrdersUpdate(RequestOrdersWriteGuestDto dto);								// 비회원 주문 완료
	ResponseJSONResult<ResponseOrdersViewDto> guestOrdersView(String ordersNo, String guestPassword);			// 비회원 주문 상세
	ResponseJSONResult<ResponseOrdersDto> memberOrders(Long[] optionNos, Long[] optionCnts, String mockToken);	// 회원 주문 시작
	ResponseJSONResult<OrdersVo> memberOrdersUpdate(RequestOrdersWriteGuestDto dto, String mockToken);			// 회원 주문 완료
	ResponseJSONResult<OrdersVoList> memberOrdersList(String mockToken);										// 회원 주문 리스트
	ResponseJSONResult<OrdersVo> memberOrdersView(String ordersNo, String mockToken);							// 회원 주문 상세
	ResponseJSONResult<Boolean> memberOrdersCancel(String ordersNo, String mockToken);							// 회원 주문 취소
	ResponseJSONResult<Boolean> guestOrdersCancel(String ordersNo, String guestPassword);						// 비회원 주문 취소
	ResponseJSONResult<OrdersVoList> getAdminList(String mockToken);											// 관리자 주문 리스트 조회
	ResponseJSONResult<OrdersVo> getAdminView(String ordersNo, String mockToken);								// 관리자 주문 상세 조회
	ResponseJSONResult<Boolean> getAdminPaycheck(String ordersNo, String mockToken);							// 관리자 주문 입금확인
	ResponseJSONResult<Boolean> getAdminTnumcheck(String ordersNo, String trackingNum, String mockToken);		// 관리자 운송장번호 등록
	ResponseJSONResult<OrdersVoList> guestFindOrdersNo(String guestName, String guestPhone1, String guestPhone2,// 비회원 주문번호 찾기
			String guestPhone3, String guestPassword);


}
