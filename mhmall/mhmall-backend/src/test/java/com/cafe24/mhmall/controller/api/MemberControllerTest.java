package com.cafe24.mhmall.controller.api;

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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback(value = true)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private String authorization;
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		

		ResultActions resultActions;
		
		// 사용자 로그인
		resultActions = mockMvc.perform(post("/api/member/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"test_id1\","
						+ "\"password\":\"testpassword1!\""
						+ "}"));
		// 응답이 200 인지
		MvcResult mvcResult = resultActions
		.andExpect(status().isOk())
		.andReturn();

		// 로그인키 가져오기
		String content = mvcResult.getResponse().getContentAsString();
		JsonParser Parser = new JsonParser();
		JsonObject jsonObj = (JsonObject) Parser.parse(content);
		authorization = jsonObj.get("data").getAsJsonObject().get("mockToken").getAsString();
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
	

	
	// 회원가입
	@Test
	public void testB회원가입() throws Exception {
		ResultActions resultActions;
		
		
		// 아이디 Valid
		resultActions = mockMvc.perform(post("/api/member/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+"\"id\":\"1test_id3\","
						+"\"password\":\"testpassword3!\","
						+"\"name\":\"test3\","
						+"\"phone\":\"01033244343\","
						+"\"email\":\"test_email3@naver.com\","
						+"\"zipcode\":\"test_zipcode3\","
						+"\"addr\":\"test_addr3\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 비밀번호 Valid
		resultActions = mockMvc.perform(post("/api/member/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+"\"id\":\"test_id3\","
						+"\"password\":\"testpassword3\","
						+"\"name\":\"test3\","
						+"\"phone\":\"01033244343\","
						+"\"email\":\"test_email3@naver.com\","
						+"\"zipcode\":\"test_zipcode3\","
						+"\"addr\":\"test_addr3\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 전화번호 Valid
		resultActions = mockMvc.perform(post("/api/member/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+"\"id\":\"test_id3\","
						+"\"password\":\"testpassword3!\","
						+"\"name\":\"test3\","
						+"\"phone\":\"test_phone\","
						+"\"email\":\"test_email3@naver.com\","
						+"\"zipcode\":\"test_zipcode3\","
						+"\"addr\":\"test_addr3\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 회원가입성공
		resultActions = mockMvc.perform(post("/api/member/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+"\"id\":\"test_id0\","
						+"\"password\":\"testpassword3!\","
						+"\"name\":\"test3\","
						+"\"phone\":\"01033244343\","
						+"\"email\":\"test_email3@naver.com\","
						+"\"zipcode\":\"test_zipcode3\","
						+"\"addr\":\"test_addr3\""
						+ "}"));
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
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"1test_id1\","
						+ "\"password\":\"testpassword1!\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
				
		
		// 비밀번호 Valid
		resultActions = mockMvc.perform(post("/api/member/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"test_id1\","
						+ "\"password\":\"testpassword1\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 로그인 실패
		resultActions = mockMvc.perform(post("/api/member/login")
				.param("id", "test_id9")
				.param("password", "testpassword1!")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"test_id9\","
						+ "\"password\":\"testpassword1!\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 로그인 성공
		resultActions = mockMvc.perform(post("/api/member/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"test_id1\","
						+ "\"password\":\"testpassword1!\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data", Matchers.notNullValue()));
	}
	
	
	
	
	
	
	
	
	
	// 회원수정 페이지
	@Test
	public void testD회원수정페이지() throws Exception {
		ResultActions resultActions;
		
		// 회원정보 불러오기
		resultActions = mockMvc.perform(get("/api/member/loginupdate")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", Matchers.notNullValue()));
		
	}
	
	
	
	
	

	// 회원수정
	@Test
	public void testE회원수정() throws Exception {
		ResultActions resultActions;
		
		// 회원정보 불러오기
		resultActions = mockMvc.perform(put("/api/member/loginupdate")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+"\"id\":\"test_id1\","
						+"\"password\":\"testpassword1!\","
						+"\"name\":\"cname\","
						+"\"phone\":\"01033244343\","
						+"\"email\":\"test_email3@naver.com\","
						+"\"zipcode\":\"test_zipcode3\","
						+"\"addr\":\"test_addr3\""
						+ "}"));
		// 응답이 200 인지
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	
}
