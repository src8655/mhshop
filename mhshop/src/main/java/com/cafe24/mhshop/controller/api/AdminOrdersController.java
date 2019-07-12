package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.GuestService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.service.MemberService;
import com.cafe24.mhshop.service.OrdersItemService;
import com.cafe24.mhshop.service.OrdersService;
import com.cafe24.mhshop.service.PayBankService;
import com.cafe24.mhshop.service.PayKakaoService;
import com.cafe24.mhshop.vo.GuestVo;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.MemberVo;
import com.cafe24.mhshop.vo.OptionDetailVo;
import com.cafe24.mhshop.vo.OptionVo;
import com.cafe24.mhshop.vo.OrdersItemVo;
import com.cafe24.mhshop.vo.OrdersVo;
import com.cafe24.mhshop.vo.PayBankVo;
import com.cafe24.mhshop.vo.PayKakaoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("adminOrdersAPIController")
@RequestMapping("/api/admin/orders")
@Api(value = "AdminOrdersController", description = "관리자 주문관리 컨트롤러")
public class AdminOrdersController {
	
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	PayBankService payBankService;
	
	@Autowired
	PayKakaoService payKakaoService;
	
	@Autowired
	GuestService guestService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	OrdersItemService ordersItemService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "주문 리스트", notes = "주문 리스트 요청 API")
	public JSONResult list() {
		
		// 권한 확인
		
		
		// OrdersService에 주문리스트 요청
		List<OrdersVo> ordersList = ordersService.getList();
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("ordersList", ordersList);
		dataMap.put("forward", "admin/orders_list");
		return JSONResult.success(dataMap);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/view/{ordersNo}", method = RequestMethod.GET)
	@ApiOperation(value = "주문 상세보기", notes = "주문 상세보기 요청 API")
	public JSONResult view(
			@PathVariable(value = "ordersNo") String ordersNo
			) {
		
		// 권한 확인
		
		
		
		// OrdersService에 주문상세 요청
		OrdersVo ordersVo = ordersService.getByOrdersNo(ordersNo);
		
		// PayBankService와 PayKakaoService에 결제정보 요청
		PayBankVo payBankVo = payBankService.getByOrdersNo(ordersNo);
		PayKakaoVo payKakaoVo = payKakaoService.getByOrdersNo(ordersNo);
		
		// GuestService나 MemberService에 구매자 정보 요청
		GuestVo guestVo = guestService.getByOrdersNo(ordersNo);
		MemberVo memberVo = null;
		if(ordersVo.getMemberId() != null)
			memberVo = memberService.getById(ordersVo.getMemberId());
		
		// OrdersItemService에 주문상품리스트 요청
		List<OrdersItemVo> ordersItemList = ordersItemService.getListByOrdersNo();
		
		
		
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("ordersVo", ordersVo);
		dataMap.put("payBankVo", payBankVo);
		dataMap.put("payKakaoVo", payKakaoVo);
		dataMap.put("guestVo", guestVo);
		dataMap.put("memberVo", memberVo);
		dataMap.put("ordersItemList", ordersItemList);
		dataMap.put("forward", "admin/orders_view");
		return JSONResult.success(dataMap);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/paycheck/{ordersNo}", method = RequestMethod.PUT)
	@ApiOperation(value = "무통장 결제확인 상태변경 요청", notes = "무통장 결제확인 상태변경 요청 API")
	public JSONResult paycheck(
			@PathVariable(value = "ordersNo") String ordersNo
			) {
		
		// 권한 확인
		
		// OrderService에서 하나 가져와서 상태 확인(입금대기 상태가 아니면 fail)
		OrdersVo ordersVo = ordersService.getByOrdersNo(ordersNo);
		if(!ordersVo.getStatus().equals("입금대기")) return JSONResult.fail("변경할 수 없는 상태입니다.");
		
		// PayBankService에 날짜 갱신 요청
		payBankService.updateDate(ordersNo);
		
		// OrdersService에 상태변경 요청(입금대기 상태가 아니였으면 false가 나온다)
		boolean isSuccess = ordersService.changeStatus(ordersNo, "결제완료");
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/orders/view");
		return JSONResult.success(dataMap);
	}
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "path", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "trackingNum", value = "운송장번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/trackingnumbercheck/{ordersNo}", method = RequestMethod.PUT)
	@ApiOperation(value = "운송장번호 등록 요청", notes = "운송장번호 등록 요청 API")
	public JSONResult trackingnumbercheck(
			@PathVariable(value = "ordersNo") String ordersNo,
			@RequestParam(value = "trackingNum", required = true, defaultValue = "") String trackingNum
			) {
		
		// 권한 확인
		

		// 유효성검사
		if(trackingNum.equals("")) return JSONResult.fail("잘못된 입력 입니다.");
		
		// OrdersService에 운송장번호 수정 요청
		ordersService.changeTrackingNum(ordersNo, trackingNum);
		
		// OrdersService에 상태변경 요청
		boolean isSuccess = ordersService.changeStatus(ordersNo, "배송중");
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/orders/view");
		return JSONResult.success(dataMap);
	}
	
}
