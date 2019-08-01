package com.cafe24.mhmall.frontend.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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

import com.cafe24.mhmall.frontend.dto.RequestJoinDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/member")
public class MemberController {

	// 로그인 폼
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginform() {
		
		return "member/login";
	}
	

	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(
			@RequestParam(name = "id", required = true, defaultValue = "") String id,
			@RequestParam(name = "password", required = true, defaultValue = "") String password,
			Model model,
			HttpSession session
			) {
		

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("password", password);
        ResponseJSONResult<MemberVo> rJson = MhmallRestTemplate.<MemberVo>request("/api/member/login", HttpMethod.POST, params, null, MemberVo.class);

        // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
        MemberVo memberVo = rJson.getData();
        session.setAttribute("authUser", new MemberVo(memberVo.getId(), null, memberVo.getName(), null, null, null, null, null, memberVo.getRole(), memberVo.getMockToken()));
		
		return "redirect:/";
	}
	
	

	// 로그아웃
	@RequestMapping("logout")
	public String logout(
			HttpSession session
			) {
		
        session.setAttribute("authUser", null);
		
		return "redirect:/";
	}
	
	
	// 회원가입 약관
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join() {
		
		return "member/join_agree";
	}
	
	

	// 회원가입 폼
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join_form(
			@RequestParam(name = "agrees", required = true, defaultValue = "false") boolean agrees,
			HttpSession session,
			Model model
			) {
		
		// 약관에 동의하지 않았으면
		if(!agrees) {
			model.addAttribute("message", "약관에 동의해주세요.");
			return "post/error";
		}
		
		return "member/join_write";
	}
	
	
	

	// 회원가입 완료
	@RequestMapping(value = "join_post", method = RequestMethod.POST)
	public String join_post(
			@ModelAttribute RequestJoinDto dto,
			HttpSession session,
			Model model
			) {
		
		MemberVo memberVo = dto.toVo();
		
		// 가입 요청
        Map<String, Object> params = new HashMap<String, Object>();
	    params.put("id", memberVo.getId());
	    params.put("password", memberVo.getPassword());
	    params.put("name", memberVo.getName());
	    params.put("phone", memberVo.getPhone());
	    params.put("email", memberVo.getEmail());
	    params.put("zipcode", memberVo.getZipcode());
	    params.put("addr", memberVo.getAddr());
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.<Boolean>request("/api/member/join", HttpMethod.POST, params, null, Boolean.class);

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
        session.setAttribute("joinResult", memberVo.getName());
		return "redirect:/member/join_result";
	}
	
	
	// 회원가입 완료 페이지
	@RequestMapping("join_result")
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
