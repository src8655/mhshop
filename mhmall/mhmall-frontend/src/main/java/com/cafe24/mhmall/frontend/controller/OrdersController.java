package com.cafe24.mhmall.frontend.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.mhmall.frontend.dto.JSONResult;
import com.cafe24.mhmall.frontend.dto.RequestGuestOrdersStartDto;
import com.cafe24.mhmall.frontend.dto.RequestOrdersWriteGuestDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.dto.ResponseOrdersDto;
import com.cafe24.mhmall.frontend.dto.ResponseOrdersViewDto;
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
			@CookieValue(name = "GuestSession", required = true, defaultValue = "") String guestSession,
			Model model
			) {
		
		// 주문할게 없으면 오류
		if((dto.getOptionNos() != null && dto.getOptionNos().length == 0) || dto.getOptionNos() == null) {
        	model.addAttribute("message", "주문할 상품이 없습니다.");
        	return "post/error";
		}
		
		// 존재하는 옵션이고 재고가 있는지 확인
		ResponseJSONResult<Boolean> rJson = basketService.hasCnt(dto.getOptionNos(), dto.getOptionCnts());
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "주문 실패");
        	return "post/error";
        }
		

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
		model.addAttribute("guestName", dto.getGuestName());
		model.addAttribute("guestPhone1", dto.getPhone1());
		model.addAttribute("guestPhone2", dto.getPhone2());
		model.addAttribute("guestPhone3", dto.getPhone3());
		model.addAttribute("ordersVo", rJson.getData());
		
		
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
		model.addAttribute("guestPassword", dto.getGuestPassword());
		
		
		return "orders/guest_orders_fin";
	}
	
	
	// 비회원 주문상세 비회원정보 입력 페이지
	@RequestMapping(value = "/guest/view", method = RequestMethod.GET)
	public String guestViewForm(
			Model model
			) {
		
		
		return "orders/guest_view_form";
	}
	
	
	// 비회원 주문 상세
	@RequestMapping(value = "/guest/view", method = RequestMethod.POST)
	public String guestView(
			@RequestParam("ordersNo") String ordersNo,
			@RequestParam("guestPassword") String guestPassword,
			Model model
			) {
		
		
		// 비회원 주문 상세
		ResponseJSONResult<ResponseOrdersViewDto> rJson = ordersService.guestOrdersView(ordersNo, guestPassword);
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
		
        // 비회원 주문 상세 출력
		model.addAttribute("ordersItemList", rJson.getData().getOrdersItemList());
		model.addAttribute("ordersVo", rJson.getData().getOrdersVo());
		model.addAttribute("guestPassword", guestPassword);
		
		
		return "orders/guest_view";
	}
	
	
	// 비회원 주문 취소
	@RequestMapping(value = "/guest/cancel", method = RequestMethod.POST)
	public String guestCancel(
			@RequestParam("ordersNo") String ordersNo,
			@RequestParam("guestPassword") String guestPassword,
			RedirectAttributes redirectAttributes,
			Model model
			) {
		
		
		// 비회원 주문 취소
		ResponseJSONResult<Boolean> rJson = ordersService.guestOrdersCancel(ordersNo, guestPassword);
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "취소 실패");
        	return "post/error";
        }
        
        
        redirectAttributes.addFlashAttribute("ordersNo", ordersNo);
        redirectAttributes.addFlashAttribute("guestPassword", guestPassword);
        
        
		return "redirect:/orders/guest/view";
	}
	
	
	// 회원 주문
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public String member(
			@RequestParam("optionNos") Long[] optionNos,
			@RequestParam("optionCnts") Long[] optionCnts,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		
		// 회원 주문 시작
		ResponseJSONResult<ResponseOrdersDto> rJson = ordersService.memberOrders(optionNos, optionCnts, authUser.getMockToken());
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
		
        // 성공이면 주문서 작성 페이지
		model.addAttribute("ordersItemList", rJson.getData().getOrdersItemList());
		model.addAttribute("ordersNo", rJson.getData().getOrdersNo());
		
		
		return "orders/member_orders";
	}
	

	// 회원 주문완료
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String memberUpdate(
			@ModelAttribute RequestOrdersWriteGuestDto dto,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		
		// 회원 주문 완료
		ResponseJSONResult<OrdersVo> rJson = ordersService.memberOrdersUpdate(dto, authUser.getMockToken());
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
		
        // 성공이면 입금 계좌 정보 출력
		model.addAttribute("ordersVo", rJson.getData());
		
		
		return "orders/member_orders_fin";
	}
	
	
	// 회원 주문 리스트
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String memberList(
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		
		// 회원 주문 리스트
		ResponseJSONResult<OrdersService.OrdersVoList> rJson = ordersService.memberOrdersList(authUser.getMockToken());
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
		
        // 비회원 주문 상세 출력
		model.addAttribute("ordersList", rJson.getData());
		
		
		return "orders/member_list";
	}

	
	// 회원 주문 상세
	@RequestMapping(value = "/member/view/{ordersNo}", method = RequestMethod.GET)
	public String memberView(
			@PathVariable("ordersNo") String ordersNo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		
		// 회원 주문 상세
		ResponseJSONResult<OrdersVo> rJson = ordersService.memberOrdersView(ordersNo, authUser.getMockToken());
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        
		
        // 비회원 주문 상세 출력
		model.addAttribute("ordersVo", rJson.getData());
		
		
		return "orders/member_view";
	}
	

	// 회원 주문 취소
	@RequestMapping(value = "/member/cancel", method = RequestMethod.POST)
	public String memberCancel(
			@RequestParam("ordersNo") String ordersNo,
			@AuthUser SecurityUser authUser,
			Model model
			) {
		
		
		// 회원 주문 취소
		ResponseJSONResult<Boolean> rJson = ordersService.memberOrdersCancel(ordersNo, authUser.getMockToken());
		
		
	    // 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }
        if(!rJson.getData()) {
        	model.addAttribute("message", "취소 실패");
        	return "post/error";
        }
        
		return "redirect:/orders/member/list";
	}
	

	// 비회원 주문번호 찾기 입력 페이지
	@RequestMapping(value = "/guest/find/ordersno", method = RequestMethod.GET)
	public String guestFindOrdersnoForm() {
		
		return "orders/guest_find_ordersno_form";
	}
	
	
	// 비회원 주문번호 찾기
	@RequestMapping(value = "/guest/find/ordersno", method = RequestMethod.POST)
	public String guestFindOrdersno(
			@RequestParam("guestName") String guestName,
			@RequestParam("guestPhone1") String guestPhone1,
			@RequestParam("guestPhone2") String guestPhone2,
			@RequestParam("guestPhone3") String guestPhone3,
			@RequestParam("guestPassword") String guestPassword,
			Model model
			) {
		
		// 비회원 주문번호 찾기
		ResponseJSONResult<OrdersService.OrdersVoList> rJson = ordersService.guestFindOrdersNo(guestName, guestPhone1, guestPhone2, guestPhone3, guestPassword);
		
		// 실패면
        if("fail".equals(rJson.getResult())) {
        	model.addAttribute("message", rJson.getMessage());
        	return "post/error";
        }

		model.addAttribute("ordersList", rJson.getData());
		model.addAttribute("guestPassword", guestPassword);
		
		return "orders/guest_find_ordersno";
	}

}
