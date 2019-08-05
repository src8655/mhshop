package com.cafe24.mhmall.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.MemberVo;


@Service
public class MemberService {
	@Autowired
	private OAuth2RestTemplate restTemplate;

	
	public MemberVo login(String id){
		
		String endpoint = "http://192.168.0.5:8888/mhmall/api/admin/member/view/" + id;
		ResponseJSONResult<MemberVo> jsonResult = restTemplate.getForObject(endpoint, JSONResultMemberVo.class);

		return jsonResult.getData();
	}
	private static class JSONResultMemberVo extends ResponseJSONResult<MemberVo> {
		
	}
}
