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
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.JSONResult;
import com.cafe24.mhmall.frontend.dto.RequestJoinDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.security.Auth;
import com.cafe24.mhmall.frontend.security.Auth.Role;
import com.cafe24.mhmall.frontend.security.AuthUser;
import com.cafe24.mhmall.frontend.security.SecurityUser;
import com.cafe24.mhmall.frontend.service.AdminCategoryService;
import com.cafe24.mhmall.frontend.service.AdminItemService;
import com.cafe24.mhmall.frontend.service.AdminMemberService;
import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.service.impl.MemberServiceImpl;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.ItemVo;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/admin/item")
public class AdminItemController {

	@Autowired
	AdminCategoryService adminCategoryService;
	
	@Autowired
	AdminItemService adminItemService;
	
	
	
	// 관리자 상품목록
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = {"", "/{categoryNo}"}, method = RequestMethod.GET)
	public String item(
			@PathVariable("categoryNo") Optional<Long> categoryNo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상품 리스트 요청
		ResponseJSONResult<AdminItemService.ListItemVo> rJson = adminItemService.getList(authUser.getMockToken(), categoryNo);
		model.addAttribute("itemList", rJson.getData());

		// 카테고리 리스트 요청
		ResponseJSONResult<AdminCategoryService.ListCategoryVo> rJsonCategory = adminCategoryService.getList();
		model.addAttribute("categoryList", rJsonCategory.getData());
		
		if(categoryNo.isPresent())
			model.addAttribute("categoryNos", categoryNo.get());
		
		return "admin/item";
	}
	
	

	// 관리자 상품작성
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String item_write_form(
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 카테고리 리스트 요청
		ResponseJSONResult<AdminCategoryService.ListCategoryVo> rJson = adminCategoryService.getList();
		
		model.addAttribute("categoryList", rJson.getData());
		return "admin/item_write";
	}
	

	// 관리자 상품작성 완료
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String item_write(
			@ModelAttribute ItemVo itemVo,
			@RequestParam(value="thumbnailFile") MultipartFile thumbnailFile,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상품작성 요청
		ResponseJSONResult<Boolean> rJson = adminItemService.add(authUser.getMockToken(), itemVo, thumbnailFile);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "작성 실패");
        	return "post/error";
        }
		
		return "redirect:/admin/item";
	}
	
}
