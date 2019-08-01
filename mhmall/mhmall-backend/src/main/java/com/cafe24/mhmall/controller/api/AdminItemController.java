package com.cafe24.mhmall.controller.api;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhmall.dto.JSONResult;
import com.cafe24.mhmall.dto.RequestItemCategoryDto;
import com.cafe24.mhmall.dto.RequestItemDisplayDto;
import com.cafe24.mhmall.dto.RequestItemEditDto;
import com.cafe24.mhmall.dto.RequestItemImgWriteDto;
import com.cafe24.mhmall.dto.RequestItemNoDto;
import com.cafe24.mhmall.dto.RequestItemWriteDto;
import com.cafe24.mhmall.dto.RequestNoDto;
import com.cafe24.mhmall.dto.RequestOptionDetailViewDto;
import com.cafe24.mhmall.dto.RequestOptionDetailWriteDto;
import com.cafe24.mhmall.dto.RequestOptionListDto;
import com.cafe24.mhmall.dto.RequestOptionWriteDto;
import com.cafe24.mhmall.security.Auth;
import com.cafe24.mhmall.security.Auth.Role;
import com.cafe24.mhmall.service.CategoryService;
import com.cafe24.mhmall.service.ItemImgService;
import com.cafe24.mhmall.service.ItemService;
import com.cafe24.mhmall.service.OptionDetailService;
import com.cafe24.mhmall.service.OptionService;
import com.cafe24.mhmall.vo.CategoryVo;
import com.cafe24.mhmall.vo.ItemImgVo;
import com.cafe24.mhmall.vo.ItemVo;
import com.cafe24.mhmall.vo.OptionDetailVo;
import com.cafe24.mhmall.vo.OptionVo;

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
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = ""),

		@ApiImplicitParam(name = "categoryNo", value = "카테고리번호", paramType = "path", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/list/{cateogryNo}", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 상품 리스트", notes = "관리자 상품 리스트 요청 API")
	public ResponseEntity<JSONResult> list(
			@ModelAttribute @Valid RequestItemCategoryDto dto,
			BindingResult result
			) {
		
		// Service에 상품리스트 요청
		List<ItemVo> itemList = itemService.getList(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(itemList));
	}
	
	

	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "authorization", value = "인증키", paramType = "header", required = false, defaultValue = "")
	})
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 DB에 저장", notes = "관리자 상품 DB에 저장 API")
	public ResponseEntity<JSONResult> write(
			@RequestBody @Valid RequestItemWriteDto dto,
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
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 DB에 삭제", notes = "관리자 상품 DB에 삭제 API")
	public ResponseEntity<JSONResult> delete(
			@RequestBody @Valid RequestNoDto dto,
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
		
		@ApiImplicitParam(name = "itemNo", value = "상품번호", paramType = "path", required = true, defaultValue = ""),
		@ApiImplicitParam(name = "level", value = "옵션레벨", paramType = "path", required = true, defaultValue = "")
	})
	@RequestMapping(value = "/optiondetail/{itemNo}/{level}", method = RequestMethod.GET)
	@ApiOperation(value = "관리자 상세옵션 리스트", notes = "관리자 상세옵션 리스트 API")
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
	
	
	
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 상품 DB에 수정", notes = "관리자 상품 DB에 수정 API")
	public ResponseEntity<JSONResult> edit(
			@RequestBody @Valid RequestItemEditDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// Service에 상품 정보 수정
		boolean isSuccess = itemService.edit(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	
	
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/display", method = RequestMethod.PUT)
	@ApiOperation(value = "관리자 상품 진열여부 DB에 수정", notes = "관리자 상품 진열여부 DB에 수정 API")
	public ResponseEntity<JSONResult> edit_display(
			@RequestBody @Valid RequestItemDisplayDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// TRUE로 바꾸고자 할 때 
		// OptionService에 상품옵션이 존재하는지 확인 (없으면 실패)
		if("TRUE".equals(dto.getDisplay())) {
			List<OptionVo> optionList = optionService.getListByItemNo(dto.getNo());
			if(optionList == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("옵션이 없습니다."));
		}
		
		// ItemService에 상품 정보 수정
		boolean isSuccess = itemService.editDisplay(dto.getNo(), dto.getDisplay());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/img", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 이미지를 DB에 저장", notes = "관리자 상품 이미지를 DB에 저장 API")
	public ResponseEntity<JSONResult> additemimg(
			@RequestBody @Valid RequestItemImgWriteDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 없는 상품번호일 때
		if(itemService.getByNo(dto.getItemNo()) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("없는 상품입니다."));
		
		// ItemImgService에 상품아이템 추가 요청
		boolean isSuccess = itemImgService.add(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/img", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 이미지를 DB에서 삭제", notes = "관리자 상품 이미지를 DB에서 삭제 API")
	public ResponseEntity<JSONResult> deleteitemimg(
			@RequestBody @Valid RequestNoDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));

		// ItemImgService에 상품아이템 삭제 요청
		boolean isSuccess = itemImgService.delete(dto.getNo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	
	
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/optiondetail", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 상세옵션 추가", notes = "관리자 상품 상세옵션 추가 API")
	public ResponseEntity<JSONResult> addoptiondetail(
			@RequestBody @Valid RequestOptionDetailWriteDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// 없는 상품번호일 때
		if(itemService.getByNo(dto.getItemNo()) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("없는 상품입니다."));
		
		// OptionDetailService에 상품상세옵션 추가 요청
		boolean isSuccess = optionDetailService.add(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/optiondetail", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 상세옵션 삭제", notes = "관리자 상품 상세옵션 삭제 API")
	public ResponseEntity<JSONResult> deleteoptiondetail(
			@RequestBody @Valid RequestNoDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));
		
		// OptionService 에서 상세옵션번호를 가지는 옵션이 있는지 확인 요청
		if(optionService.hasOptionDetailNo(dto.getNo())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("상세옵션번호를 옵션이 사용중입니다."));
		
		// OptionDetailService에 상품상세옵션 삭제 요청
		boolean isSuccess = optionDetailService.delete(dto.getNo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	
	
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/option", method = RequestMethod.POST)
	@ApiOperation(value = "관리자 상품 옵션 추가", notes = "관리자 상품 옵션 추가 API")
	public ResponseEntity<JSONResult> addoption(
			@RequestBody @Valid RequestOptionWriteDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));

		// 없는 상품번호일 때
		if(itemService.getByNo(dto.getItemNo()) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("없는 상품입니다."));
		
		// 존재하는 상세옵션인지 확인
		if(!optionDetailService.hasOptionDetail(dto.getOptionDetailNo1(), dto.getOptionDetailNo2()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("없는 상세옵션 입니다."));

		// OptionService에 상품옵션 추가 요청
		boolean isSuccess = optionService.add(dto.toVo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}
	

	@Auth(role = Role.ROLE_ADMIN)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mockToken", value = "인증키", paramType = "query", required = false, defaultValue = "")
	})
	@RequestMapping(value = "/option", method = RequestMethod.DELETE)
	@ApiOperation(value = "관리자 상품 옵션 삭제", notes = "관리자 상품 옵션 삭제 API")
	public ResponseEntity<JSONResult> addoption(
			@RequestBody @Valid RequestNoDto dto,
			BindingResult result
			) {
		// 유효성검사
		if(result.hasErrors()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(result.getAllErrors().get(0).getDefaultMessage()));

		// OptionService에 상품옵션 삭제 요청
		boolean isSuccess = optionService.delete(dto.getNo());
		
		// JSON 리턴 생성
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(isSuccess));
	}

}
