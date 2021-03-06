package com.cafe24.mhmall.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhmall.dto.JSONResult;
import com.cafe24.mhmall.dto.RequestCategoryEditDto;
import com.cafe24.mhmall.dto.RequestCategoryWriteDto;
import com.cafe24.mhmall.dto.RequestNoDto;
import com.cafe24.mhmall.security.Auth;
import com.cafe24.mhmall.security.Auth.Role;
import com.cafe24.mhmall.service.CategoryService;
import com.cafe24.mhmall.service.ItemService;
import com.cafe24.mhmall.vo.CategoryVo;
import com.cafe24.mhmall.vo.MemberVo;

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
	
	
	
	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = "")
	})
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 카테고리를 DB에 등록", notes = "관리자 카테고리 등록 API")
	public ResponseEntity<JSONResult> write(
			@RequestBody @Valid RequestCategoryWriteDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 등록 요청
		boolean isSuccess = categoryService.add(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = "")
	})
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 카테고리를 DB에서 수정", notes = "관리자 카테고리 수정 API")
	public ResponseEntity<JSONResult> edit(
			@RequestBody @Valid RequestCategoryEditDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 수정 요청
		boolean isSuccess = categoryService.edit(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	
	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = "")
	})
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 카테고리를 DB에서 삭제", notes = "관리자 카테고리 삭제 API")
	public ResponseEntity<JSONResult> delete(
			@RequestBody @Valid RequestNoDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		
		// ItemService 에서 제품이 있는지 확인요청(없어야 삭제)
		boolean hasItem = itemService.hasItemByCategory(dto.getNo());
		if(hasItem) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("상품이 존재합니다."));
		
		// Service에 삭제 요청
		boolean isSuccess = categoryService.delete(dto.getNo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
}
