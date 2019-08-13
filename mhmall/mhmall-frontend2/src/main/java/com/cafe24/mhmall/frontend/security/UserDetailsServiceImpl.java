package com.cafe24.mhmall.frontend.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.vo.MemberVo;




@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		// 아이디로 회원정보(로그인)
		ResponseJSONResult<MemberVo> rJson = memberService.get(id);
		MemberVo memberVo = rJson.getData();
		
		SecurityUser securityUser = new SecurityUser();
		System.out.println(memberVo);
		if(memberVo != null) {
			securityUser.setPhone(memberVo.getPhone());
			securityUser.setZipcode(memberVo.getZipcode());
			securityUser.setAddr(memberVo.getAddr());
			securityUser.setMockToken(memberVo.getMockToken());
			securityUser.setName(memberVo.getName());
			securityUser.setUsername(memberVo.getId());
			securityUser.setPassword(memberVo.getPassword());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberVo.getRole())));
		}
		System.out.println(securityUser);
		
		return securityUser;
	}	
}
