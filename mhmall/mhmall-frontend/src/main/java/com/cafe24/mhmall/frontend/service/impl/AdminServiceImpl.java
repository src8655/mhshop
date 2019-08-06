package com.cafe24.mhmall.frontend.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public ResponseJSONResult<ListMemberVo> getMemberList(String authorization, Optional<String> search) {
		
		String search_str = "";
		// 검색어가 존재하면
		if(search.isPresent()) {
			try {
				search_str = URLEncoder.encode(search.get(), "UTF-8");
				search_str = "/" + search_str;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		System.out.println(search_str + "--");
		
		ResponseJSONResult rJson = MhmallRestTemplate.request("/api/admin/member/list"+search_str, HttpMethod.GET, null, authorization);
		
		ObjectMapper mapper = new ObjectMapper();
		ListMemberVo data = mapper.convertValue(rJson.getData(), ListMemberVo.class);
    	rJson.setData(data);
		
		return rJson;
	}

}
