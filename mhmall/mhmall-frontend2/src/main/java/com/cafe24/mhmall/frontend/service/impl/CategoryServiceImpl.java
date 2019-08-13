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
import com.cafe24.mhmall.frontend.service.CategoryService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoryServiceImpl implements CategoryService {


	@Autowired
	OAuth2RestTemplate restTemplate;

	
	// 카테고리 추가요청
	@Override
	public ResponseJSONResult<Boolean> add(String mockToken, String categoryName) {
		
        Map<String, Object> params = new HashMap<String, Object>();
	    params.put("name", categoryName);
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/category", HttpMethod.POST, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		return rJson;
	}

	
	// 카테고리 리스트 요청
	@Override
	public ResponseJSONResult<ListCategoryVo> getList() {
		
		ResponseJSONResult<ListCategoryVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/category/list", HttpMethod.GET, null, null);
	    
		ObjectMapper mapper = new ObjectMapper();
		ListCategoryVo data = mapper.convertValue(rJson.getData(), ListCategoryVo.class);
		rJson.setData(data);
		
		return rJson;
	}


	// 카테고리 삭제요청
	@Override
	public ResponseJSONResult<Boolean> delete(String mockToken, Long no) {

        Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/category", HttpMethod.DELETE, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		return rJson;
	}


	// 카테고리 수정요청
	@Override
	public ResponseJSONResult<Boolean> edit(String mockToken, Long no, String categoryName) {

        Map<String, Object> params = new HashMap<String, Object>();
	    params.put("name", categoryName);
	    params.put("no", no);
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/category", HttpMethod.PUT, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		return rJson;
	}



}
