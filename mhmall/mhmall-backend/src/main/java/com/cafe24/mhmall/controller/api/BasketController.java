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
import com.cafe24.mhmall.dto.RequestBasketAddDto;
import com.cafe24.mhmall.dto.RequestBasketAddGuestDto;
import com.cafe24.mhmall.dto.RequestBasketDelGuestDto;
import com.cafe24.mhmall.dto.RequestBasketEditDto;
import com.cafe24.mhmall.dto.RequestBasketEditGuestDto;
import com.cafe24.mhmall.dto.RequestBasketGuestDto;
import com.cafe24.mhmall.dto.RequestGuestOrdersDto;
import com.cafe24.mhmall.dto.RequestGuestOrdersViewDto;
import com.cafe24.mhmall.dto.RequestMemberIdDto;
import com.cafe24.mhmall.dto.RequestNoDto;
import com.cafe24.mhmall.dto.RequestOrdersNoDto;
import com.cafe24.mhmall.dto.RequestOrdersTrackingDto;
import com.cafe24.mhmall.dto.RequestOrdersWriteDto;
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
import com.cafe24.mhmall.vo.BasketVo;
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

@RestController("basketAPIController")
@RequestMapping("/api/basket")
@Api(value = "BasketController", description = "장바구니 컨트롤러")
public class BasketController {
	
	@Autowired
	BasketService basketService;
	
	@Autowired
	OptionService optionService;
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "guestSession", value = "비회원식별자", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest", method = RequestMethod.GET)
	@ApiOperation(value = "비회원 장바구니 리스트", notes = "비회원 장바구니 리스트 요청 API")
	public ResponseEntity<JSONResult> basketguestList(
			@ModelAttribute @Valid RequestBasketGuestDto guestDto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 장바구니 리스트 중에 수량보다 재고가 없는 것 일괄삭제
		basketService.guestDeleteByCnt(guestDto.getGuestSession());
		
		// 입력 시간을 현재로 갱신(비회원의 장바구니는 30개월간만 유지된다)
		basketService.guestNewTime(guestDto.getGuestSession());
		
		// 장바구니 리스트
		List<BasketVo> basketList = basketService.getListByGuest(guestDto.getGuestSession());
		
		// 랑바구니 리스트 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(basketList));
	}
	
	
	@Transactional(rollbackFor=Exception.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "optionNo", value = "옵션번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestSession", value = "비회원식별자", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "cnt", value = "수량", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest", method = RequestMethod.POST)
	@ApiOperation(value = "비회원 장바구니 추가", notes = "비회원 장바구니 추가 요청 API")
	public ResponseEntity<JSONResult> basketguestAdd(
			@ModelAttribute @Valid RequestBasketAddGuestDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 존재하는 옵션인지 확인
		if(!optionService.isExistOption(dto.getOptionNo())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 상품입니다."));
		
		// 옵션의 재고가 수량만큼 존재하는지 확인
		if(!optionService.isExistCnt(dto.getOptionNo(), dto.getCnt())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("재고가 부족합니다."));
		
		// 현재 장바구니에 같은 옵션 삭제
		basketService.deleteByOptionGuest(dto.toVo());
		
		// 비회원 장바구니 추가
		boolean isSuccess = basketService.addGuest(dto.toVo());
		
		// 성공여부 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	

	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "장바구니번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestSession", value = "비회원식별자", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest", method = RequestMethod.DELETE)
	@ApiOperation(value = "비회원 장바구니 삭제", notes = "비회원 장바구니 삭제 요청 API")
	public ResponseEntity<JSONResult> basketguestDelete(
			@ModelAttribute @Valid RequestBasketDelGuestDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 비회원 장바구니 삭제
		boolean isSuccess = basketService.deleteGuest(dto.toVo());
		
		// 성공여부 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "장바구니번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "guestSession", value = "비회원식별자", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "cnt", value = "수량", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/guest", method = RequestMethod.PUT)
	@ApiOperation(value = "비회원 장바구니 수정", notes = "비회원 장바구니 수정 요청 API")
	public ResponseEntity<JSONResult> basketguestEdit(
			@ModelAttribute @Valid RequestBasketEditGuestDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 비회원 장바구니 정보가 존재하는지 확인하고 가져오기
		BasketVo basketVo = basketService.getByNoGuest(dto.toVo());
		if(basketVo == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 장바구니 입니다."));
		
		// 옵션의 재고가 수량만큼 존재하는지 확인
		if(!optionService.isExistCnt(basketVo.getOptionNo(), dto.getCnt())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("재고가 부족합니다."));
		
		// 장바구니 수정
		boolean isSuccess = basketService.updateCnt(dto.getNo(), dto.getCnt());
		
		// 성공여부 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}

	
	
	
	
	
	
	
	
	

	@Auth
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	@ApiOperation(value = "회원 장바구니 리스트", notes = "회원 장바구니 리스트 요청 API")
	public ResponseEntity<JSONResult> basketmemberList(
			@AuthUser MemberVo authMember
			) {
		// 장바구니 리스트 중에 수량보다 재고가 없는 것 일괄삭제(회원)
		basketService.memberDeleteByCnt(authMember.getId());
		
		// 회원 장바구니 리스트
		List<BasketVo> basketList = basketService.getListByMember(authMember.getId());
		
		// 장바구니 리스트 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(basketList));
	}
	
	
	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "optionNo", value = "옵션번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "cnt", value = "수량", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	@ApiOperation(value = "회원 장바구니 추가", notes = "회원 장바구니 추가 요청 API")
	public ResponseEntity<JSONResult> basketmemberAdd(
			@ModelAttribute @Valid RequestBasketAddDto dto,
			BindingResult result,
			@AuthUser MemberVo authMember
			) {
		
		// valid 체크
		
		// 옵션의 재고가 수량만큼 존재하는지 확인
		
		// 현재 장바구니에 같은 옵션 삭제
		
		// 장바구니 추가
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	
	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "장바구니번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/member", method = RequestMethod.DELETE)
	@ApiOperation(value = "회원 장바구니 삭제", notes = "회원 장바구니 삭제 요청 API")
	public ResponseEntity<JSONResult> basketmemberDelete(
			@ModelAttribute @Valid RequestNoDto dto,
			BindingResult result,
			@AuthUser MemberVo authMember
			) {
		
		// valid 체크
		
		// 옵션의 재고가 수량만큼 존재하는지 확인
		
		// 장바구니 추가
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	
	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "장바구니번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "cnt", value = "수량", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/member", method = RequestMethod.PUT)
	@ApiOperation(value = "회원 장바구니 수정", notes = "회원 장바구니 수정 요청 API")
	public ResponseEntity<JSONResult> basketmemberEdit(
			@ModelAttribute @Valid RequestBasketEditDto dto,
			BindingResult result,
			@AuthUser MemberVo authMember
			) {
		
		// valid 체크
		
		// 옵션의 재고가 수량만큼 존재하는지 확인
		
		// 장바구니 수정
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	

	
}
