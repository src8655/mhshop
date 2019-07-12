package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
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
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	@ApiOperation(value = "[회원약관과 회원가입 입력 페이지]", notes = "회원가입 요청 API")
	public JSONResult join_form() {
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "join/join_form");
		return JSONResult.success(dataMap);
	}
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "아이디", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/join/idcheck/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "회원ID 중복여부 확인", notes = "회원ID 중복확인 API")
	public JSONResult idcheck(
			@PathVariable(value = "id") String id
			) {
		
		// 유효성검사
		if(!Pattern.matches(MemberVo.REGX_ID, id)) return JSONResult.fail("잘못된 아이디 형식 입니다.");
		
		
		// Service에 요청
		boolean isExist = memberService.idCheck(id);
		
		return JSONResult.success(isExist);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "아이디", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "password", value = "비밀번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "name", value = "이름", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "phone", value = "연락처", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "email", value = "이메일", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "zipcode", value = "우편번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "addr", value = "주소", paramType = "query", required = true, defaultValue = ""),
		
		@ApiImplicitParam(name = "regDate", value = "", paramType = "", required = false, defaultValue = ""),
		@ApiImplicitParam(name = "role", value = "", paramType = "", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ApiOperation(value = "회원을 DB에 등록", notes = "회원등록 API")
	public JSONResult join(
			@ModelAttribute @Valid MemberVo memberVo,
			BindingResult result
			) {
		
		// 유효성검사
		if(!Pattern.matches(MemberVo.REGX_ID, memberVo.getId())) return JSONResult.fail("잘못된 아이디 형식 입니다.");
		if(!Pattern.matches(MemberVo.REGX_PASSWORD, memberVo.getPassword())) return JSONResult.fail("잘못된 비밀번호 형식 입니다.");
		if(!Pattern.matches(MemberVo.REGX_PHONE, memberVo.getPhone())) return JSONResult.fail("잘못된 연락처 형식 입니다.");
		if(!Pattern.matches(MemberVo.REGX_EMAIL, memberVo.getEmail())) return JSONResult.fail("잘못된 이메일 형식 입니다.");
		if(result.hasErrors()) return JSONResult.fail("잘못된 입력 입니다.");
		
		
		
		// Service에 등록
		boolean isSuccess = memberService.add(memberVo);
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/member/join_result");
		return JSONResult.success(dataMap);
	}
	
	
	
	
	@RequestMapping(value = "/join/result", method = RequestMethod.GET)
	@ApiOperation(value = "[회원가입 결과 페이지]", notes = "회원가입 결과 API")
	@ResponseBody
	public JSONResult join_result() {

		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "join/join_result");
		return JSONResult.success(dataMap);
	}
	
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ApiOperation(value = "[로그인 페이지]", notes = "로그인 요청 API")
	@ResponseBody
	public JSONResult login_form() {
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "login/login_form");
		return JSONResult.success(dataMap);
	}
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "아이디", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "password", value = "비밀번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "회원 로그인", notes = "회원 로그인 API")
	public JSONResult login(
			@RequestParam(value = "id", required = true, defaultValue = "") String id,
			@RequestParam(value = "password", required = true, defaultValue = "") String password,
			HttpSession session
			) {
		
		// 유효성검사
		if(!Pattern.matches(MemberVo.REGX_ID, id)) return JSONResult.fail("잘못된 아이디 형식 입니다.");
		if(!Pattern.matches(MemberVo.REGX_PASSWORD, password)) return JSONResult.fail("잘못된 비밀번호 형식 입니다.");
		
		
		// Service로 회원 확인
		MemberVo newMemberVo = memberService.login(id, password);
		
		
		// 확인 후 로그인
		if(newMemberVo != null) {
			session.setAttribute("authUser", newMemberVo);
		}
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("memberVo", newMemberVo);
		dataMap.put("redirect", "/");
		return JSONResult.success(dataMap);
	}
	
	

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ApiOperation(value = "회원 로그아웃", notes = "회원 로그아웃 API")
	public JSONResult logout(HttpSession session) {
		
		
		// 로그아웃 처리
		session.setAttribute("authUser", null);
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/");
		return JSONResult.success(dataMap);
	}
}
