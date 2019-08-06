package com.cafe24.mhmall.controller.api;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhmall.dto.JSONResult;
import com.cafe24.mhmall.dto.RequestMemberIdDto;
import com.cafe24.mhmall.security.Auth;
import com.cafe24.mhmall.security.Auth.Role;
import com.cafe24.mhmall.service.CategoryService;
import com.cafe24.mhmall.service.ItemImgService;
import com.cafe24.mhmall.service.ItemService;
import com.cafe24.mhmall.service.MemberService;
import com.cafe24.mhmall.vo.ItemImgVo;
import com.cafe24.mhmall.vo.ItemVo;
import com.cafe24.mhmall.vo.MemberVo;
import com.cafe24.mhmall.vo.OptionDetailVo;
import com.cafe24.mhmall.vo.OptionVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("adminMemberAPIController")
@RequestMapping("/api/admin/member")
@Api(value = "AdminMemberController", description = "관리자 회원관리 컨트롤러")
public class AdminMemberController {
	
	@Autowired
	MemberService memberService;
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = "")
	})
	@RequestMapping(value = {"/list", "/list/{search}"}, method = RequestMethod.GET)
	@ApiOperation(value = "회원 리스트", notes = "회원 리스트 요청 API")
	public ResponseEntity<JSONResult> list(
			@PathVariable(name = "search", required = false, value = "") String search
			) {
		
		// Service에 회원리스트 요청
		List<MemberVo> memberList = memberService.getList(search);
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberList));
	}
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "id", value = "회원아이디", paramType = "path", required = true, defaultValue = ""),
	})
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "회원 상세보기", notes = "회원 상세보기 요청 API")
	public ResponseEntity<JSONResult> view(
			@ModelAttribute @Valid RequestMemberIdDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 회원상세 요청
		MemberVo memberVo = memberService.getById(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberVo));
	}
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = "")
	})
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@ApiOperation(value = "회원 삭제", notes = "회원 삭제 요청 API")
	public ResponseEntity<JSONResult> delete(
			@RequestBody @Valid RequestMemberIdDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 회원 삭제 요청
		boolean isSuccess = memberService.delete(dto.getId());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
}
