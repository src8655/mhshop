package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("adminItemAPIController")
@RequestMapping("/api/adminitem")
@Api(value = "AdminItemController", description = "관리자 상품 컨트롤러")
public class AdminItemController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemImgService itemImgService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 상품 리스트", notes = "관리자 상품 리스트 요청 API")
	public Map<String, Object> list() {
		
		// 권한 확인
		
		

		// CategoryService에서 카테고리 리스트 요청
		
		
		// Service에 상품리스트 요청
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", true);
		
		return map;
	}
	
	
	

	@RequestMapping(value = "/write_form", method = RequestMethod.GET)
	@ApiOperation(value = "[관리자 상품 작성 페이지]", notes = "관리자 상품 작성 페이지 API")
	public Map<String, Object> write_form() {
		
		// 권한 확인
		
		
		// CategoryService에서 카테고리 리스트 요청
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", "admin_item/write_form");
		
		return map;
	}
	
	
	
	

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 DB에 저장", notes = "관리자 상품 DB에 저장 API")
	public Map<String, Object> write(
			@RequestParam(value = "name", required = true, defaultValue = "") String name,
			@RequestParam(value = "description", required = true, defaultValue = "") String description,
			@RequestParam(value = "money", required = true, defaultValue = "") String money,
			@RequestParam(value = "thumbnail", required = true, defaultValue = "") String thumbnail,
			@RequestParam(value = "categoryNo", required = true, defaultValue = "-1") Long categoryNo
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		// @ModelAttribute로 처리
		ItemVo ivo = new ItemVo();
		ivo.setName(name);
		ivo.setDescription(description);
		ivo.setMoney(money);
		ivo.setThumbnail(thumbnail);
		ivo.setCategoryNo(categoryNo);
		
		
		// Service에 등록
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", ivo);
		
		return map;
	}
	
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 DB에 삭제", notes = "관리자 상품 DB에 삭제 API")
	public Map<String, Object> delete(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		// @ModelAttribute로 처리
		ItemVo ivo = new ItemVo();
		ivo.setNo(no);
		
		
		// ItemImgService 에서 이미지 삭제 요청
		
		
		// Service에 삭제 요청
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", ivo);
		
		return map;
	}
	
	

	
	@RequestMapping(value = "/edit_form", method = RequestMethod.GET)
	@ApiOperation(value = "[관리자 상품 수정 페이지]", notes = "관리자 상품 수정 페이지 API")
	public Map<String, Object> edit_form(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		// @ModelAttribute로 처리
		ItemVo ivo = new ItemVo();
		ivo.setNo(no);
		
		

		// Service에 상품 정보 요청
		
		
		// CategoryService에서 카테고리 리스트 요청
		
		
		// ItemImgService 에서 이미지 리스트 요청
		
				
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", ivo);
		
		return map;
	}
	

	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 상품 DB에 수정", notes = "관리자 상품 DB에 수정 API")
	public Map<String, Object> edit(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no,
			@RequestParam(value = "name", required = true, defaultValue = "") String name,
			@RequestParam(value = "description", required = true, defaultValue = "") String description,
			@RequestParam(value = "money", required = true, defaultValue = "") String money,
			@RequestParam(value = "thumbnail", required = true, defaultValue = "") String thumbnail,
			@RequestParam(value = "categoryNo", required = true, defaultValue = "-1") Long categoryNo
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		// @ModelAttribute로 처리
		ItemVo ivo = new ItemVo();
		ivo.setName(name);
		ivo.setDescription(description);
		ivo.setMoney(money);
		ivo.setThumbnail(thumbnail);
		ivo.setCategoryNo(categoryNo);
		
		

		// Service에 상품 정보 수정
				
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", ivo);
		
		return map;
	}
	
	
	
	
	

	@RequestMapping(value = "/edit_display", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 상품 진열여부 DB에 수정", notes = "관리자 상품 진열여부 DB에 수정 API")
	public Map<String, Object> edit_display(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no,
			@RequestParam(value = "display", required = true, defaultValue = "") String display
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		// @ModelAttribute로 처리
		ItemVo ivo = new ItemVo();
		ivo.setNo(no);
		ivo.setDisplay(display);
		
		
		

		// Service에 상품 정보 수정
				
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", ivo);
		
		return map;
	}
	
	
	

	@RequestMapping(value = "/additemimg", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 이미지를 DB에 저장", notes = "관리자 상품 이미지를 DB에 저장 API")
	public Map<String, Object> additemimg(
			@RequestParam(value = "itemNo", required = true, defaultValue = "-1") Long itemNo,
			@RequestParam(value = "itemImg", required = true, defaultValue = "") String itemImg
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		
		
		// @ModelAttribute로 처리
		ItemImgVo iivo = new ItemImgVo();
		iivo.setItemNo(itemNo);
		iivo.setItemImg(itemImg);
		
		

		// ItemImgService에 상품아이템 추가 요청
				
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", iivo);
		
		return map;
	}
	
	
	
	

	@RequestMapping(value = "/deleteitemimg", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 이미지를 DB에서 삭제", notes = "관리자 상품 이미지를 DB에서 삭제 API")
	public Map<String, Object> deleteitemimg(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		
		
		// @ModelAttribute로 처리
		ItemImgVo iivo = new ItemImgVo();
		iivo.setNo(no);
		
		

		// ItemImgService에 상품아이템 삭제 요청
				
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("message", null);
		map.put("data", iivo);
		
		return map;
	}

}
