package com.cafe24.mhmall.frontend.controller;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.JSONResult;
import com.cafe24.mhmall.frontend.dto.RequestGuestOrdersStartDto;
import com.cafe24.mhmall.frontend.dto.RequestOrdersWriteGuestDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.dto.ResponseOrdersDto;
import com.cafe24.mhmall.frontend.security.AuthUser;
import com.cafe24.mhmall.frontend.security.SecurityUser;
import com.cafe24.mhmall.frontend.service.BasketService;
import com.cafe24.mhmall.frontend.service.CategoryService;
import com.cafe24.mhmall.frontend.service.ItemImgService;
import com.cafe24.mhmall.frontend.service.ItemService;
import com.cafe24.mhmall.frontend.service.OptionService;
import com.cafe24.mhmall.frontend.service.OrdersService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.ItemVo;
import com.cafe24.mhmall.frontend.vo.ItemsVo;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.cafe24.mhmall.frontend.vo.OrdersVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	OptionService optionService;
	
	@Autowired
	BasketService basketService;
	
	@Autowired
	OrdersService ordersService;
	
	

	// 비회원 정보 입력 페이지
	@RequestMapping(value = "/guestinfo", method = RequestMethod.POST)
	public String guestInfo(
			@ModelAttribute RequestGuestOrdersStartDto dto,
			Model model
			) {
		

		model.addAttribute("optionNos", dto.getOptionNos());
		model.addAttribute("optionCnts", dto.getOptionCnts());
		
		
		return "orders/guest_info";
	}


	// 비회원 주문
	@RequestMapping(value = "/guest", method = RequestMethod.POST)
	public String guest(
			@ModelAttribute RequestGuestOrdersStartDto dto,
			@CookieValue(name = "GuestSession", required = true, defaultValue = "") String guestSession,
			Model model
			) {
		
		
		// 비회원 주문 시작
		ResponseJSONResult<ResponseOrdersDto> rJson = ordersService.guestOrders(dto, guestSession);
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
		
        // 성공이면 주문서 작성 페이지
		model.addAttribute("ordersItemList", rJson.getData().getOrdersItemList());
		model.addAttribute("ordersNo", rJson.getData().getOrdersNo());
		model.addAttribute("guestPassword", dto.getGuestPassword());
		
		
		return "orders/guest_orders";
	}
	
	
	// 비회원 주문완료
	@RequestMapping(value = "/guest/update", method = RequestMethod.POST)
	public String guestUpdate(
			@ModelAttribute RequestOrdersWriteGuestDto dto,
			@CookieValue(name = "GuestSession", required = true, defaultValue = "") String guestSession,
			Model model
			) {
		
		
		// 비회원 주문 완료
		ResponseJSONResult<OrdersVo> rJson = ordersService.guestOrdersUpdate(dto);
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
		
        // 성공이면 입금 계좌 정보 출력
		model.addAttribute("ordersVo", rJson.getData());
		
		
		return "orders/guest_orders_fin";
	}
	
}
