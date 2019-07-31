package com.cafe24.mhmall.frontend.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MhmallRestTemplate {
	public static final String BACKENDHOST = "http://localhost:8888/mhmall";
	
	
	public static <T> ResponseJSONResult<T> request(String uri, HttpMethod method , MultiValueMap<String, String> params, String authorization, Class<T> types) {
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        // 인증
        if(authorization != null) headers.add("Authorization", "Basic " + authorization);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
    	try {
    		ResponseEntity<ResponseJSONResult> response = restTemplate.exchange(new URI(BACKENDHOST + uri), method, body, ResponseJSONResult.class);
    		ResponseJSONResult rJson = response.getBody();
    		
    		ObjectMapper mapper = new ObjectMapper();
        	T data = mapper.convertValue(rJson.getData(), types);
        	rJson.setData(data);
        	
    		return rJson;
		} catch (HttpClientErrorException e) {
			ResponseJSONResult rJson = ResponseJSONResult.fail(e.getMessage());
			// 400 에러일 때
			if(e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					rJson = mapper.readValue(e.getResponseBodyAsString(), ResponseJSONResult.class);
				} catch (IOException e1) {}
				return rJson;
			}else {
				return rJson;
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        return null;
    }
}
