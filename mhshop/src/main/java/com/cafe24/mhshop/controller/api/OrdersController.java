package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.dto.RequestGuestOrdersDto;
import com.cafe24.mhshop.dto.RequestGuestOrdersViewDto;
import com.cafe24.mhshop.dto.RequestMemberIdDto;
import com.cafe24.mhshop.dto.RequestOrdersNoDto;
import com.cafe24.mhshop.dto.RequestOrdersTrackingDto;
import com.cafe24.mhshop.dto.RequestOrdersWriteDto;
import com.cafe24.mhshop.security.Auth;
import com.cafe24.mhshop.security.Auth.Role;
import com.cafe24.mhshop.security.AuthUser;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.GuestService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.service.MemberService;
import com.cafe24.mhshop.service.OrdersItemService;
import com.cafe24.mhshop.service.OrdersService;
import com.cafe24.mhshop.vo.GuestVo;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.MemberVo;
import com.cafe24.mhshop.vo.OptionDetailVo;
import com.cafe24.mhshop.vo.OptionVo;
import com.cafe24.mhshop.vo.OrdersItemVo;
import com.cafe24.mhshop.vo.OrdersVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("ordersAPIController")
@RequestMapping("/api/orders")
@Api(value = "OrdersController", description = "주문관리 컨트롤러")
public class OrdersController {
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "guestName", value = "비회원이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPhone", value = "비회원연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPassword", value = "비회원비밀번호", paramType = "query", required = true, defaultValue = ""),

		@ApiImplicitParam(name = "toName", value = "받는사람이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toPhone", value = "받는사람연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toZipcode", value = "받는사람우편번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toAddr", value = "받는사람주소", paramType = "query", required = true, defaultValue = ""),
		
		@ApiImplicitParam(name = "optionNos", value = "주문상품들", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest", method = RequestMethod.POST)
	@ApiOperation(value = "비회원 주문", notes = "비회원 주문 요청 API")
	public ResponseEntity<JSONResult> guestOrders(
			@ModelAttribute @Valid RequestGuestOrdersDto guestDto,
			@ModelAttribute @Valid RequestOrdersWriteDto OrdersDto,
			BindingResult result,
			@RequestParam(name = "optionNos", required = true) Long[] optionNos
			) {
		
		// valid 체크
		
		// 옵션의 재고가 있는지 확인(하나라도 없는 것이 있으면 취소)
		
		// 주문 데이터 추가(회원번호:null) => 주문번호 받기
		
		// 비회원 데이터 추가 <= 주문번호
		
		// 주문내역 일괄 추가
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	

	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "toName", value = "받는사람이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toPhone", value = "받는사람연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toZipcode", value = "받는사람우편번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toAddr", value = "받는사람주소", paramType = "query", required = true, defaultValue = ""),

		@ApiImplicitParam(name = "optionNos", value = "주문상품들", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	@ApiOperation(value = "회원 주문", notes = "회원 주문 요청 API")
	public ResponseEntity<JSONResult> memberOrders(
			@ModelAttribute @Valid RequestOrdersWriteDto OrdersDto,
			BindingResult result,
			@RequestParam(name = "optionNos", required = true) Long[] optionNos,
			@AuthUser MemberVo authMember
			) {
		
		// valid 체크
		
		// 옵션의 재고가 있는지 확인(하나라도 없는 것이 있으면 취소)
		
		// 주문 데이터 추가 => 주문번호 받기
		
		// 주문내역 일괄 추가(주문한만큼 옵션 재고 조정)
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPassword", value = "비회원비밀번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest/view", method = RequestMethod.POST)
	@ApiOperation(value = "비회원 주문상세", notes = "비회원 주문상세 요청 API")
	public ResponseEntity<JSONResult> guestOrdersView(
			@ModelAttribute @Valid RequestGuestOrdersViewDto OrdersDto,
			BindingResult result
			) {
		
		// valid 체크
		
		// 주문 상세 조회
		
		// 주문내역 리스트
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	
	
	@Auth
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	@ApiOperation(value = "회원 주문 리스트", notes = "회원 주문 리스트 요청 API")
	public ResponseEntity<JSONResult> memberOrdersList(
			@AuthUser MemberVo authMember
			) {
		
		// 주문 리스트
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "path", required = true, defaultValue = "")
	})
	@Auth
	@RequestMapping(value = "/member/list/{ordersNo}", method = RequestMethod.GET)
	@ApiOperation(value = "회원 주문 상세", notes = "회원 주문 상세 요청 API")
	public ResponseEntity<JSONResult> memberOrdersView(
			@ModelAttribute @Valid RequestOrdersNoDto dto,
			BindingResult result,
			@AuthUser MemberVo authMember
			) {
		
		// valid 체크
		
		// 주문 상세 조회
		
		// 주문내역 리스트
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "path", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPassword", value = "비회원비밀번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest/cancel/{ordersNo}", method = RequestMethod.PUT)
	@ApiOperation(value = "비회원 주문취소", notes = "비회원 주문취소 요청 API")
	public ResponseEntity<JSONResult> guestOrdersCancel(
			@ModelAttribute @Valid RequestGuestOrdersViewDto OrdersDto,
			BindingResult result
			) {
		
		// valid 체크
		
		// 상태가 입금 대기중인지 확인
		
		// 상태를 취소상태로 변경
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	

	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/member/cancel/{ordersNo}", method = RequestMethod.GET)
	@ApiOperation(value = "회원 주문 취소", notes = "회원 주문 취소 요청 API")
	public ResponseEntity<JSONResult> memberOrdersCancel(
			@ModelAttribute @Valid RequestOrdersNoDto dto,
			BindingResult result,
			@AuthUser MemberVo authMember
			) {
		
		// valid 체크

		// 상태가 입금 대기중인지 확인

		// 상태를 취소상태로 변경
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}

	
}
