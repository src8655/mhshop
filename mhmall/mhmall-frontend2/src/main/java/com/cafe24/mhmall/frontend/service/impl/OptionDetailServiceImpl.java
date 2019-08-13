package com.cafe24.mhmall.frontend.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.OptionDetailService;
import com.cafe24.mhmall.frontend.service.MemberService.ListMemberVo;
import com.cafe24.mhmall.frontend.service.OptionDetailService.ListOptionDetailVo;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.OptionDetailVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OptionDetailServiceImpl implements OptionDetailService {

	
	// 상세옵션 리스트
	@Override
	public ResponseJSONResult<ListOptionDetailVo> getOptionDetail(String authorization, Long itemNo, Integer level) {
		ResponseJSONResult<ListOptionDetailVo> rJson = MhmallRestTemplate.request(null, "/api/admin/item/optiondetail/"+itemNo + "/" + level, HttpMethod.GET, null, authorization);
		
		ObjectMapper mapper = new ObjectMapper();
		ListOptionDetailVo data = mapper.convertValue(rJson.getData(), ListOptionDetailVo.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 상세옵션 추가
	@Override
	public ResponseJSONResult<Boolean> add(String authorization, OptionDetailVo optionDetailVo) {
        Map<String, Object> params = new HashMap<String, Object>();
	    params.put("optionName", optionDetailVo.getOptionName());
	    params.put("itemNo", optionDetailVo.getItemNo());
	    params.put("level", optionDetailVo.getLevel());
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(null, "/api/admin/item/optiondetail", HttpMethod.POST, params, authorization);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}


	// 상세옵션 삭제 요청
	@Override
	public ResponseJSONResult<Boolean> delete(String authorization, Long no) {
		Map<String, Object> params = new HashMap<String, Object>();
	    params.put("no", no);
	    
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.request(null, "/api/admin/item/optiondetail", HttpMethod.DELETE, params, authorization);
		
		ObjectMapper mapper = new ObjectMapper();
		Boolean data = mapper.convertValue(rJson.getData(), Boolean.class);
    	rJson.setData(data);
		
		return rJson;
	}
	
	
}
