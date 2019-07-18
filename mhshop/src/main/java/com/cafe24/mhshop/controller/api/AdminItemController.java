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
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.dto.RequestItemDisplayDto;
import com.cafe24.mhshop.dto.RequestItemEditDto;
import com.cafe24.mhshop.dto.RequestItemImgWriteDto;
import com.cafe24.mhshop.dto.RequestItemNoDto;
import com.cafe24.mhshop.dto.RequestItemWriteDto;
import com.cafe24.mhshop.dto.RequestNoDto;
import com.cafe24.mhshop.dto.RequestOptionDetailViewDto;
import com.cafe24.mhshop.dto.RequestOptionDetailWriteDto;
import com.cafe24.mhshop.dto.RequestOptionWriteDto;
import com.cafe24.mhshop.security.Auth;
import com.cafe24.mhshop.security.Auth.Role;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.service.OptionDetailService;
import com.cafe24.mhshop.service.OptionService;
import com.cafe24.mhshop.vo.CategoryVo;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.OptionDetailVo;
import com.cafe24.mhshop.vo.OptionVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("adminItemAPIController")
@RequestMapping("/api/admin/item")
@Api(value = "AdminItemController", description = "관리자 상품 컨트롤러")
public class AdminItemController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ItemService itemService;

	@Autowired
	ItemImgService itemImgService;
	
	@Autowired
	OptionDetailService optionDetailService;
	
	@Autowired
	OptionService optionService;
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 상품 리스트", notes = "관리자 상품 리스트 요청 API")
	public ResponseEntity<JSONResult> list() {
		
		// Service에 상품리스트 요청
		List<ItemVo> itemList = itemService.getList();
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(itemList));
	}
	
	

	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "name", value = "상품명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "description", value = "상품설명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "money", value = "가격", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "thumbnail", value = "썸네일", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "categoryNo", value = "카테고리번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 DB에 저장", notes = "관리자 상품 DB에 저장 API")
	public ResponseEntity<JSONResult> write(
			@ModelAttribute @Valid RequestItemWriteDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 존재하는 카테고리인지 확인
		if(!categoryService.isExistByNo(dto.toVo().getCategoryNo())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("존재하지 않는 카테고리 입니다."));
		
		// Service에 등록
		boolean isSuccess = itemService.add(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "no", value = "상품번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/{no}", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 DB에 삭제", notes = "관리자 상품 DB에 삭제 API")
	public ResponseEntity<JSONResult> delete(
			@ModelAttribute @Valid RequestNoDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 삭제 요청
		boolean isSuccess = itemService.delete(dto.getNo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "no", value = "상품번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 상품 상세", notes = "관리자 상품 상세 API")
	public ResponseEntity<JSONResult> view(
			@ModelAttribute @Valid RequestNoDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// ItemService에 상품 정보 요청
		ItemVo itemVo = itemService.getByNo(dto.getNo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(itemVo));
	}
	
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/img/{itemNo}", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 상품이미지 리스트", notes = "관리자  상품이미지 리스트 API")
	public ResponseEntity<JSONResult> imglist(
			@ModelAttribute @Valid RequestItemNoDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
				
		// ItemImgService 에서 이미지 리스트 요청
		List<ItemImgVo> itemImgList = itemImgService.getListByItemNo(dto.getItemNo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(itemImgList));
	}
	
	
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = ""),
		
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "path", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "level", value = "옵션레벨", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/optiondetail/{itemNo}", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 옵션상세 리스트", notes = "관리자 옵션상세 리스트 API")
	public ResponseEntity<JSONResult> optionDetailList(
			@ModelAttribute @Valid RequestOptionDetailViewDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// OptionDetailService 에서 상세옵션 리스트 요청
		List<OptionDetailVo> optionDetailList = optionDetailService.getListByItemNoAndLevel(dto.getItemNo(), dto.getLevel());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(optionDetailList));
	}
	
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/option/{itemNo}", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 옵션 리스트", notes = "관리자 옵션 리스트 API")
	public ResponseEntity<JSONResult> optionList(
			@ModelAttribute @Valid RequestItemNoDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// OptionService 에서 옵션 리스트 요청
		List<OptionVo> optionList = optionService.getListByItemNo(dto.getItemNo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(optionList));
	}
	
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상품번호", paramType = "path", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "name", value = "상품명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "description", value = "상품설명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "money", value = "가격", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "thumbnail", value = "썸네일", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "categoryNo", value = "카테고리번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/{no}", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 상품 DB에 수정", notes = "관리자 상품 DB에 수정 API")
	public JSONResult edit(
			@ModelAttribute @Valid RequestItemEditDto dto,
			BindingResult result
			) {
		
		// 권한 확인
		

		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage());
		
		

		// Service에 상품 정보 수정
		boolean isSuccess = itemService.edit(dto.toVo());
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/item/edit");
		return JSONResult.success(dataMap);
	}
	
	
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상품번호", paramType = "path", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "display", value = "상품진열여부", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/edit/display/{no}", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 상품 진열여부 DB에 수정", notes = "관리자 상품 진열여부 DB에 수정 API")
	public JSONResult edit_display(
			@ModelAttribute @Valid RequestItemDisplayDto dto,
			BindingResult result
			) {
		
		// 권한 확인
		

		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage());
		
		
		// TRUE로 바꾸고자 할 때 
		// OptionService에 상품옵션이 존재하는지 확인 (없으면 실패)
		if("TRUE".equals(dto.getDisplay())) {
			List<OptionVo> optionList = optionService.getListByItemNo(dto.getNo());
			if(optionList == null) return JSONResult.fail("옵션이 없습니다.");
		}
		

		// ItemService에 상품 정보 수정
		boolean isSuccess = itemService.editDisplay(dto.getNo(), dto.getDisplay());
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/item/edit");
		return JSONResult.success(dataMap);
	}
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "itemImg", value = "상품이미지", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/img", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 이미지를 DB에 저장", notes = "관리자 상품 이미지를 DB에 저장 API")
	public JSONResult additemimg(
			@ModelAttribute @Valid RequestItemImgWriteDto dto,
			BindingResult result
			) {
		
		// 권한 확인

		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage());
		
		
		// ItemImgService에 상품아이템 추가 요청
		boolean isSuccess = itemImgService.add(dto.toVo());
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/item/edit");
		return JSONResult.success(dataMap);
	}
	
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상품이미지번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/img/{no}", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 이미지를 DB에서 삭제", notes = "관리자 상품 이미지를 DB에서 삭제 API")
	public JSONResult deleteitemimg(
			@ModelAttribute @Valid RequestNoDto dto,
			BindingResult result
			) {
		
		// 권한 확인

		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage());

		// ItemImgService에 상품아이템 삭제 요청
		boolean isSuccess = itemImgService.delete(dto.getNo());
				
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/item/edit");
		return JSONResult.success(dataMap);
	}
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "optionName", value = "옵션상세명", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "level", value = "옵션상세레벨", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/optiondetail", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 상세옵션 추가", notes = "관리자 상품 상세옵션 추가 API")
	public JSONResult addoptiondetail(
			@ModelAttribute @Valid RequestOptionDetailWriteDto dto,
			BindingResult result
			) {
		
		// 권한 확인
		

		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage());
		

		// OptionDetailService에 상품상세옵션 추가 요청
		boolean isSuccess = optionDetailService.add(dto.toVo());
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/item/edit");
		return JSONResult.success(dataMap);
	}
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상세옵션번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/optiondetail/{no}", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 상세옵션 삭제", notes = "관리자 상품 상세옵션 삭제 API")
	public JSONResult deleteoptiondetail(
			@ModelAttribute @Valid RequestNoDto dto,
			BindingResult result
			) {
		
		// 권한 확인


		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage());
		
		// OptionService 에서 상세옵션번호를 가지는 옵션이 있는지 확인 요청
		if(optionService.hasOptionDetailNo(dto.getNo())) return JSONResult.fail("상세옵션번호를 옵션이 사용중입니다.");
		

		// OptionDetailService에 상품상세옵션 삭제 요청
		boolean isSuccess = optionDetailService.delete(dto.getNo());
				
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/item/edit");
		return JSONResult.success(dataMap);
	}
	
	
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "query", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "optionDetailNo1", value = "1차상세옵션번호", paramType = "query", required = false, defaultValue = ""),
		@ApiImplicitParam(name = "optionDetailNo2", value = "2차상세옵션번호", paramType = "query", required = false, defaultValue = ""),
		@ApiImplicitParam(name = "cnt", value = "재고", paramType = "query", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/option", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 옵션 추가", notes = "관리자 상품 옵션 추가 API")
	public JSONResult addoption(
			@ModelAttribute @Valid RequestOptionWriteDto dto,
			BindingResult result
			) {
		
		// 권한 확인
		

		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage());
		

		// OptionService에 상품옵션 추가 요청
		boolean isSuccess = optionService.add(dto.toVo());
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/item/edit");
		return JSONResult.success(dataMap);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "옵션번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/option/{no}", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 옵션 삭제", notes = "관리자 상품 옵션 삭제 API")
	public JSONResult addoption(
			@ModelAttribute @Valid RequestNoDto dto,
			BindingResult result
			) {
		
		// 권한 확인

		// 유효성검사
		if(result.hasErrors()) return JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage());

		// OptionService에 상품옵션 삭제 요청
		boolean isSuccess = optionService.delete(dto.getNo());
				
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/item/edit");
		return JSONResult.success(dataMap);
	}

}
