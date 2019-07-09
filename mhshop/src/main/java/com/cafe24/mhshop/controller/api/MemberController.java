package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	@RequestMapping(value = "/join_form", method = RequestMethod.GET)
	@ApiOperation(value = "[회원약관과 회원가입 입력 페이지]", notes = "회원가입 요청 API")
	public Map<String, Object> join_form() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", "join/join_form");
		
		return map;
	}
	
	
	
	@RequestMapping(value = "/join_idcheck/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "회원ID 중복여부 확인", notes = "회원ID 중복확인 API")
	public Map<String, Object> idcheck(
			@PathVariable(value = "id") String id
			) {
		
		// Service에 유효성검사
		
		
		// Service에 요청
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", true);	// isExist
		
		return map;
	}
	
	
	
	@RequestMapping(value = "/join_post", method = RequestMethod.POST)
	@ApiOperation(value = "회원을 DB에 등록", notes = "회원등록 API")
	public Map<String, Object> join_post(
			@RequestParam(value = "id", required = true, defaultValue = "") String id,
			@RequestParam(value = "password", required = true, defaultValue = "") String password,
			@RequestParam(value = "name", required = true, defaultValue = "") String name,
			@RequestParam(value = "phones", required = true, defaultValue = "") String[] phones,
			@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "zipcode", required = true, defaultValue = "") String zipcode,
			@RequestParam(value = "addr", required = true, defaultValue = "") String addr
			
			) {
		
		// 유효성검사
		
		
		// @ModelAttribute로 처리
		MemberVo mvo = new MemberVo();
		mvo.setId(id);
		mvo.setPassword(password);
		mvo.setName(name);
		mvo.setPhone(phones[0] + "-" + phones[1] + "-" + phones[2]);
		mvo.setEmail(email);
		mvo.setZipcode(zipcode);
		mvo.setAddr(addr);
		
		// Service에 등록
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", mvo);
		
		return map;
	}
	
	
	
	
	@RequestMapping(value = "/join_result", method = RequestMethod.GET)
	@ApiOperation(value = "[회원가입 결과 페이지]", notes = "회원가입 결과 API")
	@ResponseBody
	public Map<String, Object> join_result() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", "join/join_result");
		
		return map;
	}
	
	
	
	
	@RequestMapping(value = "/login_form", method = RequestMethod.GET)
	@ApiOperation(value = "[로그인 페이지]", notes = "로그인 요청 API")
	@ResponseBody
	public Map<String, Object> login_form() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", "login/login_form");
		
		return map;
	}
	
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "회원 로그인", notes = "회원 로그인 API")
	public Map<String, Object> login(
			@RequestParam(value = "id", required = true, defaultValue = "") String id,
			@RequestParam(value = "password", required = true, defaultValue = "") String password
			) {
		
		// 유효성검사
		
		
		// @ModelAttribute로 처리
		MemberVo mvo = new MemberVo();
		mvo.setId(id);
		mvo.setPassword(password);
		
		// Service로 회원 확인
		
		
		// 확인 후 로그인
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", mvo);
		
		return map;
	}
	
	

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ApiOperation(value = "회원 로그아웃", notes = "회원 로그아웃 API")
	public Map<String, Object> logout() {
		
		
		
		// 로그아웃 처리
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", "main/index");
		
		return map;
	}
}
