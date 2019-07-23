package com.cafe24.mhmall.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhmall.dto.JSONResult;
import com.cafe24.mhmall.dto.RequestGuestOrdersDto;
import com.cafe24.mhmall.dto.RequestGuestOrdersViewDto;
import com.cafe24.mhmall.dto.RequestMemberIdDto;
import com.cafe24.mhmall.dto.RequestOrdersNoDto;
import com.cafe24.mhmall.dto.RequestOrdersTrackingDto;
import com.cafe24.mhmall.dto.RequestOrdersWriteDto;
import com.cafe24.mhmall.security.Auth;
import com.cafe24.mhmall.security.AuthUser;
import com.cafe24.mhmall.security.Auth.Role;
import com.cafe24.mhmall.service.CategoryService;
import com.cafe24.mhmall.service.GuestService;
import com.cafe24.mhmall.service.ItemImgService;
import com.cafe24.mhmall.service.ItemService;
import com.cafe24.mhmall.service.MemberService;
import com.cafe24.mhmall.service.OptionService;
import com.cafe24.mhmall.service.OrdersItemService;
import com.cafe24.mhmall.service.OrdersService;
import com.cafe24.mhmall.vo.GuestVo;
import com.cafe24.mhmall.vo.ItemImgVo;
import com.cafe24.mhmall.vo.ItemVo;
import com.cafe24.mhmall.vo.MemberVo;
import com.cafe24.mhmall.vo.OptionDetailVo;
import com.cafe24.mhmall.vo.OptionVo;
import com.cafe24.mhmall.vo.OrdersItemVo;
import com.cafe24.mhmall.vo.OrdersVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("ordersAPIController")
@RequestMapping("/api/orders")
@Api(value = "OrdersController", description = "주문관리 컨트롤러")
public class OrdersController {
	
	@Autowired
	OptionService optionService;
	
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	GuestService guestService;
	
	@Autowired
	OrdersItemService ordersItemService;
	

	@Transactional(rollbackFor=Exception.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "guestName", value = "비회원이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPhone", value = "비회원연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPassword", value = "비회원비밀번호", paramType = "query", required = true, defaultValue = ""),

		@ApiImplicitParam(name = "optionNos", value = "주문상품들", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "optionCnts", value = "주문수량들", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest", method = RequestMethod.POST)
	@ApiOperation(value = "비회원 주문", notes = "비회원 주문 요청 API")
	public ResponseEntity<JSONResult> guestOrders(
			@ModelAttribute @Valid RequestGuestOrdersDto guestDto,
			BindingResult result,
			@RequestParam(name = "optionNos", required = true) Long[] optionNos,
			@RequestParam(name = "optionCnts", required = true) Integer[] optionCnts
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));

		// 존재하는 옵션들인지 확인
		if(!optionService.isExistAllOption(optionNos)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 상품이 존재합니다."));
		
		// 옵션의 재고가 있는지 확인(하나라도 없는 것이 있으면 취소, 모두 있으면 남은 재고량 줄이기)
		if(!optionService.isExistAllCnt(optionNos, optionCnts)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("재고가 부족한 상품이 존재합니다."));

		// 금액계산
		Long money = optionService.moneySum(optionNos, optionCnts);
		
		// 비회원 주문 데이터 추가(회원번호:null, 상태:주문대기) => 주문번호 받기
		String ordersNo = ordersService.guestOrdersAdd(money);
		
		// 비회원 데이터 추가 <= 주문번호
		guestService.add(ordersNo, guestDto.toVo());
		
		// 주문내역 일괄 추가 <= 주문번호
		ordersItemService.add(ordersNo, optionNos, optionCnts);
		
		// 주문번호 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(ordersNo));
	}

	
	@Transactional(rollbackFor=Exception.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPassword", value = "비회원비밀번호", paramType = "query", required = true, defaultValue = ""),

		@ApiImplicitParam(name = "toName", value = "받는사람이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toPhone", value = "받는사람연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toZipcode", value = "받는사람우편번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toAddr", value = "받는사람주소", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest/post", method = RequestMethod.POST)
	@ApiOperation(value = "비회원 주문 완료", notes = "비회원 주문 완료 요청 API")
	public ResponseEntity<JSONResult> guestOrdersPost(
			@ModelAttribute @Valid RequestGuestOrdersViewDto guestDto,
			@ModelAttribute @Valid RequestOrdersWriteDto ordersDto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));

		// 존재하는 주문이고 상태가 "주문대기"인지 확인
		if(!ordersService.isExistAndValid(guestDto.toVo())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 접근입니다."));
		
		// 주문에 받는사람 정보를 변경하고 상태를 "입금대기"로 변경
		if(!ordersService.ordersPost(guestDto.getOrdersNo(), ordersDto.toVo()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("주문실패"));
		
		// 주문을 불러온다.
		OrdersVo ordersVo = ordersService.getByOrdersNo(guestDto.getOrdersNo());
		
		// 주문번호, 가상계좌은행, 가상계좌번호, 결제해야할 금액을 리턴 
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(ordersVo));
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
