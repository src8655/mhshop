package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.OptionDetailVo;
import com.cafe24.mhshop.vo.OptionVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	public JSONResult list() {
		
		// 권한 확인
		
		

		// CategoryService에서 카테고리 리스트 요청
		
		
		// Service에 상품리스트 요청
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "admin/item_list");
		return JSONResult.success(dataMap);
	}
	
	
	

	@RequestMapping(value = "/write_form", method = RequestMethod.GET)
	@ApiOperation(value = "[관리자 상품 작성 페이지]", notes = "관리자 상품 작성 페이지 API")
	public JSONResult write_form() {
		
		// 권한 확인
		
		
		// CategoryService에서 카테고리 리스트 요청
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "admin/item_write_form");
		return JSONResult.success(dataMap);
	}
	
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "상품명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "description", value = "상품설명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "money", value = "가격", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "thumbnail", value = "썸네일", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "categoryNo", value = "카테고리번호", paramType = "query", required = true, defaultValue = ""),
		
		@ApiImplicitParam(name = "no", value = "", paramType = "", required = false, defaultValue = ""),
		@ApiImplicitParam(name = "display", value = "", paramType = "", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 DB에 저장", notes = "관리자 상품 DB에 저장 API")
	public JSONResult write(
			@ModelAttribute ItemVo itemVo
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		
		
		// Service에 등록
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/item_list");
		return JSONResult.success(dataMap);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상품번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 DB에 삭제", notes = "관리자 상품 DB에 삭제 API")
	public JSONResult delete(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		// @ModelAttribute로 처리
		ItemVo ivo = new ItemVo();
		ivo.setNo(no);
		
		
		// ItemImgService 에서 이미지 삭제 요청
		
		
		// Service에 삭제 요청
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/list");
		return JSONResult.success(dataMap);
	}
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상품번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/edit_form", method = RequestMethod.GET)
	@ApiOperation(value = "[관리자 상품 수정 페이지]", notes = "관리자 상품 수정 페이지 API")
	public JSONResult edit_form(
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
		
				
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("forward", "admin/item_edit_form");
		return JSONResult.success(dataMap);
	}
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상품번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "name", value = "상품명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "description", value = "상품설명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "money", value = "가격", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "thumbnail", value = "썸네일", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "categoryNo", value = "카테고리번호", paramType = "query", required = true, defaultValue = ""),
		
		@ApiImplicitParam(name = "display", value = "", paramType = "", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 상품 DB에 수정", notes = "관리자 상품 DB에 수정 API")
	public JSONResult edit(
			@ModelAttribute ItemVo itemVo
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		

		// Service에 상품 정보 수정
				
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/edit");
		return JSONResult.success(dataMap);
	}
	
	
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상품번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "display", value = "상품진열여부", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/edit_display", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 상품 진열여부 DB에 수정", notes = "관리자 상품 진열여부 DB에 수정 API")
	public JSONResult edit_display(
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
				
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/edit");
		return JSONResult.success(dataMap);
	}
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "itemImg", value = "상품이미지", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/additemimg", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 이미지를 DB에 저장", notes = "관리자 상품 이미지를 DB에 저장 API")
	public JSONResult additemimg(
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
				
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/edit");
		return JSONResult.success(dataMap);
	}
	
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상품이미지번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/deleteitemimg", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 이미지를 DB에서 삭제", notes = "관리자 상품 이미지를 DB에서 삭제 API")
	public JSONResult deleteitemimg(
			@RequestParam(value = "no", required = true, defaultValue = "-1") Long no
			) {
		
		// 권한 확인
		
		
		

		// 유효성검사
		
		
		
		
		// @ModelAttribute로 처리
		ItemImgVo iivo = new ItemImgVo();
		iivo.setNo(no);
		
		

		// ItemImgService에 상품아이템 삭제 요청
				
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/list");
		return JSONResult.success(dataMap);
	}
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "optionName", value = "옵션상세명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "level", value = "옵션상세레벨", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "query", required = true, defaultValue = ""),
		
		@ApiImplicitParam(name = "no", value = "", paramType = "", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/addoptiondetail", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 상세옵션 추가", notes = "관리자 상품 상세옵션 추가 API")
	public JSONResult addoptiondetail(
			@ModelAttribute OptionDetailVo optionDetailVo
			) {
		
		// 권한 확인
		
		
		// 유효성검사
		
		

		// OptionDetailService에 상품상세옵션 추가 요청
				
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/edit");
		return JSONResult.success(dataMap);
	}
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상세옵션번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/deleteoptiondetail", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 상세옵션 삭제", notes = "관리자 상품 상세옵션 삭제 API")
	public JSONResult deleteoptiondetail(
			@RequestParam(value = "no", required = true, defaultValue = "") Long no
			) {
		
		// 권한 확인
		
		
		// 유효성검사
		
		
		// OptionService 에서 상세옵션번호를 가지는 옵션이 있는지 확인 요청
		

		// OptionDetailService에 상품상세옵션 추가 요청
		
				
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/edit");
		return JSONResult.success(dataMap);
	}
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "optionDetail1", value = "1차상세옵션번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "optionDetail2", value = "2차상세옵션번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "cnt", value = "재고", paramType = "query", required = true, defaultValue = ""),
		
		@ApiImplicitParam(name = "no", value = "", paramType = "", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/addoption", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 옵션 추가", notes = "관리자 상품 옵션 추가 API")
	public JSONResult addoption(
			@ModelAttribute OptionVo optionVo
			) {
		
		// 권한 확인
		
		
		// 유효성검사
		
		

		// OptionService에 상품옵션 추가 요청
		
				
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/edit");
		return JSONResult.success(dataMap);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "옵션번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/deleteoption", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 옵션 삭제", notes = "관리자 상품 옵션 삭제 API")
	public JSONResult addoption(
			@RequestParam(value = "no", required = true, defaultValue = "") Long no
			) {
		
		// 권한 확인
		
		
		// 유효성검사
		
		

		// OptionService에 상품옵션 삭제 요청
		
				
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("redirect", "/api/adminitem/edit");
		return JSONResult.success(dataMap);
	}

}
