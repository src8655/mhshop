package com.cafe24.mhmall.frontend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.AdminService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdminServiceImpl implements AdminService {

	
	// 관리자 회원목록
	@Override
	public ResponseJSONResult<ListMemberVo> getMemberList(String authorization) {
		
		ResponseJSONResult rJson = MhmallRestTemplate.request("/api/admin/member/list", HttpMethod.GET, null, authorization);
		
		ObjectMapper mapper = new ObjectMapper();
		ListMemberVo data = mapper.convertValue(rJson.getData(), ListMemberVo.class);
    	rJson.setData(data);
		
		return rJson;
	}

}
