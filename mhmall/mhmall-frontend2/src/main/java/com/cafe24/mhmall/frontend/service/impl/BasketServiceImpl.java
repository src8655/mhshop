package com.cafe24.mhmall.frontend.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.BasketService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BasketServiceImpl implements BasketService {


	@Autowired
	OAuth2RestTemplate restTemplate;
	
	// 비회원 장바구니 추가 요청
	@Override
	public ResponseJSONResult<Boolean> guestAdd(Long[] optionNo, Long[] cnt, String guestSession) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("optionNos", optionNo);
	    params.put("optionCnts", cnt);
	    params.put("guestSession", guestSession);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/basket/guest", HttpMethod.POST, params, null);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 회원장바구니 추가 요청
	@Override
	public ResponseJSONResult<Boolean> memberAdd(String mockToken, Long[] optionNo, Long[] cnt) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("optionNos", optionNo);
	    params.put("optionCnts", cnt);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/basket/member", HttpMethod.POST, params, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 비회원일 때 비회원장바구니 리스트 요청
	@Override
	public ResponseJSONResult<ListBasketVo> guestList(String guestSession) {
	    
		ResponseJSONResult<ListBasketVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/basket/guest/" + guestSession, HttpMethod.GET, null, null);
		
		ObjectMapper mapper = new ObjectMapper();
		ListBasketVo data = mapper.convertValue(rJson.getData(), ListBasketVo.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 회원일 때 회원장바구니 리스트 요청
	@Override
	public ResponseJSONResult<ListBasketVo> memberList(String mockToken) {
		ResponseJSONResult<ListBasketVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/basket/member", HttpMethod.GET, null, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		ListBasketVo data = mapper.convertValue(rJson.getData(), ListBasketVo.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 비회원일 때 비회원장바구니 수정 요청
	@Override
	public ResponseJSONResult<Boolean> guestUpdate(Long no, Long cnt, String guestSession) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    params.put("cnt", cnt);
	    params.put("guestSession", guestSession);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/basket/guest", HttpMethod.PUT, params, null);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 회원일 때 회원장바구니 수정 요청
	@Override
	public ResponseJSONResult<Boolean> memberUpdate(String mockToken, Long no, Long cnt) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    params.put("cnt", cnt);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/basket/member", HttpMethod.PUT, params, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 비회원일 때 비회원장바구니 삭제 요청
	@Override
	public ResponseJSONResult<Boolean> guestDelete(Long no, String guestSession) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    params.put("guestSession", guestSession);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/basket/guest", HttpMethod.DELETE, params, null);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 회원일 때 회원장바구니 삭제 요청
	@Override
	public ResponseJSONResult<Boolean> memberDelete(String mockToken, Long no) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/basket/member", HttpMethod.DELETE, params, mockToken);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 존재하는 옵션이고 재고가 있는지 확인
	@Override
	public ResponseJSONResult<Boolean> hasCnt(Long[] optionNos, Integer[] optionCnts) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("optionNos", optionNos);
	    params.put("optionCnts", optionCnts);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/orders/hascnt", HttpMethod.POST, params, null);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}

}
