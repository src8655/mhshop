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
import com.cafe24.mhmall.frontend.service.CategoryService;
import com.cafe24.mhmall.frontend.service.ItemImgService;
import com.cafe24.mhmall.frontend.service.ItemService;
import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.service.OptionDetailService;
import com.cafe24.mhmall.frontend.service.OptionService;
import com.cafe24.mhmall.frontend.service.OptionDetailService.ListOptionDetailVo;
import com.cafe24.mhmall.frontend.service.impl.MemberServiceImpl;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.ItemVo;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.cafe24.mhmall.frontend.vo.OptionDetailVo;
import com.cafe24.mhmall.frontend.vo.OptionVo;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/admin/item")
public class AdminItemController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ItemService itemService;

	@Autowired
	OptionDetailService optionDetailService;
	
	@Autowired
	OptionService optionService;
	
	@Autowired
	ItemImgService itemImgService;
	
	
	
	// 관리자 상품목록
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = {"", "/{categoryNo}"}, method = RequestMethod.GET)
	public String item(
			@PathVariable("categoryNo") Optional<Long> categoryNo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상품 리스트 요청
		ResponseJSONResult<ItemService.ListItemVo> rJson = itemService.getList(authUser.getMockToken(), categoryNo);
		model.addAttribute("itemList", rJson.getData());

		// 카테고리 리스트 요청
		ResponseJSONResult<CategoryService.ListCategoryVo> rJsonCategory = categoryService.getList();
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
		ResponseJSONResult<CategoryService.ListCategoryVo> rJson = categoryService.getList();
		
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
		ResponseJSONResult<Boolean> rJson = itemService.add(authUser.getMockToken(), itemVo, thumbnailFile);
		
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
	
	
	
	
	// 관리자 상품수정
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = "/edit/{no}", method = RequestMethod.GET)
	public String item_edit_form(
			@PathVariable("no") Long no,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 카테고리 리스트 요청
		ResponseJSONResult<CategoryService.ListCategoryVo> rJsonCategory = categoryService.getList();
		model.addAttribute("categoryList", rJsonCategory.getData());
		
		
		// 상품정보 요청
		ResponseJSONResult<ItemVo> rJsonItem = itemService.get(no);
		model.addAttribute("itemVo", rJsonItem.getData());
		
		
		// 1차 상세옵션 리스트
		ResponseJSONResult<OptionDetailService.ListOptionDetailVo> rJsonOptionDetail1 = optionDetailService.getOptionDetail(authUser.getMockToken(), rJsonItem.getData().getNo(), 1);
		model.addAttribute("optionDetailList1", rJsonOptionDetail1.getData());
		
		
		// 2차 상세옵션 리스트
		ResponseJSONResult<OptionDetailService.ListOptionDetailVo> rJsonOptionDetail2 = optionDetailService.getOptionDetail(authUser.getMockToken(), rJsonItem.getData().getNo(), 2);
		model.addAttribute("optionDetailList2", rJsonOptionDetail2.getData());
		
		
		// 옵션리스트(1차만)
		Optional<Long> optionDetailNo1 = Optional.empty();
		ResponseJSONResult<OptionService.ListOptionVo> rJsonOption = optionService.getList(no, optionDetailNo1);
		model.addAttribute("optionList", rJsonOption.getData());
		
		
		// 상품이미지리스트 요청
		ResponseJSONResult<ItemImgService.ListItemImgVo> rJsonItemImg = itemImgService.getList(no);
		model.addAttribute("itemImgList", rJsonItemImg.getData());
		
		
		return "admin/item_edit";
	}
	
	
	
	

	// 관리자 상세옵션 추가
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = "/optiondetail", method = RequestMethod.POST)
	public String optionDetailAdd(
			@ModelAttribute OptionDetailVo optionDetailVo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상세옵션 추가
		ResponseJSONResult<Boolean> rJson = optionDetailService.add(authUser.getMockToken(), optionDetailVo);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "추가 실패");
        	return "post/error";
        }
		
		return "redirect:/admin/item/edit/" + optionDetailVo.getItemNo();
	}
	
	

	// 관리자 옵션 추가
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = "/option", method = RequestMethod.POST)
	public String optionAdd(
			@ModelAttribute OptionVo optionVo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 옵션 추가
		ResponseJSONResult<Boolean> rJson = optionService.add(authUser.getMockToken(), optionVo);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "추가 실패");
        	return "post/error";
        }
		
		return "redirect:/admin/item/edit/" + optionVo.getItemNo();
	}
	
	
	
	// 관리자 옵션 리스트(1차 없으면 1차 있으면 2차)
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

	
	

	// 관리자 상세옵션 삭제
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = "/optiondetail/delete", method = RequestMethod.POST)
	public String optionDetailDelete(
			@RequestParam("itemNo") Long itemNo,
			@RequestParam("no") Long no,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상세옵션 삭제 요청
		ResponseJSONResult<Boolean> rJson = optionDetailService.delete(authUser.getMockToken(), no);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "삭제 실패");
        	return "post/error";
        }
		
		return "redirect:/admin/item/edit/" + itemNo;
	}
	
	

	// 관리자 옵션 삭제
	@Auth(role = Role.ROLE_ADMIN)
	@RequestMapping(value = "/option/delete", method = RequestMethod.POST)
	public String optionDelete(
			@RequestParam("itemNo") Long itemNo,
			@RequestParam("no") Long no,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 옵션 삭제 요청
		ResponseJSONResult<Boolean> rJson = optionService.delete(authUser.getMockToken(), no);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "삭제 실패");
        	return "post/error";
        }
		
		return "redirect:/admin/item/edit/" + itemNo;
	}
	
	
	

	// 관리자 상품이미지 추가
	@RequestMapping(value = "/img/write", method = RequestMethod.POST)
	public String itemImgAdd(
			@RequestParam("itemNo") Long itemNo,
			@RequestParam(value="itemImgFile") MultipartFile itemImgFile,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상품이미지 추가 요청
		ResponseJSONResult<Boolean> rJson = itemImgService.add(authUser.getMockToken(), itemNo, itemImgFile);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "추가 실패");
        	return "post/error";
        }

		return "redirect:/admin/item/edit/" + itemNo;
	}
	
	

	// 관리자 상품이미지 삭제
	@RequestMapping(value = "/img/delete", method = RequestMethod.POST)
	public String itemImgDelete(
			@RequestParam("no") Long no,
			@RequestParam("itemNo") Long itemNo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상품이미지 삭제 요청
		ResponseJSONResult<Boolean> rJson = itemImgService.delete(authUser.getMockToken(), no);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "삭제 실패");
        	return "post/error";
        }
		
		return "redirect:/admin/item/edit/" + itemNo;
	}
	

	// 관리자 상품수정 완료
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String item_edit(
			@ModelAttribute ItemVo itemVo,
			@RequestParam(value="thumbnailFile") MultipartFile thumbnailFile,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상품수정 요청
		ResponseJSONResult<Boolean> rJson = itemService.edit(authUser.getMockToken(), itemVo, thumbnailFile);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "수정 실패");
        	return "post/error";
        }

		return "redirect:/admin/item/edit/" + itemVo.getNo();
	}
	
	
	
	// 관리자 상품삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String item_delete(
			@RequestParam("no") Long no,
			@RequestParam(name = "categoryNo", required = true, defaultValue = "-1") Long categoryNo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상품삭제 요청
		ResponseJSONResult<Boolean> rJson = itemService.delete(authUser.getMockToken(), no);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "삭제 실패");
        	return "post/error";
        }

		return "redirect:/admin/item/" + categoryNo;
	}
	
	

	// 관리자 상품 진열여부 변경
	@RequestMapping(value = "/display", method = RequestMethod.POST)
	public String item_display(
			@RequestParam("no") Long no,
			@RequestParam("display") String display,
			@RequestParam(name = "categoryNo", required = true, defaultValue = "-1") Long categoryNo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 상품 진열여부 수정 요청
		ResponseJSONResult<Boolean> rJson = itemService.display(authUser.getMockToken(), no, display);
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "판매여부 수정 실패");
        	return "post/error";
        }

		return "redirect:/admin/item/" + categoryNo;
	}
	
}
