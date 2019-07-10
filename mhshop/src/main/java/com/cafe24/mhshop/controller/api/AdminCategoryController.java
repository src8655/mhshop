package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.security.Auth;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.vo.CategoryVo;
import com.cafe24.mhshop.vo.MemberVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("adminCategoryAPIController")
@RequestMapping("/api/admincategory")
@Api(value = "AdminCategoryController", description = "관리자 카테고리 컨트롤러")
public class AdminCategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ItemService itemService;
	
	
	@Auth(role=Auth.Role.ADMIN)
	@RequestMapping(value = "/write_form", method = RequestMethod.GET)
	@ApiOperation(value = "[관리자 카테고리 작성 페이지]", notes = "관리자 카테고리 작성 페이지 API")
	public JSONResult write_form() {
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "admin/category_write_form");
		return JSONResult.success(dataMap);
	}
	
	

	@Auth(role=Auth.Role.ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "", paramType = "", required = false, defaultValue = ""),
		@ApiImplicitParam(name = "name", value = "카테고리명", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 카테고리를 DB에 등록", notes = "관리자 카테고리 등록 API")
	public JSONResult write(
			@ModelAttribute @Valid CategoryVo categoryVo,
			BindingResult result
			) {
		
		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail("잘못된 입력 입니다.");
		
		// Service에 등록 요청
		CategoryVo newCategoryVo = categoryService.add(categoryVo);
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("categoryVo", newCategoryVo);
		dataMap.put("redirect", "/api/admincategory/category_list");
		return JSONResult.success(dataMap);
	}
	
	

	

	@Auth(role=Auth.Role.ADMIN)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 카테고리 리스트", notes = "관리자 카테고리 리스트 요청 API")
	public JSONResult list() {
		
		// Service에 카테고리리스트 요청
		List<CategoryVo> list = categoryService.get();
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("categoryList", list);
		dataMap.put("forward", "admin/category_list");
		return JSONResult.success(dataMap);
	}
	
	

	@Auth(role=Auth.Role.ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "카테고리번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "name", value = "카테고리명", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 카테고리를 DB에서 수정", notes = "관리자 카테고리 수정 API")
	public JSONResult edit(
			@ModelAttribute CategoryVo categoryVo,
			BindingResult result
			) {
		
		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail("잘못된 입력 입니다.");
		
		
		// Service에 수정 요청
		boolean isSuccess = categoryService.edit(categoryVo);
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admincategory/category_list");
		return JSONResult.success(dataMap);
	}
	
	
	

	@Auth(role=Auth.Role.ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "카테고리번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 카테고리를 DB에서 삭제", notes = "관리자 카테고리 삭제 API")
	public JSONResult delete(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no
			) {
		
		// 유효성검사
		
		
		// @ModelAttribute로 처리
		CategoryVo cvo = new CategoryVo();
		cvo.setNo(no);
		
		
		// ItemService 에서 제품이 있는지 확인요청(없어야 삭제)
		boolean hasItem = itemService.hasItemByCategory(no);
		if(hasItem) return JSONResult.fail("제품이 존재합니다.");
		
		
		// Service에 삭제 요청
		boolean isSuccess = categoryService.delete(no);
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admincategory/category_list");
		return JSONResult.success(dataMap);
	}
}
