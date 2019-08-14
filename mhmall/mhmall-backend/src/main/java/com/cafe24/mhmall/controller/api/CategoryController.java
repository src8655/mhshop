package com.cafe24.mhmall.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

@RestController("categoryAPIController")
@RequestMapping("/api/category")
@Api(value = "CategoryController", description = "카테고리 컨트롤러")
public class CategoryController {
	
	/*@Autowired
	RestTemplate restTemplate;*/

	@Autowired
	CategoryService categoryService;
	
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "카테고리 리스트", notes = "카테고리 리스트 요청 API")
	public ResponseEntity<JSONResult> list(@RequestHeader(value = "Authorization") String authorization) {
		
		// Service에 카테고리리스트 요청
		List<CategoryVo> list = categoryService.getList();
		
		
		
		
		// 테스트용 -----------------------------------------------------------------
		/*HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        
        HttpEntity body = new HttpEntity(null, headers);
        
        restTemplate.exchange("http://localhost:8888/mhmall/api/test", HttpMethod.GET, body, String.class);*/
        // ------------------------------------------------------------------------
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(list));
	}
}
