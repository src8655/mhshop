package com.cafe24.mhshop.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mhshop.dto.JSONResult;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.service.MemberService;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.MemberVo;
import com.cafe24.mhshop.vo.OptionDetailVo;
import com.cafe24.mhshop.vo.OptionVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("adminMemberAPIController")
@RequestMapping("/api/admin/member")
@Api(value = "AdminMemberController", description = "관리자 회원관리 컨트롤러")
public class AdminMemberController {
	
	@Autowired
	MemberService memberService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "회원 리스트", notes = "회원 리스트 요청 API")
	public JSONResult list() {
		
		// 권한 확인
		
		
		// Service에 회원리스트 요청
		List<MemberVo> memberList = memberService.getList();
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("memberList", memberList);
		dataMap.put("forward", "admin/member_list");
		return JSONResult.success(dataMap);
	}
	

	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "회원아이디", paramType = "path", required = true, defaultValue = ""),
	})
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "회원 상세보기", notes = "회원 상세보기 요청 API")
	public JSONResult view(
			@PathVariable("id") String id
			) {
		
		// 권한 확인
		
		
		// Service에 회원상세 요청
		MemberVo memberVo = memberService.getById(id);
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("memberVo", memberVo);
		dataMap.put("forward", "admin/member_view");
		return JSONResult.success(dataMap);
	}
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "회원아이디", paramType = "path", required = true, defaultValue = ""),
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "회원 삭제", notes = "회원 삭제 요청 API")
	public JSONResult delete(
			@PathVariable("id") String id
			) {
		
		// 권한 확인
		
		
		// Service에 회원 삭제 요청
		boolean isSuccess = memberService.delete(id);
		
		
		// JSON 리턴 생성
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("result", isSuccess);
		dataMap.put("redirect", "/api/admin/member/list");
		return JSONResult.success(dataMap);
	}
	
}
