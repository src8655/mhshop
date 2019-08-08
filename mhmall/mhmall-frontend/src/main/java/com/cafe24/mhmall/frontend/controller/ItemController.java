package com.cafe24.mhmall.frontend.controller;

import java.net.URI;
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

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.security.AuthUser;
import com.cafe24.mhmall.frontend.service.CategoryService;
import com.cafe24.mhmall.frontend.service.ItemImgService;
import com.cafe24.mhmall.frontend.service.ItemService;
import com.cafe24.mhmall.frontend.service.OptionService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.ItemVo;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	OptionService optionService;
	
	@Autowired
	ItemImgService itemImgService;
	

	// 상품상세
	@RequestMapping("/view/{no}")
	public String item_view(
			@PathVariable("no") Long no,
			Model model
			) {
		
		// 카테고리 리스트 요청
		ResponseJSONResult<CategoryService.ListCategoryVo> rJsonCategory = categoryService.getList();
		model.addAttribute("categoryList", rJsonCategory.getData());
		
		
		// 상품정보 요청
		ResponseJSONResult<ItemVo> rJsonItem = itemService.get(no);
		model.addAttribute("itemVo", rJsonItem.getData());
		
		// 옵션리스트(1차만)
		Optional<Long> optionDetailNo1 = Optional.empty();
		ResponseJSONResult<OptionService.ListOptionVo> rJsonOption = optionService.getList(no, optionDetailNo1);
		model.addAttribute("optionList", rJsonOption.getData());
		
		
		// 상품이미지리스트 요청
		ResponseJSONResult<ItemImgService.ListItemImgVo> rJsonItemImg = itemImgService.getList(no);
		model.addAttribute("itemImgList", rJsonItemImg.getData());
		
			
		return "item/view";
	}
	
}
