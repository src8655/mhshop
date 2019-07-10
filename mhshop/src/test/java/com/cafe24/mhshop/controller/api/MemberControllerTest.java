package com.cafe24.mhshop.controller.api;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import com.cafe24.mhshop.config.AppConfig;
import com.cafe24.mhshop.config.TestWebConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class MemberControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeClass
	public static void classSetup() {
		// DB 초기화
	}
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
	}
	
	

	// [회원약관, 회원가입 페이지]
	@Test
	public void testAJoinForm() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/member/join_form").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.forward", is("join/join_form")));
		
	}
	
	
	// 아이디 중복확인
	@Test
	public void testBJoinIdCheck() throws Exception {

		ResultActions resultActions = mockMvc.perform(get("/api/member/join_idcheck/{id}", "test_id").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 존재하지 않는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", is(false)));
	}

	// 회원 등록
	@Test
	public void testCJoin() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/join")
				.param("id", "test_id")
				.param("password", "testpassword1!")
				.param("name", "test")
				.param("phone", "01000000000")
				.param("email", "test_email@naver.com")
				.param("zipcode", "test_zipcode")
				.param("addr", "test_addr")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// DB의 사용자 정보 결과 확인
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		
		.andExpect(jsonPath("$.data.memberVo.id", is("test_id")))
		.andExpect(jsonPath("$.data.memberVo.name", is("test")))
		.andExpect(jsonPath("$.data.memberVo.phone", is("01000000000")))
		.andExpect(jsonPath("$.data.memberVo.email", is("test_email@naver.com")))
		.andExpect(jsonPath("$.data.memberVo.zipcode", is("test_zipcode")))
		.andExpect(jsonPath("$.data.memberVo.addr", is("test_addr")))
		.andExpect(jsonPath("$.data.memberVo.role", is("USER")))
		
		.andExpect(jsonPath("$.data.redirect", is("/join_result")));
		
	}
	
	
	// [회원가입 결과 페이지]
	@Test
	public void testDJoinResult() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/member/join_result").contentType(MediaType.APPLICATION_JSON));

		// 응답이 200 인지
		// 결과가 성공햇는지
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.forward", is("join/join_result")));
		
	}

	// [로그인 페이지]
	@Test
	public void testELoginForm() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/member/login_form").contentType(MediaType.APPLICATION_JSON));

		// 응답이 200 인지
		// 결과가 성공햇는지
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.forward", is("login/login_form")));
	}

	// 회원 로그인
	@Test
	public void testFLogin() throws Exception {

		ResultActions resultActions = mockMvc.perform(post("/api/member/login")
				.param("id", "test_id")
				.param("password", "testpassword1!")
				.contentType(MediaType.APPLICATION_JSON));

		// 응답이 200 인지
		// 결과가 성공햇는지
		// 로그인된 정보
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		
		.andExpect(jsonPath("$.data.memberVo.id", is("test_id")))
		.andExpect(jsonPath("$.data.memberVo.name", is("test")))
		.andExpect(jsonPath("$.data.memberVo.phone", is("01000000000")))
		.andExpect(jsonPath("$.data.memberVo.email", is("test_email@naver.com")))
		.andExpect(jsonPath("$.data.memberVo.zipcode", is("test_zipcode")))
		.andExpect(jsonPath("$.data.memberVo.addr", is("test_addr")))
		.andExpect(jsonPath("$.data.memberVo.role", is("USER")))
		
		.andExpect(jsonPath("$.data.redirect", is("/")));
	}
	

	// 회원 로그아웃
	@Test
	public void testGLogout() throws Exception {

		ResultActions resultActions = mockMvc.perform(post("/api/member/logout").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.redirect", is("/")));
		
	}
	
	
	@AfterClass
	public static void finish() {
		// DB 초기화
	}
	
	
}
