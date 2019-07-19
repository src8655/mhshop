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
import com.cafe24.mhshop.security.Auth.Role;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.vo.CategoryVo;
import com.cafe24.mhshop.vo.MemberVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("categoryAPIController")
@RequestMapping("/api/category")
@Api(value = "CategoryController", description = "카테고리 컨트롤러")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "카테고리 리스트", notes = "카테고리 리스트 요청 API")
	public ResponseEntity<JSONResult> list() {
		// Service에 카테고리리스트 요청
		List<CategoryVo> list = categoryService.getList();
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list));
	}
}
