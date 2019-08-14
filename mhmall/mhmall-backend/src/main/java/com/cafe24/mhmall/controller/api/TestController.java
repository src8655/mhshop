package com.cafe24.mhmall.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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

@RestController("testAPIController")
@RequestMapping("/api/test")
@Api(value = "TestController", description = "테스트 컨트롤러")
public class TestController {

	@Autowired
	CategoryService categoryService;
	
	
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	@ApiOperation(value = "테스트 리스트", notes = "테스트요청 API")
	public ResponseEntity<JSONResult> test() {
		
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("hello"));
	}
}
