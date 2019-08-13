package com.cafe24.mhmall.frontend.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mhmall.frontend.dto.JSONResult;
import com.cafe24.mhmall.frontend.dto.RequestJoinDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.service.impl.MemberServiceImpl;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;

	// 로그인 폼
	@RequestMapping(value = {"/login", "/login/{fail}"}, method = RequestMethod.GET)
	public String loginform(
			@PathVariable("fail") Optional<Long> fail,
			Model model
			) {
		
		if(fail.isPresent()) {
			model.addAttribute("failMessage", "아이디/비밀번호를 확인해 주세요.");
		}else {
			model.addAttribute("failMessage", "");
		}
		
		
		return "member/login";
	}
	
/*
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(name = "id", required = true, defaultValue = "") String id,
			@RequestParam(name = "password", required = true, defaultValue = "") String password,
			Model model,
			HttpSession session
			) {
		
		
		ResponseJSONResult<MemberVo> rJson = memberService.login(id, password);

        // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
        // 세션에 저장
        MemberVo memberVo = rJson.getData();
        session.setAttribute("authUser", new MemberVo(memberVo.getId(), null, memberVo.getName(), null, null, null, null, null, memberVo.getRole(), null, memberVo.getMockToken()));
		
		return "redirect:/";
	}
	
	

	// 로그아웃
	@RequestMapping("/logout")
	public String logout(
			HttpSession session
			) {
		
		// 세션에서 제거
        session.setAttribute("authUser", null);
		
		return "redirect:/";
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// 회원가입 폼
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join_form(
			) {
		
		return "member/join_write";
	}
	
	
	
	
	// 아이디 중복확인
	@ResponseBody
	@RequestMapping(value = "/join/idcheck/{id}", method = RequestMethod.GET)
	public ResponseJSONResult<Boolean> id_check(
			@PathVariable("id") String id
			) {
		
		ResponseJSONResult<Boolean> rJson = memberService.idcheck(id);
		
		return rJson;
	}
	
	
	

	// 회원가입 완료
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join_post(
			@ModelAttribute RequestJoinDto dto,
			HttpSession session,
			Model model
			) {
		
		ResponseJSONResult<Boolean> rJson = memberService.add(dto);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "가입실패");
        	return "post/error";
        }
        
        // 가입 결과페이지
        session.setAttribute("joinResult", dto.getName());
		return "redirect:/member/join_result";
	}
	
	
	// 회원가입 완료 페이지
	@RequestMapping("/join_result")
	public String join_result(
			HttpSession session,
			Model model
			) {
		
		String name = (String) session.getAttribute("joinResult");
        session.setAttribute("joinResult", null);
        model.addAttribute("name", name);
		
		
		return "member/join_result";
	}
}
