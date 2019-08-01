package com.cafe24.mhmall.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhmall.dto.JSONResult;
import com.cafe24.mhmall.dto.RequestMemberIdDto;
import com.cafe24.mhmall.dto.RequestMemberJoinDto;
import com.cafe24.mhmall.dto.RequestMemberLoginDto;
import com.cafe24.mhmall.security.Auth;
import com.cafe24.mhmall.security.AuthUser;
import com.cafe24.mhmall.security.Auth.Role;
import com.cafe24.mhmall.service.MemberService;
import com.cafe24.mhmall.vo.MemberVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("memberAPIController")
@RequestMapping("/api/member")
@Api(value = "MemberController", description = "회원 컨트롤러")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "아이디", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/join/idcheck/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "회원ID 중복여부 확인", notes = "회원ID 중복확인 API")
	public ResponseEntity<JSONResult> idcheck(
			@ModelAttribute @Valid RequestMemberIdDto dto,
			HttpServletResponse response,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 요청
		boolean isExist = memberService.idCheck(dto.getId());
		
		// 중복하면 True 아니면 False
		return ResponseEntity.status(HttpStatus.OK)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
				.header("Access-Control-Max-Age", "3600")
				.header("Access-Control-Allow-Headers", "x-requested-with")
				.body(JSONResult.success(isExist));
	}
	

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ApiOperation(value = "회원을 DB에 등록", notes = "회원등록 API")
	public ResponseEntity<JSONResult> join(
			@RequestBody @Valid RequestMemberJoinDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 요청
		if(memberService.idCheck(dto.getId())) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("중복된 아이디 입니다."));
		
		// Service에 등록
		boolean isSuccess = memberService.add(dto.toVo());
		
		// 성공여부 리턴
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "회원 로그인", notes = "회원 로그인 API")
	public ResponseEntity<JSONResult> login(
			@RequestBody @Valid RequestMemberLoginDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		
		// Service로 회원 확인
		MemberVo newMemberVo = memberService.login(dto.toVo());
		
		
		// 확인 후 로그인
		if(newMemberVo == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("로그인 실패"));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(newMemberVo));
	}
	
	
	
	
	

	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/loginupdate", method = RequestMethod.GET)
	@ApiOperation(value = "회원수정 페이지", notes = "회원 수정 페이지 API")
	public ResponseEntity<JSONResult> loginUpdate(
			@AuthUser MemberVo authMember
			) {
		// 회원정보를 가져옴
		MemberVo memberVo = memberService.getById(authMember);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberVo));
	}
	

	@Auth
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/loginupdate", method = RequestMethod.PUT)
	@ApiOperation(value = "회원수정", notes = "회원 로그아웃 API")
	public ResponseEntity<JSONResult> loginUpdate(
			@AuthUser MemberVo authMember,
			@RequestBody @Valid RequestMemberJoinDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		MemberVo memberVo = dto.toVo();
		memberVo.setId(authMember.getId());
		
		// 수정요청
		boolean isSuccess = memberService.edit(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
}
