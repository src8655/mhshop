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

}
