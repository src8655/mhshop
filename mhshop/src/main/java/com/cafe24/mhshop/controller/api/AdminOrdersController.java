package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.OptionDetailVo;
import com.cafe24.mhshop.vo.OptionVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("adminOrdersAPIController")
@RequestMapping("/api/adminorders")
@Api(value = "AdminOrdersController", description = "관리자 주문관리 컨트롤러")
public class AdminOrdersController {
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "주문 리스트", notes = "주문 리스트 요청 API")
	public JSONResult list() {
		
		// 권한 확인
		
		
		
		// OrdersService에 주문리스트 요청
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "admin/orders_list");
		return JSONResult.success(dataMap);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ApiOperation(value = "주문 상세보기", notes = "주문 상세보기 요청 API")
	public JSONResult view(
			@RequestParam(value = "ordersNo", required = true, defaultValue = "") String ordersNo
			) {
		
		// 권한 확인
		
		
		// 유효성검사
		
		
		// OrdersService에 주문상세 요청
		
		
		// PayBankService와 PayKakaoService에 결제정보 요청
		
		
		// GuestService나 MemberService에 구매자 정보 요청
		
		
		// OrdersItemService에 주문상품리스트 요청
		
		
		
		
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "admin/orders_view");
		return JSONResult.success(dataMap);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/paycheck", method = RequestMethod.PUT)
	@ApiOperation(value = "무통장 결제확인 상태변경 요청", notes = "무통장 결제확인 상태변경 요청 API")
	public JSONResult paycheck(
			@RequestParam(value = "ordersNo", required = true, defaultValue = "") String ordersNo
			) {
		
		// 권한 확인
		

		// 유효성검사
		
		
		// PayBankService에 날짜 갱신 요청
		
		
		// OrdersService에 상태변경 요청
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "/api/adminorders/view");
		return JSONResult.success(dataMap);
	}
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "trackingNum", value = "운송장번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/trackingnumbercheck", method = RequestMethod.PUT)
	@ApiOperation(value = "운송장번호 등록 요청", notes = "운송장번호 등록 요청 API")
	public JSONResult trackingnumbercheck(
			@RequestParam(value = "ordersNo", required = true, defaultValue = "") String ordersNo,
			@RequestParam(value = "trackingNum", required = true, defaultValue = "") String trackingNum
			) {
		
		// 권한 확인
		

		// 유효성검사
		
		
		// OrdersService에 운송장번호 수정 요청
		
		
		// OrdersService에 상태변경 요청
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminorders/view");
		return JSONResult.success(dataMap);
	}
	
}
