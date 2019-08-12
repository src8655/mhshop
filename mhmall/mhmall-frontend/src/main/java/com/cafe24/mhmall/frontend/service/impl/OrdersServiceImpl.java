package com.cafe24.mhmall.frontend.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.frontend.dto.RequestGuestOrdersStartDto;
import com.cafe24.mhmall.frontend.dto.RequestOrdersWriteGuestDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.dto.ResponseOrdersDto;
import com.cafe24.mhmall.frontend.dto.ResponseOrdersViewDto;
import com.cafe24.mhmall.frontend.service.OrdersService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.GuestVo;
import com.cafe24.mhmall.frontend.vo.OrdersVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OAuth2RestTemplate restTemplate;

	
	// 비회원 주문 시작
	@Override
	public ResponseJSONResult<ResponseOrdersDto> guestOrders(RequestGuestOrdersStartDto dto, String guestSession) {
		GuestVo guestVo = dto.toVo();
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("guestName", dto.getGuestName());
	    params.put("guestPhone", guestVo.getGuestPhone());
	    params.put("guestPassword", dto.getGuestPassword());
	    params.put("optionNos", dto.getOptionNos());
	    params.put("optionCnts", dto.getOptionCnts());
	    params.put("guestSession", guestSession);
	    
		ResponseJSONResult<ResponseOrdersDto> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/guest", HttpMethod.POST, params, null);
		
		ObjectMapper mapper = new ObjectMapper();
		ResponseOrdersDto data = mapper.convertValue(rJson.getData(), ResponseOrdersDto.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 비회원 주문 완료
	@Override
	public ResponseJSONResult<OrdersVo> guestOrdersUpdate(RequestOrdersWriteGuestDto dto) {
		OrdersVo ordersVo = dto.toVo();
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("guestPassword", dto.getGuestPassword());
	    params.put("toName", dto.getToName());
	    params.put("toPhone", ordersVo.getToPhone());
	    params.put("toZipcode", dto.getToZipcode());
	    params.put("toAddr", dto.getToAddr());
	    params.put("ordersNo", dto.getOrdersNo());
	    
		ResponseJSONResult<OrdersVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/guest", HttpMethod.PUT, params, null);
		
		ObjectMapper mapper = new ObjectMapper();
		OrdersVo data = mapper.convertValue(rJson.getData(), OrdersVo.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 비회원 주문 상세
	@Override
	public ResponseJSONResult<ResponseOrdersViewDto> guestOrdersView(String ordersNo, String guestPassword) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("ordersNo", ordersNo);
	    params.put("guestPassword", guestPassword);
	    
		ResponseJSONResult<ResponseOrdersViewDto> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/guest/view", HttpMethod.POST, params, null);
		
		ObjectMapper mapper = new ObjectMapper();
		ResponseOrdersViewDto data = mapper.convertValue(rJson.getData(), ResponseOrdersViewDto.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 회원 주문 시작
	@Override
	public ResponseJSONResult<ResponseOrdersDto> memberOrders(Long[] optionNos, Long[] optionCnts, String mockToken) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("optionNos", optionNos);
	    params.put("optionCnts", optionCnts);
	    
		ResponseJSONResult<ResponseOrdersDto> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/member", HttpMethod.POST, params, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		ResponseOrdersDto data = mapper.convertValue(rJson.getData(), ResponseOrdersDto.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 회원 주문 완료
	@Override
	public ResponseJSONResult<OrdersVo> memberOrdersUpdate(RequestOrdersWriteGuestDto dto, String mockToken) {
		OrdersVo ordersVo = dto.toVo();
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("toName", dto.getToName());
	    params.put("toPhone", ordersVo.getToPhone());
	    params.put("toZipcode", dto.getToZipcode());
	    params.put("toAddr", dto.getToAddr());
	    params.put("ordersNo", dto.getOrdersNo());
	    
		ResponseJSONResult<OrdersVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/member", HttpMethod.PUT, params, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		OrdersVo data = mapper.convertValue(rJson.getData(), OrdersVo.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 회원 주문 리스트
	@Override
	public ResponseJSONResult<OrdersVoList> memberOrdersList(String mockToken) {
		ResponseJSONResult<OrdersVoList> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/member/list", HttpMethod.GET, null, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		OrdersVoList data = mapper.convertValue(rJson.getData(), OrdersVoList.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 회원 주문 상세
	@Override
	public ResponseJSONResult<OrdersVo> memberOrdersView(String ordersNo, String mockToken) {
	    
		ResponseJSONResult<OrdersVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/member/view/" + ordersNo, HttpMethod.GET, null, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		OrdersVo data = mapper.convertValue(rJson.getData(), OrdersVo.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 회원 주문 취소
	@Override
	public ResponseJSONResult<Boolean> memberOrdersCancel(String ordersNo, String mockToken) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("ordersNo", ordersNo);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/member/cancel", HttpMethod.PUT, params, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 비회원 주문 취소
	@Override
	public ResponseJSONResult<Boolean> guestOrdersCancel(String ordersNo, String guestPassword) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("ordersNo", ordersNo);
	    params.put("guestPassword", guestPassword);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/guest/cancel", HttpMethod.PUT, params, null);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 관리자 주문 리스트 조회
	@Override
	public ResponseJSONResult<OrdersVoList> getAdminList(String mockToken) {
		ResponseJSONResult<OrdersVoList> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/orders/list", HttpMethod.GET, null, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		OrdersVoList data = mapper.convertValue(rJson.getData(), OrdersVoList.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 관리자 주문 상세 조회
	@Override
	public ResponseJSONResult<OrdersVo> getAdminView(String ordersNo, String mockToken) {
		ResponseJSONResult<OrdersVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/orders/view/" + ordersNo, HttpMethod.GET, null, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		OrdersVo data = mapper.convertValue(rJson.getData(), OrdersVo.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 관리자 주문 입금확인
	@Override
	public ResponseJSONResult<Boolean> getAdminPaycheck(String ordersNo, String mockToken) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("ordersNo", ordersNo);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/orders/paycheck", HttpMethod.PUT, params, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 관리자 운송장번호 등록
	@Override
	public ResponseJSONResult<Boolean> getAdminTnumcheck(String ordersNo, String trackingNum, String mockToken) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("ordersNo", ordersNo);
	    params.put("trackingNum", trackingNum);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/orders/tnumcheck", HttpMethod.PUT, params, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}

}
