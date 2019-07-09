package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.vo.CategoryVo;
import com.cafe24.mhshop.vo.MemberVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("adminCategoryAPIController")
@RequestMapping("/api/admincategory")
@Api(value = "AdminCategoryController", description = "관리자 카테고리 컨트롤러")
public class AdminCategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 카테고리 리스트", notes = "관리자 카테고리 리스트 요청 API")
	public Map<String, Object> list() {
		
		// 권한 확인
		
		
		// Service에 카테고리리스트 요청
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", true);
		
		return map;
	}
	
	
	
	
	@RequestMapping(value = "/write_form", method = RequestMethod.GET)
	@ApiOperation(value = "[관리자 카테고리 작성 페이지]", notes = "관리자 카테고리 작성 페이지 API")
	@ResponseBody
	public String write_form() {
		
		// 권한 확인
		
		return "admin_cafegory_write_form.jsp";
	}
	
	
	
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 카테고리를 DB에 등록", notes = "관리자 카테고리 등록 API")
	public Map<String, Object> write(
			@RequestParam(value = "name", required = true, defaultValue = "") String name
			) {
		
		// 권한 확인
		
		
		// 유효성검사
		
		
		// @ModelAttribute로 처리
		CategoryVo cvo = new CategoryVo();
		cvo.setName(name);
		
		// Service에 등록 요청
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", cvo);
		
		return map;
	}
	
	
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 카테고리를 DB에서 수정", notes = "관리자 카테고리 수정 API")
	public Map<String, Object> edit(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no,
			@RequestParam(value = "name", required = true, defaultValue = "") String name
			) {
		
		// 권한 확인
		
		
		// 유효성검사
		
		
		// @ModelAttribute로 처리
		CategoryVo cvo = new CategoryVo();
		cvo.setName(name);
		cvo.setNo(no);
		
		// Service에 수정 요청
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", cvo);
		
		return map;
	}
	
	
	
	

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 카테고리를 DB에서 삭제", notes = "관리자 카테고리 삭제 API")
	public Map<String, Object> delete(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no
			) {
		
		// 권한 확인
		
		
		// 유효성검사
		
		
		// @ModelAttribute로 처리
		CategoryVo cvo = new CategoryVo();
		cvo.setNo(no);
		
		
		// ItemService 에서 아이템이 있는지 확인요청(없어야 삭제)
		
		
		
		// Service에 삭제 요청
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", cvo);
		
		return map;
	}
}
