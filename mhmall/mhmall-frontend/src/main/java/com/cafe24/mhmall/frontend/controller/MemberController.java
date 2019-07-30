package com.cafe24.mhmall.frontend.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.util.MhmallRestTemplate;
import com.cafe24.mhmall.frontend.vo.MemberVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginform() {
		
		return "member/login";
	}
	

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(
			@RequestParam(name = "id", required = true, defaultValue = "") String id,
			@RequestParam(name = "password", required = true, defaultValue = "") String password,
			Model model
			) {
		

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("id", id);
        params.add("password", password);
        ResponseJSONResult<MemberVo> rJson = MhmallRestTemplate.<MemberVo>request("/api/member/login", HttpMethod.POST, params, null, MemberVo.class);
        
        // 실패면
        if("fail".equals(rJson.getResult())) 
        	model.addAttribute("message", rJson.getMessage());
        else {
        	MemberVo memberVo = rJson.getData();
        	model.addAttribute("authUser", new MemberVo(memberVo.getId(), null, memberVo.getName(), null, null, null, null, null, null, null));
        }
		
		return "member/login";
	}
}
