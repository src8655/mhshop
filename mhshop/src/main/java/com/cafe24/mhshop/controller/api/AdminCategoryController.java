package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.dto.RequestCategoryEditDto;
import com.cafe24.mhshop.dto.RequestCategoryWriteDto;
import com.cafe24.mhshop.dto.RequestNoDto;
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
@RequestMapping("/api/admin/category")
@Api(value = "AdminCategoryController", description = "관리자 카테고리 컨트롤러")
public class AdminCategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ItemService itemService;
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "카테고리명", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 카테고리를 DB에 등록", notes = "관리자 카테고리 등록 API")
	public ResponseEntity<JSONResult> write(
			@ModelAttribute @Valid RequestCategoryWriteDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 등록 요청
		boolean isSuccess = categoryService.add(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 카테고리 리스트", notes = "관리자 카테고리 리스트 요청 API")
	public ResponseEntity<JSONResult> list() {
		// Service에 카테고리리스트 요청
		List<CategoryVo> list = categoryService.getList();
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list));
	}
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "카테고리번호", paramType = "path", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "name", value = "카테고리명", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/{no}/{name}", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 카테고리를 DB에서 수정", notes = "관리자 카테고리 수정 API")
	public ResponseEntity<JSONResult> edit(
			@ModelAttribute @Valid RequestCategoryEditDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 수정 요청
		boolean isSuccess = categoryService.edit(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "카테고리번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/{no}", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 카테고리를 DB에서 삭제", notes = "관리자 카테고리 삭제 API")
	public ResponseEntity<JSONResult> delete(
			@ModelAttribute @Valid RequestNoDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// @ModelAttribute로 처리
		CategoryVo cvo = new CategoryVo();
		cvo.setNo(dto.getNo());
		
		// ItemService 에서 제품이 있는지 확인요청(없어야 삭제)
		boolean hasItem = itemService.hasItemByCategory(dto.getNo());
		if(hasItem) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("제품이 존재합니다."));
		
		// Service에 삭제 요청
		boolean isSuccess = categoryService.delete(dto.getNo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
}
