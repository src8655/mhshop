package com.cafe24.mhmall.security;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mhmall.service.BasketService;
import com.cafe24.mhmall.service.MemberService;
import com.cafe24.mhmall.service.OptionService;
import com.cafe24.mhmall.service.OrdersItemService;
import com.cafe24.mhmall.service.OrdersService;
import com.cafe24.mhmall.vo.MemberVo;
import com.cafe24.mhmall.vo.OrdersItemVo;


public class BasicInterceptor extends HandlerInterceptorAdapter {
	private static String updateDate = "2000-01-01";
	private final static Long ORDERS_TIME = 2592000L;	// 1개월
	private final static Long BASKET_TIME = 2592000L;	// 1개월
	
	@Autowired
	OptionService optionService;
	
	@Autowired
	OrdersService ordersService;

	@Autowired
	OrdersItemService ordersItemService;
	
	@Autowired
	BasketService basketService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 현재날짜를 구해서
		Calendar cal = Calendar.getInstance();
		String nowDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE);
		
		// 날짜가 바뀌면 실행
		if(!updateDate.equals(nowDate)) {
			System.out.println("날짜변경!" + updateDate + " => " + nowDate);
			updateDate = nowDate;
		
			// 시간이 초과된 주문대기 상태의 주문들 주문취소 처리
			timeOverOrders();
			// 시간이 초과된 비회원 장바구니들은 삭제
			timeOverBasket();
		}
		
		
		return true;
	}


	// 시간이 초과된 주문대기 상태의 주문들 주문취소 처리
	@Transactional(rollbackFor=Exception.class)
	public void timeOverOrders() {
		// 시간이 초과된 주문대기 상태의 주문이 있는지 확인
		if(ordersService.isExistTimeOverOrders(ORDERS_TIME)) {
			// 초과된 주문의 번호에 해당하는 옵션번호와 수량 리스트를 받음
			List<OrdersItemVo> ordersItemList = ordersItemService.getTimeOverList(ORDERS_TIME);

			// 옵션의 상품수량 복구
			optionService.restoreCnt(ordersItemList);
			
			// 상태를 취소로 변경
			for(OrdersItemVo ordersItemVo : ordersItemList) {
				ordersService.changeStatus(ordersItemVo.getOrdersNo(), "취소");
				System.out.println("초과된 주문 처리 : " + ordersItemVo.getOrdersNo());
			}
		}
	}
	
	// 시간이 초과된 비회원 장바구니들은 삭제
	public void timeOverBasket() {
		Integer count = basketService.deleteTimeOver(BASKET_TIME);
		if(count != 0) System.out.println("초과된 비회원 장바구니 처리 : " + count);
	}
}
