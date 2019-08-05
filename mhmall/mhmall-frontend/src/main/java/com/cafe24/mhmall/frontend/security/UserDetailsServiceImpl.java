package com.cafe24.mhmall.frontend.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cafe24.mhmall.frontend.service.MemberService;
import com.cafe24.mhmall.frontend.vo.MemberVo;





@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberVo memberVo = memberService.login(id);
		
		SecurityUser securityUser = new SecurityUser();
		
		if(memberVo != null) {
			securityUser.setAuthorization(memberVo.getMockToken());
			securityUser.setName(memberVo.getName());
			securityUser.setUsername(memberVo.getId());
			securityUser.setPassword(memberVo.getPassword());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberVo.getRole())));
		}
		
		return securityUser;
	}	
}
