package com.cafe24.mhshop.controller.api;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.ibatis.session.SqlSession;
import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mhshop.config.TestAppConfig;
import com.cafe24.mhshop.config.TestWebConfig;
import com.cafe24.mhshop.vo.MemberVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class MemberControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	// 아이디 중복확인
	@Test
	public void testA아이디중복확인() throws Exception {
		ResultActions resultActions;
		
		// 잘못된 아이디
		resultActions = mockMvc.perform(get("/api/member/join/idcheck/{id}", "1test_id1").contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		
		// 중복된 아이디 상황
		resultActions = mockMvc.perform(get("/api/member/join/idcheck/{id}", "test_id1").contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 이미 존재해야함
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)));
		
		
		
		// 사용가능한 아이디 상황
		resultActions = mockMvc.perform(get("/api/member/join/idcheck/{id}", "test_id3").contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 존재하지 않아야함
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(false)));
	}
	

	
	// 회원 등록 Valid
	@Test
	public void testB회원가입() throws Exception {
		ResultActions resultActions;
		
		
		// 아이디 Valid
		resultActions = mockMvc.perform(post("/api/member/join")
				.param("id", "1test_id3")
				.param("password", "testpassword3!")
				.param("name", "test3")
				.param("phone", "01000000003")
				.param("email", "test_email3@naver.com")
				.param("zipcode", "test_zipcode3")
				.param("addr", "test_addr3")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 비밀번호 Valid
		resultActions = mockMvc.perform(post("/api/member/join")
				.param("id", "test_id3")
				.param("password", "testpassword3")
				.param("name", "test3")
				.param("phone", "01000000003")
				.param("email", "test_email3@naver.com")
				.param("zipcode", "test_zipcode3")
				.param("addr", "test_addr3")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 전화번호 Valid
		resultActions = mockMvc.perform(post("/api/member/join")
				.param("id", "test_id3")
				.param("password", "testpassword3!")
				.param("name", "test3")
				.param("phone", "test_phone")
				.param("email", "test_email3@naver.com")
				.param("zipcode", "test_zipcode3")
				.param("addr", "test_addr3")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 회원가입성공
		resultActions = mockMvc.perform(post("/api/member/join")
				.param("id", "test_id5")
				.param("password", "testpassword3!")
				.param("name", "test3")
				.param("phone", "01033244343")
				.param("email", "test_email3@naver.com")
				.param("zipcode", "test_zipcode3")
				.param("addr", "test_addr3")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(true)));
	}
	

	
	
	
	
	// 로그인
	@Test
	public void testC로그인() throws Exception {

		ResultActions resultActions;
		
		// 아이디 Valid
		resultActions = mockMvc.perform(post("/api/member/login")
				.param("id", "1test_id1")
				.param("password", "testpassword1!")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
				
		
		// 비밀번호 Valid
		resultActions = mockMvc.perform(post("/api/member/login")
				.param("id", "test_id1")
				.param("password", "testpassword1")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 로그인 실패
		resultActions = mockMvc.perform(post("/api/member/login")
				.param("id", "test_id9")
				.param("password", "testpassword1!")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 로그인 성공
		resultActions = mockMvc.perform(post("/api/member/login")
				.param("id", "test_id1")
				.param("password", "testpassword1!")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", Matchers.notNullValue()));
	}
	
	
	
	
	
	
	
	
	
	/*
	// 회원수정 페이지
	@Test
	public void testH회원수정페이지() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/member/loginupdate").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.forward", is("login/update_form")));
		
	}
	*/
	
}
