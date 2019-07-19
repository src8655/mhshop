package com.cafe24.mhshop.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.dto.RequestItemNoDto;
import com.cafe24.mhshop.dto.RequestNoDto;
import com.cafe24.mhshop.dto.RequestOptionListDto;
import com.cafe24.mhshop.security.Auth;
import com.cafe24.mhshop.security.Auth.Role;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.service.OptionDetailService;
import com.cafe24.mhshop.service.OptionService;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.OptionVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("itemAPIController")
@RequestMapping("/api/item")
@Api(value = "ItemController", description = "상품 컨트롤러")
public class ItemController {
	
	@Autowired
	ItemService itemService;

	@Autowired
	ItemImgService itemImgService;
	
	@Autowired
	OptionService optionService;
	
	
	
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "상품번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	@ApiOperation(value = "상품 상세", notes = "상품 상세 API")
	public ResponseEntity<JSONResult> itemview(
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
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/img/{itemNo}", method = RequestMethod.GET)
	@ApiOperation(value = "상품이미지 리스트", notes = " 상품이미지 리스트 API")
	public ResponseEntity<JSONResult> itemimglist(
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
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "path", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "optionDetailNo1", value = "1차상세옵션번호", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/option/{itemNo}", method = RequestMethod.GET)
	@ApiOperation(value = "옵션 리스트", notes = "옵션 리스트 API")
	public ResponseEntity<JSONResult> itemoptionList(
			@ModelAttribute @Valid RequestOptionListDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// OptionService 에서 옵션 리스트 요청
		List<OptionVo> optionList = optionService.getListByItemNo(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(optionList));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "사용자 상품 리스트", notes = "사용자 상품 리스트 요청 API")
	public ResponseEntity<JSONResult> itemlist() {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "no", value = "옵션번호", paramType = "path", required = true, defaultValue = ""),
	})
	@RequestMapping(value = "/option/{no}", method = RequestMethod.GET)
	@ApiOperation(value = "옵션 상세", notes = "옵션 상세 요청 API")
	public ResponseEntity<JSONResult> itemoptionView(
			@ModelAttribute @Valid RequestNoDto dto,
			BindingResult result
			) {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null));
	}

}
