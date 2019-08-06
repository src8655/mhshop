package com.cafe24.mhmall.frontend.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.AdminCategoryService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

	
	// 카테고리 추가요청
	@Override
	public ResponseJSONResult<Boolean> add(String mockToken, String categoryName) {
		
        Map<String, Object> params = new HashMap<String, Object>();
	    params.put("name", categoryName);
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request("/api/admin/category", HttpMethod.POST, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		return rJson;
	}

	
	// 카테고리 리스트 요청
	@Override
	public ResponseJSONResult<ListCategoryVo> getList() {
		
		ResponseJSONResult<ListCategoryVo> rJson = MhmallRestTemplate.request("/api/category/list", HttpMethod.GET, null, null);
	    
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
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request("/api/admin/category", HttpMethod.DELETE, params, mockToken);
	    
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
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request("/api/admin/category", HttpMethod.PUT, params, mockToken);
	    
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
		rJson.setData(data);
		
		return rJson;
	}

}
