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
	
	

	// [회원약관, 회원가입 페이지]
	@Test
	public void testA회원가입페이지() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/member/join").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.forward", is("join/join_form")));
		
	}
	
	// 아이디 중복확인 잘못된 아이디 Valid
	@Test
	public void testB아이디중복확인_아이디_Valid() throws Exception {
		ResultActions resultActions;
		
		// 잘못된 아이디
		resultActions = mockMvc.perform(get("/api/member/join/idcheck/{id}", "1test_id1").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	

	// 회원 등록 아이디 Valid
	@Test
	public void testC회원가입_아이디_Valid() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/join")
				.param("id", "1test_id3")
				.param("password", "testpassword3!")
				.param("name", "test3")
				.param("phone", "01000000003")
				.param("email", "test_email3@naver.com")
				.param("zipcode", "test_zipcode3")
				.param("addr", "test_addr3")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	

	// 회원 등록 비밀번호 Valid
	@Test
	public void testC회원가입_비밀번호_Valid() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/join")
				.param("id", "test_id3")
				.param("password", "testpassword3")
				.param("name", "test3")
				.param("phone", "01000000003")
				.param("email", "test_email3@naver.com")
				.param("zipcode", "test_zipcode3")
				.param("addr", "test_addr3")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	
	
	// 회원 등록 전화번호 Valid
	@Test
	public void testC회원가입_전화번호_Valid() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(post("/api/member/join")
				.param("id", "test_id3")
				.param("password", "testpassword3!")
				.param("name", "test3")
				.param("phone", "test_phone")
				.param("email", "test_email3@naver.com")
				.param("zipcode", "test_zipcode3")
				.param("addr", "test_addr3")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	
	
	// [회원가입 결과 페이지]
	@Test
	public void testD회원가입결과페이지() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/member/join/result").contentType(MediaType.APPLICATION_JSON));

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
	public void testE로그인페이지() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/member/login").contentType(MediaType.APPLICATION_JSON));

		// 응답이 200 인지
		// 결과가 성공했는지
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.forward", is("login/login_form")));
	}


	// 회원 로그인 아이디 Valid
	@Test
	public void testF로그인_아이디Valid() throws Exception {

		ResultActions resultActions;
		
		
		resultActions = mockMvc.perform(post("/api/member/login")
				.param("id", "1test_id1")
				.param("password", "testpassword1!")
				.contentType(MediaType.APPLICATION_JSON));

		// 응답이 200 인지
		// 결과가 실패했는지
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	
	

	// 회원 로그인 비밀번호 Valid
	@Test
	public void testF로그인_비밀번호Valid() throws Exception {

		ResultActions resultActions;
		
		
		resultActions = mockMvc.perform(post("/api/member/login")
				.param("id", "test_id1")
				.param("password", "testpassword1")
				.contentType(MediaType.APPLICATION_JSON));

		// 응답이 200 인지
		// 결과가 실패했는지
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	

	// 회원 로그아웃
	@Test
	public void testG로그아웃() throws Exception {

		ResultActions resultActions = mockMvc.perform(post("/api/member/logout").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.redirect", is("/")));
		
	}
	
	
	
	
}
