package com.cafe24.mhmall.frontend.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.frontend.dto.RequestJoinDto;
import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.MemberVo;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;

	
	// 로그인
	@Override
	public MemberVo login(String id){
		
		ResponseJSONResult<MemberVo> jsonResult = MhmallRestTemplate.<MemberVo>request(restTemplate, "/api/admin/member/view/" + id, HttpMethod.GET, null, null, MemberVo.class);

		return jsonResult.getData();
	}
	
	
	// 회원가입
	@Override
	public ResponseJSONResult<Boolean> add(RequestJoinDto dto) {
		MemberVo memberVo = dto.toVo();
		
		// 비밀번호가 다르면
		if(!dto.getPassword().equals(dto.getPassword2()))
			return ResponseJSONResult.fail("비밀번호가 다릅니다.");
			
		// 가입 요청
        Map<String, Object> params = new HashMap<String, Object>();
	    params.put("id", memberVo.getId());
	    params.put("password", memberVo.getPassword());
	    params.put("name", memberVo.getName());
	    params.put("phone", memberVo.getPhone());
	    params.put("email", memberVo.getEmail());
	    params.put("zipcode", memberVo.getZipcode());
	    params.put("addr", memberVo.getAddr());
	    
	    ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.<Boolean>request(restTemplate, "/api/member/join", HttpMethod.POST, params, null, Boolean.class);
	    
	    return rJson;
	}

	
	// 아이디 중복확인
	@Override
	public ResponseJSONResult<Boolean> idcheck(String id) {
		ResponseJSONResult<Boolean> rJson = MhmallRestTemplate.<Boolean>request(restTemplate, "/api/member/join/idcheck/" + id, HttpMethod.GET, null, null, Boolean.class);
		return rJson;
	}
}
