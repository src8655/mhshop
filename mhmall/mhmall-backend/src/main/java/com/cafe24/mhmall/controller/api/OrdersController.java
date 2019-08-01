package com.cafe24.mhmall.controller.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhmall.dto.JSONResult;
import com.cafe24.mhmall.dto.RequestBasketGuestDto;
import com.cafe24.mhmall.dto.RequestGuestChangePwDto;
import com.cafe24.mhmall.dto.RequestGuestFindPwDto;
import com.cafe24.mhmall.dto.RequestGuestOrdersDto;
import com.cafe24.mhmall.dto.RequestGuestOrdersViewDto;
import com.cafe24.mhmall.dto.RequestMemberIdDto;
import com.cafe24.mhmall.dto.RequestOrdersNoDto;
import com.cafe24.mhmall.dto.RequestOrdersTrackingDto;
import com.cafe24.mhmall.dto.RequestOrdersWriteDto;
import com.cafe24.mhmall.dto.ResponseOrdersDto;
import com.cafe24.mhmall.dto.ResponseOrdersViewDto;
import com.cafe24.mhmall.security.Auth;
import com.cafe24.mhmall.security.AuthUser;
import com.cafe24.mhmall.security.Auth.Role;
import com.cafe24.mhmall.service.BasketService;
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
	
	@Autowired
	BasketService basketService;
	

	// sqlException 발생 시 롤백
	@Transactional(rollbackFor=Exception.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "guestSession", value = "비회원식별자", paramType = "query", required = true, defaultValue = ""),
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
			@ModelAttribute @Valid RequestBasketGuestDto basketDto,
			BindingResult result,
			@RequestParam(name = "optionNos", required = true) Long[] optionNos,
			@RequestParam(name = "optionCnts", required = true) Integer[] optionCnts
			) {
		// 유효성검사
		if(result.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));

		// 존재하는 옵션들인지 확인
		if(!optionService.isExistAllOption(optionNos))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 상품이 존재합니다."));
		
		// 판매중인 상품들인지 확인
		if(!optionService.isOnSaleAll(optionNos))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("판매중이 아닌 상품이 존재합니다."));
		
		// 옵션의 재고가 있는지 확인(하나라도 없는 것이 있으면 취소, 모두 있으면 남은 재고량 줄이기)
		try {optionService.isExistAllCnt(optionNos, optionCnts);} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("재고가 부족한 상품이 존재합니다."));
		}

		// 금액계산
		Long money = optionService.moneySum(optionNos, optionCnts);
		
		// 주문 데이터 추가(회원번호:null, 상태:주문대기) => 주문번호 받기
		String ordersNo = ordersService.guestOrdersAdd(money, null);
		
		// 옵션으로 비회원 장바구니 삭제
		basketService.deleteAllByOptionNoG(optionNos, basketDto.toVo());
		
		// 비회원 데이터 추가 <= 주문번호
		guestService.add(ordersNo, guestDto.toVo());
		
		// 주문내역 일괄 추가 <= 주문번호
		ordersItemService.add(ordersNo, optionNos, optionCnts);
		
		// 주문내역 리스트 받기
		List<OrdersItemVo> ordersItemList = ordersItemService.getListByOrdersNo(ordersNo);
		
		// 주문번호 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(new ResponseOrdersDto(ordersNo, ordersItemList)));
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
	@RequestMapping(value = "/guest", method = RequestMethod.PUT)
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
	
	
	
	
	
	// sqlException 발생 시 롤백
	@Transactional(rollbackFor=Exception.class)
	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "optionNos", value = "주문상품들", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "optionCnts", value = "주문수량들", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	@ApiOperation(value = "회원 주문", notes = "회원 주문 요청 API")
	public ResponseEntity<JSONResult> memberOrders(
			@RequestParam(name = "optionNos", required = true) Long[] optionNos,
			@RequestParam(name = "optionCnts", required = true) Integer[] optionCnts,
			@AuthUser MemberVo authMember
			) {
		// 존재하는 옵션들인지 확인
		if(!optionService.isExistAllOption(optionNos))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 상품이 존재합니다."));
		
		// 판매중인 상품들인지 확인
		if(!optionService.isOnSaleAll(optionNos))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("판매중이 아닌 상품이 존재합니다."));
		
		// 옵션의 재고가 있는지 확인(하나라도 없는 것이 있으면 취소, 모두 있으면 남은 재고량 줄이기)
		try {optionService.isExistAllCnt(optionNos, optionCnts);} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("재고가 부족한 상품이 존재합니다."));
		}

		// 금액계산
		Long money = optionService.moneySum(optionNos, optionCnts);
		
		// 회원 주문 데이터 추가(상태:주문대기) => 주문번호 받기
		String ordersNo = ordersService.guestOrdersAdd(money, authMember.getId());

		// 옵션으로 회원 장바구니 삭제
		basketService.deleteAllByOptionNoM(optionNos, authMember.getId());
		
		// 주문내역 일괄 추가 <= 주문번호
		ordersItemService.add(ordersNo, optionNos, optionCnts);
		
		// 주문내역 리스트 받기
		List<OrdersItemVo> ordersItemList = ordersItemService.getListByOrdersNo(ordersNo);
		
		// 주문번호 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(new ResponseOrdersDto(ordersNo, ordersItemList)));
	}
	
	
	

	@Transactional(rollbackFor=Exception.class)
	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = ""),
		
		@ApiImplicitParam(name = "toName", value = "받는사람이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toPhone", value = "받는사람연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toZipcode", value = "받는사람우편번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "toAddr", value = "받는사람주소", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/member", method = RequestMethod.PUT)
	@ApiOperation(value = "회원 주문 완료", notes = "회원 주문 완료 요청 API")
	public ResponseEntity<JSONResult> memberOrdersPost(
			@ModelAttribute @Valid RequestOrdersNoDto dto,
			@ModelAttribute @Valid RequestOrdersWriteDto ordersDto,
			BindingResult result,
			@AuthUser MemberVo authMember
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));

		// 존재하는 주문이고 상태가 "주문대기"인지 확인(회원)
		if(!ordersService.isExistAndValidMember(dto.getOrdersNo(), authMember.getId()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("잘못된 접근입니다."));
		
		// 주문에 받는사람 정보를 변경하고 상태를 "입금대기"로 변경
		if(!ordersService.ordersPost(dto.getOrdersNo(), ordersDto.toVo()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("주문실패"));
		
		// 주문을 불러온다.
		OrdersVo ordersVo = ordersService.getByOrdersNo(dto.getOrdersNo());
		
		// 주문번호, 가상계좌은행, 가상계좌번호, 결제해야할 금액을 리턴 
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(ordersVo));
	}
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPassword", value = "비회원비밀번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest/view", method = RequestMethod.POST)
	@ApiOperation(value = "비회원 주문상세", notes = "비회원 주문상세 요청 API")
	public ResponseEntity<JSONResult> guestOrdersView(
			@ModelAttribute @Valid RequestGuestOrdersViewDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 존재하고 주문대기 상태가 아닌 것이 존재하는지
		if(!ordersService.isExistAndEnable(dto.toVo()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 주문입니다."));
		
		// 비회원 주문 상세 조회
		OrdersVo ordersVo = ordersService.getByOrdersNo(dto.getOrdersNo());
		
		// 비회원 주문상품 리스트
		List<OrdersItemVo> ordersItemList = ordersItemService.getListByOrdersNo(dto.getOrdersNo());
		
		// 주문상세와 주문상품리스트를 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(new ResponseOrdersViewDto(ordersVo, ordersItemList)));
	}
	
	
	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	@ApiOperation(value = "회원 주문 리스트", notes = "회원 주문 리스트 요청 API")
	public ResponseEntity<JSONResult> memberOrdersList(
			@AuthUser MemberVo authMember
			) {
		
		// 회원 주문 리스트
		List<OrdersVo> ordersList = ordersService.getListByMemberId(authMember.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(ordersList));
	}
	
	

	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/member/view", method = RequestMethod.GET)
	@ApiOperation(value = "회원 주문 상세", notes = "회원 주문 상세 요청 API")
	public ResponseEntity<JSONResult> memberOrdersView(
			@ModelAttribute @Valid RequestOrdersNoDto dto,
			BindingResult result,
			@AuthUser MemberVo authMember
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 존재하고 주문대기 상태가 아닌 것(회원)
		if(!ordersService.isExistAndEnableMember(dto.toVo(), authMember.getId()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 주문입니다."));
		
		// 비회원 주문 상세 조회
		OrdersVo ordersVo = ordersService.getByOrdersNo(dto.getOrdersNo());
		
		// 비회원 주문상품 리스트
		List<OrdersItemVo> ordersItemList = ordersItemService.getListByOrdersNo(dto.getOrdersNo());

		// 주문상세와 주문상품리스트를 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(new ResponseOrdersViewDto(ordersVo, ordersItemList)));
	}
	
	
	
	@Transactional(rollbackFor=Exception.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "path", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPassword", value = "비회원비밀번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest/cancel/{ordersNo}", method = RequestMethod.PUT)
	@ApiOperation(value = "비회원 주문취소", notes = "비회원 주문취소 요청 API")
	public ResponseEntity<JSONResult> guestOrdersCancel(
			@ModelAttribute @Valid RequestGuestOrdersViewDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));

		// 존재하고 주문대기 상태가 아닌 것이 존재하는지
		if(!ordersService.isExistAndEnable(dto.toVo()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 주문입니다."));
		
		// 상태가 입금 대기중인지 확인
		if(!ordersService.equalsStatus(dto.getOrdersNo(), "입금대기"))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("취소할 수 없는 상태입니다."));
		
		// 상태를 취소상태로 변경
		boolean isSuccess = ordersService.changeStatus(dto.getOrdersNo(), "취소");
		
		// 주문상품리스트를 받아와서 구매한 수량만큼 재고량 복구
		List<OrdersItemVo> ordersItemList = ordersItemService.getListByOrdersNo(dto.getOrdersNo());
		if(isSuccess) isSuccess = optionService.restoreCnt(ordersItemList);
		
		// 결과 성공여부를 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	

	@Transactional(rollbackFor=Exception.class)
	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/member/cancel/{ordersNo}", method = RequestMethod.PUT)
	@ApiOperation(value = "회원 주문 취소", notes = "회원 주문 취소 요청 API")
	public ResponseEntity<JSONResult> memberOrdersCancel(
			@ModelAttribute @Valid RequestOrdersNoDto dto,
			BindingResult result,
			@AuthUser MemberVo authMember
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 존재하고 주문대기 상태가 아닌 것(회원)
		if(!ordersService.isExistAndEnableMember(dto.toVo(), authMember.getId()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 주문입니다."));

		// 상태가 입금 대기중인지 확인
		if(!ordersService.equalsStatus(dto.getOrdersNo(), "입금대기"))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("취소할 수 없는 상태입니다."));
		
		// 상태를 취소상태로 변경
		boolean isSuccess = ordersService.changeStatus(dto.getOrdersNo(), "취소");
		
		// 주문상품리스트를 받아와서 구매한 수량만큼 재고량 복구
		List<OrdersItemVo> ordersItemList = ordersItemService.getListByOrdersNo(dto.getOrdersNo());
		if(isSuccess) isSuccess = optionService.restoreCnt(ordersItemList);
		
		// 결과 성공여부를 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "guestName", value = "비회원이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPhone", value = "비회원연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPassword", value = "비회원비밀번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest/ordersno", method = RequestMethod.POST)
	@ApiOperation(value = "주문번호 찾기", notes = "주문번호 찾기 요청 API")
	public ResponseEntity<JSONResult> guestFindOrdersNo(
			@ModelAttribute @Valid RequestGuestOrdersDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 비회원의 주문리스트 찾기(주문번호, 주문일, 상태)
		List<OrdersVo> ordersList = guestService.findOrdersNo(dto.toVo());
		
		// 결과 성공여부를 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(ordersList));
	}
	
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestName", value = "비회원이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPhone", value = "비회원연락처", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest/password", method = RequestMethod.POST)
	@ApiOperation(value = "비회원 주문 비밀번호 찾기", notes = "비회원 주문 비밀번호 찾기 요청 API")
	public ResponseEntity<JSONResult> guestFindPw(
			@ModelAttribute @Valid RequestGuestFindPwDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 조건에 맞는 주문번호가 존재하는지?
		boolean isExistOrdersNo = guestService.findPw(dto.toVo());
		
		// 존재여부만 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isExistOrdersNo));
	}
	
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "ordersNo", value = "주문번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestName", value = "비회원이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPhone", value = "비회원연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestPassword", value = "변경할 비회원비밀번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest/password", method = RequestMethod.PUT)
	@ApiOperation(value = "비회원 주문 비밀번호 변경", notes = "비회원 주문 비밀번호 변경 요청 API")
	public ResponseEntity<JSONResult> guestChangePw(
			@ModelAttribute @Valid RequestGuestChangePwDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 비회원 주문 비밀번호 변경
		boolean isSuccess = guestService.changePw(dto.toVo());
		
		// 성공여부 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}

	
}
