package com.cafe24.mhmall.frontend.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mhmall.frontend.dto.JSONResult;
import com.cafe24.mhmall.frontend.dto.RequestJoinDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.security.Auth;
import com.cafe24.mhmall.frontend.security.Auth.Role;
import com.cafe24.mhmall.frontend.security.AuthUser;
import com.cafe24.mhmall.frontend.security.SecurityUser;
import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.service.OrdersService;
import com.cafe24.mhmall.frontend.service.OrdersService.OrdersVoList;
import com.cafe24.mhmall.frontend.service.impl.MemberServiceImpl;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.cafe24.mhmall.frontend.vo.OrdersVo;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/admin/orders")
public class AdminOrdersController {
	
	@Autowired
	OrdersService ordersService;
	

	
	// 관리자 주문 리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String ordersList(
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 관리자 주문 리스트 조회
		ResponseJSONResult<OrdersVoList> rJson = ordersService.getAdminList(authUser.getMockToken());
		
		
		model.addAttribute("ordersList", rJson.getData());
		
		
		return "admin/orders_list";
	}
	

	// 관리자 주문 상세
	@RequestMapping(value = "/view/{ordersNo}", method = RequestMethod.GET)
	public String ordersView(
			@PathVariable("ordersNo") String ordersNo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 관리자 주문 상세 조회
		ResponseJSONResult<OrdersVo> rJson = ordersService.getAdminView(ordersNo, authUser.getMockToken());
		
		
		model.addAttribute("ordersVo", rJson.getData());
		
		
		return "admin/orders_view";
	}
	
	
	// 관리자 주문 입금확인
	@RequestMapping(value = "/paycheck", method = RequestMethod.POST)
	public String ordersPaycheck(
			@RequestParam("ordersNo") String ordersNo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 관리자 주문 입금확인
		ResponseJSONResult<Boolean> rJson = ordersService.getAdminPaycheck(ordersNo, authUser.getMockToken());
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "입금확인 실패");
        	return "post/error";
        }
        
		return "redirect:/admin/orders/view/" + ordersNo;
	}
	
	
	// 관리자 운송장번호 등록
	@RequestMapping(value = "/tnumcheck", method = RequestMethod.POST)
	public String ordersPaycheck(
			@RequestParam("ordersNo") String ordersNo,
			@RequestParam("trackingNum") String trackingNum,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		// 관리자 운송장번호 등록
		ResponseJSONResult<Boolean> rJson = ordersService.getAdminTnumcheck(ordersNo, trackingNum, authUser.getMockToken());
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "운송장번호 등록 실패");
        	return "post/error";
        }
        
		return "redirect:/admin/orders/view/" + ordersNo;
	}
	
	
	
}
