package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.dto.RequestMemberIdDto;
import com.cafe24.mhshop.dto.RequestMemberJoinDto;
import com.cafe24.mhshop.dto.RequestMemberLoginDto;
import com.cafe24.mhshop.security.Auth;
import com.cafe24.mhshop.security.AuthUser;
import com.cafe24.mhshop.service.MemberService;
import com.cafe24.mhshop.vo.MemberVo;

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
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		
		// Service에 요청
		boolean isExist = memberService.idCheck(dto.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isExist));
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "아이디", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "password", value = "비밀번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "name", value = "이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "phone", value = "연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "email", value = "이메일", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "zipcode", value = "우편번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "addr", value = "주소", paramType = "query", required = true, defaultValue = ""),
	})
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ApiOperation(value = "회원을 DB에 등록", notes = "회원등록 API")
	public ResponseEntity<JSONResult> join(
			@ModelAttribute @Valid RequestMemberJoinDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 등록
		boolean isSuccess = memberService.add(dto.toVo());
		
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "아이디", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "password", value = "비밀번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "회원 로그인", notes = "회원 로그인 API")
	public ResponseEntity<JSONResult> login(
			@ModelAttribute @Valid RequestMemberLoginDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		
		// Service로 회원 확인
		MemberVo newMemberVo = memberService.login(dto.getId(), dto.getPassword());
		
		
		// 확인 후 로그인
		if(newMemberVo == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success(null));
		else
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(newMemberVo));
	}
	
	
	/*
	@RequestMapping(value = "/loginupdate", method = RequestMethod.POST)
	@ApiOperation(value = "회원수정 페이지", notes = "회원 수정 페이지 API")
	public ResponseEntity<JSONResult> loginUpdate(
			@ModelAttribute @Valid RequestMemberLoginDto dto,
			BindingResult result
			) {

		// 유효성검사
		if(result.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		
		// 회원정보를 가져옴
		MemberVo memberVo = memberService.getById(dto.getId());
		
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(memberVo));
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "password", value = "비밀번호", paramType = "query", required = false, defaultValue = ""),
		@ApiImplicitParam(name = "name", value = "이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "phone", value = "연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "email", value = "이메일", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "zipcode", value = "우편번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "addr", value = "주소", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/loginupdate", method = RequestMethod.PUT)
	@ApiOperation(value = "회원수정", notes = "회원 로그아웃 API")
	public ResponseEntity<JSONResult> loginUpdate(
			@ModelAttribute @Valid RequestMemberJoinDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		
		MemberVo memberVo = dto.toVo();
		if(memberVo.getPassword() == null) memberVo.setPassword("");
		
		
		// 수정요청
		boolean isSuccess = memberService.edit(memberVo);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}*/
}
