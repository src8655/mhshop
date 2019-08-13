package com.cafe24.mhmall.frontend.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
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
import com.cafe24.mhmall.frontend.security.Auth;
import com.cafe24.mhmall.frontend.security.Auth.Role;
import com.cafe24.mhmall.frontend.security.AuthUser;
import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.service.impl.MemberServiceImpl;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
	
	@Autowired
	MemberService memberService;
	

	// 관리자 회원목록
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = {"", "/{search}"}, method = RequestMethod.GET)
	public String member(
			@PathVariable("search") Optional<String> search,
			@AuthUser MemberVo authUser,
			Model model
			) {
		System.out.println(authUser);
		
		// 관리자 회원목록
		ResponseJSONResult<MemberService.ListMemberVo> rJson = memberService.getMemberList(authUser.getMockToken(), search);
		
		model.addAttribute("memberList", rJson.getData());
		if(search.isPresent()) {
			model.addAttribute("searchs", search.get());
			System.out.println(search.get());
		}
		return "admin/member";
	}
	
	
	// 관리자 회원 삭제
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String member_delete(
			@RequestParam(name = "id", required = true, defaultValue = "-1") String id,
			@AuthUser MemberVo authUser,
			Model model
			) {
		
		// 회원 삭제요청
		ResponseJSONResult<Boolean> rJson = memberService.delete(authUser.getMockToken(), id);
		
        // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
		
		return "redirect:/admin/member";
	}
	
	
	
}
