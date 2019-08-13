package com.cafe24.mhmall.frontend.controller;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.JSONResult;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.security.AuthUser;
import com.cafe24.mhmall.frontend.security.SecurityUser;
import com.cafe24.mhmall.frontend.service.BasketService;
import com.cafe24.mhmall.frontend.service.CategoryService;
import com.cafe24.mhmall.frontend.service.ItemImgService;
import com.cafe24.mhmall.frontend.service.ItemService;
import com.cafe24.mhmall.frontend.service.OptionService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.ItemVo;
import com.cafe24.mhmall.frontend.vo.ItemsVo;
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
	
	@Autowired
	BasketService basketService;
	

	// 상품상세
	@RequestMapping(value = {"/view/{no}","/view/{no}/{kwd}"})
	public String item_view(
			@PathVariable("no") Long no,
			@PathVariable Optional<String> kwd,
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
		

		model.addAttribute("categoryNoPath", rJsonItem.getData().getCategoryNo());
		if(kwd.isPresent()) model.addAttribute("kwdPath", kwd.get());
		else model.addAttribute("kwdPath", "");
		
		return "item/view";
	}
	
	
	// 옵션 리스트(1차 없으면 1차 있으면 2차)
	@ResponseBody
	@RequestMapping(value = "/option/{itemNo}/{optionDetailNo1}", method = RequestMethod.GET)
	public JSONResult optionList(
			@PathVariable("itemNo") Long itemNo,
			@PathVariable("optionDetailNo1") Optional<Long> optionDetailNo1,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 옵션 리스트(1차 없으면 1차 있으면 2차)
		ResponseJSONResult<OptionService.ListOptionVo> rJson = optionService.getList(itemNo, optionDetailNo1);
		
		
		return JSONResult.success(rJson.getData());
	}
	
	
	// 장바구니 추가
	@RequestMapping(value = "/basket", method = RequestMethod.POST)
	public String basketAdd(
			@RequestParam("itemNo") Long itemNo,
			@RequestParam("optionNos") Long[] optionNos,
			@RequestParam("optionCnts") Long[] optionCnts,
			@CookieValue(name = "GuestSession", required = true, defaultValue = "") String guestSession,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		ResponseJSONResult<Boolean> rJson = ResponseJSONResult.fail("실패");
		// 비회원일 때 비회원장바구니 추가 요청
		if(authUser == null) rJson = basketService.guestAdd(optionNos, optionCnts, guestSession);
		// 회원일 때 회원장바구니 추가 요청
		else rJson = basketService.memberAdd(authUser.getMockToken(), optionNos, optionCnts);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "추가 실패");
        	return "post/error";
        }
        
		return "redirect:/item/basket";
	}
	
	

	// 장바구니 리스트
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String basket(
			@CookieValue(name = "GuestSession", required = true, defaultValue = "") String guestSession,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		System.out.println(guestSession + "------basket");

		// 카테고리 리스트 요청
		ResponseJSONResult<CategoryService.ListCategoryVo> rJsonCategory = categoryService.getList();
		model.addAttribute("categoryList", rJsonCategory.getData());
		
		
		ResponseJSONResult<BasketService.ListBasketVo> rJsonBasket = ResponseJSONResult.fail("실패");
		// 비회원일 때 비회원장바구니 리스트 요청
		if(authUser == null) rJsonBasket = basketService.guestList(guestSession);
		// 회원일 때 회원장바구니 리스트 요청
		else rJsonBasket = basketService.memberList(authUser.getMockToken());
		model.addAttribute("basketList", rJsonBasket.getData());
		
        
		return "item/basket";
	}
	
	

	// 장바구니 수정
	@RequestMapping(value = "/basket/update", method = RequestMethod.POST)
	public String basketUpdate(
			@RequestParam("no") Long no,
			@RequestParam("cnt") Long cnt,
			@CookieValue(name = "GuestSession", required = true, defaultValue = "") String guestSession,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		ResponseJSONResult<Boolean> rJson = ResponseJSONResult.fail("실패");
		// 비회원일 때 비회원장바구니 수정 요청
		if(authUser == null) rJson = basketService.guestUpdate(no, cnt, guestSession);
		// 회원일 때 회원장바구니 수정 요청
		else rJson = basketService.memberUpdate(authUser.getMockToken(), no, cnt);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "수정 실패");
        	return "post/error";
        }
        
		return "redirect:/item/basket";
	}
	
	
	
	

	// 장바구니 삭제
	@RequestMapping(value = "/basket/delete", method = RequestMethod.POST)
	public String basketDelete(
			@RequestParam("no") Long no,
			@CookieValue(name = "GuestSession", required = true, defaultValue = "") String guestSession,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		ResponseJSONResult<Boolean> rJson = ResponseJSONResult.fail("실패");
		// 비회원일 때 비회원장바구니 삭제 요청
		if(authUser == null) rJson = basketService.guestDelete(no, guestSession);
		// 회원일 때 회원장바구니 삭제 요청
		else rJson = basketService.memberDelete(authUser.getMockToken(), no);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "삭제 실패");
        	return "post/error";
        }
        
		return "redirect:/item/basket";
	}
	
	
	
	

	// 상품 리스트
	@RequestMapping(value = {"/list/{categoryNo}", "/list/{categoryNo}/{pages}", "/list/{categoryNo}/{pages}/{kwd}"}, method = RequestMethod.GET)
	public String itemList(
			@PathVariable Optional<Long> categoryNo,
			@PathVariable Optional<Integer> pages,
			@PathVariable Optional<String> kwd,
			Model model
			) {
		
		// 카테고리 리스트 요청
		ResponseJSONResult<CategoryService.ListCategoryVo> rJsonCategory = categoryService.getList();
		model.addAttribute("categoryList", rJsonCategory.getData());
		
		
		// 상품리스트와 페이징 가져오기
		ResponseJSONResult<ItemsVo> rJson = itemService.getListU(categoryNo, pages, kwd);
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
		
		model.addAttribute("itemList", rJson.getData().getItemList());
		model.addAttribute("paging", rJson.getData().getPaging());
		model.addAttribute("categoryNoPath", rJson.getData().getPaging().getCategoryNo());
		model.addAttribute("pagesPath", rJson.getData().getPaging().getPages());
		model.addAttribute("kwdPath", rJson.getData().getPaging().getKwd());
        
		
		return "item/list";
	}
	
}
