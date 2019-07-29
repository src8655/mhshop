package com.cafe24.mhmall.controller.api;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback(value = true)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasketControllerTest {
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
				.param("id", "test_id1")
				.param("password", "testpassword1!")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		MvcResult mvcResult = resultActions
		.andExpect(status().isOk())
		.andReturn();

		// 로그인키 가져오기
		String content = mvcResult.getResponse().getContentAsString();
		JsonParser Parser = new JsonParser();
		JsonObject jsonObj = (JsonObject) Parser.parse(content);
		authorization = jsonObj.get("data").getAsString();
	}
	
	// 비회원 장바구니 리스트
	@Test
	public void testA비회원장바구니리스트() throws Exception {
		ResultActions resultActions;

		// 세션 Valid
		resultActions = mockMvc.perform(get("/api/basket/guest")
				.param("guestSession", "")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 성공
		resultActions = mockMvc.perform(get("/api/basket/guest")
				.param("guestSession", "ODIJOSAIDPBV132012ID9V823V")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].no", is(1)))
		.andExpect(jsonPath("$.data[0].optionNo", is(1)))
		.andExpect(jsonPath("$.data[0].memberId", Matchers.nullValue()))
		.andExpect(jsonPath("$.data[0].guestSession", is("ODIJOSAIDPBV132012ID9V823V")))
		.andExpect(jsonPath("$.data[0].cnt", is(5)))
		.andExpect(jsonPath("$.data[0].optionNames", is("파란색 L")))
		.andExpect(jsonPath("$.data[0].itemName", is("test_item1")))
		.andExpect(jsonPath("$.data[0].money", is(50000)));
		
	}
	
	
	
	

	// 비회원 장바구니 추가
	@Test
	public void testB비회원장바구니추가() throws Exception {
		ResultActions resultActions;

		// 세션 Valid
		resultActions = mockMvc.perform(post("/api/basket/guest")
				.param("guestSession", "")
				.param("optionNo", "1")
				.param("cnt", "7")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 없는 옵션일 때 실패
		resultActions = mockMvc.perform(post("/api/basket/guest")
				.param("guestSession", "ODIJOSAIDPBV132012ID9V823V")
				.param("optionNo", "99")
				.param("cnt", "7")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 재고가 부족할 때 실패
		resultActions = mockMvc.perform(post("/api/basket/guest")
				.param("guestSession", "ODIJOSAIDPBV132012ID9V823V")
				.param("optionNo", "1")
				.param("cnt", "99")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(post("/api/basket/guest")
				.param("guestSession", "ODIJOSAIDPBV132012ID9V823V")
				.param("optionNo", "1")
				.param("cnt", "7")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	


	// 비회원 장바구니 삭제
	@Test
	public void testC비회원장바구니삭제() throws Exception {
		ResultActions resultActions;

		// 세션 Valid
		resultActions = mockMvc.perform(delete("/api/basket/guest")
				.param("guestSession", "")
				.param("no", "1")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(delete("/api/basket/guest")
				.param("guestSession", "ODIJOSAIDPBV132012ID9V823V")
				.param("no", "1")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	
	
	// 비회원 장바구니 수정
	@Test
	public void testD비회원장바구니수정() throws Exception {
		ResultActions resultActions;

		// 세션 Valid
		resultActions = mockMvc.perform(put("/api/basket/guest")
				.param("guestSession", "")
				.param("no", "1")
				.param("cnt", "1")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 없는 장바구니
		resultActions = mockMvc.perform(put("/api/basket/guest")
				.param("guestSession", "ODIJOSAIDPBV132012ID9V823V")
				.param("no", "99")
				.param("cnt", "1")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(put("/api/basket/guest")
				.param("guestSession", "ODIJOSAIDPBV132012ID9V823V")
				.param("no", "1")
				.param("cnt", "1")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	
	

	// 회원 장바구니 리스트
	@Test
	public void testE회원장바구니리스트() throws Exception {
		ResultActions resultActions;


		// 성공
		resultActions = mockMvc.perform(get("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].no", is(3)))
		.andExpect(jsonPath("$.data[0].optionNo", is(1)))
		.andExpect(jsonPath("$.data[0].memberId", is("test_id1")))
		.andExpect(jsonPath("$.data[0].guestSession", Matchers.nullValue()))
		.andExpect(jsonPath("$.data[0].cnt", is(2)))
		.andExpect(jsonPath("$.data[0].optionNames", is("파란색 L")))
		.andExpect(jsonPath("$.data[0].itemName", is("test_item1")))
		.andExpect(jsonPath("$.data[0].money", is(20000)));
		
	}
	
	
	
	

	// 회원 장바구니 추가
	@Test
	public void testF회원장바구니추가() throws Exception {
		ResultActions resultActions;
		
		// 없는 옵션일 때 실패
		resultActions = mockMvc.perform(post("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.param("optionNo", "99")
				.param("cnt", "7")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 재고가 부족할 때 실패
		resultActions = mockMvc.perform(post("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.param("optionNo", "1")
				.param("cnt", "99")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(post("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.param("optionNo", "1")
				.param("cnt", "7")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	
	
	

	// 회원 장바구니 삭제
	@Test
	public void testG회원장바구니삭제() throws Exception {
		ResultActions resultActions;

		// 장바구니 번호 Valid
		resultActions = mockMvc.perform(delete("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.param("no", "")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(delete("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.param("no", "3")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	

	// 회원 장바구니 수정
	@Test
	public void testH회원장바구니수정() throws Exception {
		ResultActions resultActions;

		// 장바구니번호 Valid
		resultActions = mockMvc.perform(put("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.param("no", "")
				.param("cnt", "1")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 없는 장바구니
		resultActions = mockMvc.perform(put("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.param("no", "99")
				.param("cnt", "1")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 재고가 부족할 때
		resultActions = mockMvc.perform(put("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.param("no", "3")
				.param("cnt", "99")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(put("/api/basket/member")
				.header("Authorization", "Basic " + authorization)
				.param("no", "3")
				.param("cnt", "6")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
}
