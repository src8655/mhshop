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
import com.cafe24.mhmall.frontend.service.AdminService;
import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.service.impl.MemberServiceImpl;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;

	// 관리자 메인
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping({"", "/"})
	public String admin() {
		
		return "admin/index";
	}
	
	

	// 관리자 회원목록
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = {"/member", "/member/{search}"}, method = RequestMethod.GET)
	public String member(
			@PathVariable("search") Optional<String> search,
			@AuthUser MemberVo authUser,
			Model model
			) {
		
		// 관리자 회원목록
		ResponseJSONResult<AdminService.ListMemberVo> rJson = adminService.getMemberList(authUser.getMockToken(), search);
		
		model.addAttribute("memberList", rJson.getData());
		if(search.isPresent()) {
			model.addAttribute("searchs", search.get());
			System.out.println(search.get());
		}
		return "admin/member";
	}
}
