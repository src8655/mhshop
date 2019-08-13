package com.cafe24.mhmall.frontend.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.OptionService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.OptionVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OptionServiceImpl implements OptionService {
	

	@Autowired
	RestTemplate restTemplate;

	
	// 옵션 추가
	@Override
	public ResponseJSONResult<Boolean> add(String authorization, OptionVo optionVo) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(optionVo.getOptionDetailNo1() != -1)
			 params.put("optionDetailNo1", optionVo.getOptionDetailNo1());
		else params.put("optionDetailNo1", null);
		if(optionVo.getOptionDetailNo2() != -1)
			 params.put("optionDetailNo2", optionVo.getOptionDetailNo2());
		else params.put("optionDetailNo2", null);
	    params.put("cnt", optionVo.getCnt());
	    params.put("itemNo", optionVo.getItemNo());
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/item/option", HttpMethod.POST, params, authorization);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 옵션 리스트(1차 없으면 1차 있으면 2차)
	@Override
	public ResponseJSONResult<ListOptionVo> getList(Long itemNo, Optional<Long> optionDetailNo1) {
		Long oNo = -1L;
		if(optionDetailNo1.isPresent())
			oNo = optionDetailNo1.get();
		ResponseJSONResult<ListOptionVo> rJson = MhmallRestTemplate.request(restTemplate, "/api/item/option/list/"+itemNo + "/" + oNo, HttpMethod.GET, null, null);
		
		ObjectMapper mapper = new ObjectMapper();
		ListOptionVo data = mapper.convertValue(rJson.getData(), ListOptionVo.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 옵션 삭제 요청
	@Override
	public ResponseJSONResult<Boolean> delete(String authorization, Long no) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(restTemplate, "/api/admin/item/option", HttpMethod.DELETE, params, authorization);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}
	
	
}
