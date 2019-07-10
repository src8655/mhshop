package com.cafe24.mhshop.controller.api;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mhshop.config.AppConfig;
import com.cafe24.mhshop.config.TestWebConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class MemberControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		// db초기화
	}
	
	// 회원가입 컨트롤러 테스트
	@Test
	public void testFetchJoin() throws Exception {
		
		// [회원약관, 회원가입 페이지]
		mockMvc.perform(get("/api/member/join_form")).andExpect(status().isOk()).andDo(print());
		
		// 아이디 중복확인
		mockMvc.perform(get("/api/member/join_idcheck/{id}", "test_id")).andExpect(status().isOk()).andDo(print());
		
		// 회원 등록
		mockMvc.perform(post("/api/member/join_post")
				.param("id", "test_id")
				.param("password", "test_password")
				.param("name", "test_name")
				.param("phones", "test_1,test_2,test_3")
				.param("email", "test_email")
				.param("zipcode", "test_zipcode")
				.param("addr", "test_addr"))
		.andExpect(status().isOk()).andDo(print());
		

		// [회원가입 결과 페이지]
		mockMvc.perform(get("/api/member/join_result")).andExpect(status().isOk()).andDo(print());
		
		
	}
	
	

	// 로그인 컨트롤러 테스트
	@Test
	public void testFetchLogin() throws Exception {
		
		
		// [로그인 페이지]
		mockMvc.perform(get("/api/member/login_form")).andExpect(status().isOk());

		

		// 회원 로그인
		mockMvc.perform(post("/api/member/login")
				.param("member_id", "test_id")
				.param("password", "test_password"))
		.andExpect(status().isOk()).andDo(print());
		
		
		

		// 회원 로그아웃
		mockMvc.perform(post("/api/member/logout")).andExpect(status().isOk()).andDo(print());
		
	}
	
	
	
	
	@After
	public void finish() {
		
		// db초기화
	}
}
