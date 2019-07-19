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
import com.cafe24.mhshop.security.Auth;
import com.cafe24.mhshop.security.Auth.Role;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.service.ItemImgService;
import com.cafe24.mhshop.service.ItemService;
import com.cafe24.mhshop.service.OptionDetailService;
import com.cafe24.mhshop.service.OptionService;
import com.cafe24.mhshop.vo.ItemImgVo;
import com.cafe24.mhshop.vo.ItemVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("itemAPIController")
@RequestMapping("/api/item")
@Api(value = "ItemController", description = "상품 컨트롤러")
public class ItemController {
	
	
	
	
	
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
	
	
	
	

}
