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
import com.cafe24.mhshop.dto.RequestMemberIdDto;
import com.cafe24.mhshop.security.Auth;
import com.cafe24.mhshop.security.Auth.Role;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.service.MemberService;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.MemberVo;
import com.cafe24.mhshop.vo.OptionDetailVo;
import com.cafe24.mhshop.vo.OptionVo;

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
	

	@Auth(role = Role.ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "회원 리스트", notes = "회원 리스트 요청 API")
	public ResponseEntity<JSONResult> list() {
		// 권한 확인
		
		// Service에 회원리스트 요청
		List<MemberVo> memberList = memberService.getList();
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberList));
	}
	

	@Auth(role = Role.ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = ""),
		
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
	

	@Auth(role = Role.ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "id", value = "회원아이디", paramType = "path", required = true, defaultValue = ""),
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "회원 삭제", notes = "회원 삭제 요청 API")
	public ResponseEntity<JSONResult> delete(
			@ModelAttribute @Valid RequestMemberIdDto dto,
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
