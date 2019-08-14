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
import com.cafe24.mhmall.frontend.security.SecurityUser;
import com.cafe24.mhmall.frontend.service.CategoryService;
import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.service.impl.MemberServiceImpl;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller

@RequestMapping("/admin/category")
public class AdminCategoryController {
	
	@Autowired
	CategoryService categoryService;

	
	// 관리자 카테고리목록
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String category(
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 카테고리 리스트 요청
		ResponseJSONResult<CategoryService.ListCategoryVo> rJson = categoryService.getList();
		
		model.addAttribute("categoryList", rJson.getData());
		model.addAttribute("cnt", 1);
		return "admin/category";
	}
	
	
	
	// 관리자 카테고리추가
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String category_add(
			@RequestParam(name = "categoryName", required = true, defaultValue = "") String categoryName,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 카테고리 추가요청
		ResponseJSONResult<Boolean> rJson = categoryService.add(authUser.getMockToken(), categoryName);
		
        // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
		
		return "redirect:/admin/category";
	}
	
	

	// 관리자 카테고리 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String category_delete(
			@RequestParam(name = "no", required = true, defaultValue = "-1") Long no,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 카테고리 삭제요청
		ResponseJSONResult<Boolean> rJson = categoryService.delete(authUser.getMockToken(), no);
		
        // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
		
		return "redirect:/admin/category";
	}
	
	
	


	// 관리자 카테고리 수정
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String category_edit(
			@RequestParam(name = "no", required = true, defaultValue = "-1") Long no,
			@RequestParam(name = "categoryName", required = true, defaultValue = "") String categoryName,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 카테고리 수정요청
		ResponseJSONResult<Boolean> rJson = categoryService.edit(authUser.getMockToken(), no, categoryName);
		
        // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
		
		return "redirect:/admin/category";
	}
}
