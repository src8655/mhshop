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
public class OrdersControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private String mockToken;

	
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
		mockToken = jsonObj.get("data").getAsString();
	}
	
	// 비회원 주문
	@Test
	public void testA비회원주문() throws Exception {
		ResultActions resultActions;

		// 없는 옵션일 때
		resultActions = mockMvc.perform(post("/api/orders/guest")
				.param("guestName", "guest")
				.param("guestPhone", "01000000001")
				.param("guestPassword", "snrnsnrn1!")

				.param("optionNos", "999")
				.param("optionCnts", "5")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 재고가 부족할 때
		resultActions = mockMvc.perform(post("/api/orders/guest")
				.param("guestName", "guest")
				.param("guestPhone", "01000000001")
				.param("guestPassword", "snrnsnrn1!")

				.param("optionNos", "1")
				.param("optionCnts", "11")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공, 주문번호를 리턴하는지
		resultActions = mockMvc.perform(post("/api/orders/guest")
				.param("guestName", "guest")
				.param("guestPhone", "01000000001")
				.param("guestPassword", "snrnsnrn1!")

				.param("optionNos", "1")
				.param("optionCnts", "1")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", Matchers.notNullValue()));
		
	}
	
	
	

	// 비회원 주문 완료
	@Test
	public void testB비회원주문완료() throws Exception {
		ResultActions resultActions;
		

		// 주문번호 Valid
		resultActions = mockMvc.perform(post("/api/orders/guest/post")
				.param("ordersNo", "")
				.param("guestPassword", "guestpw3!")
				
				.param("toName", "guest")
				.param("toPhone", "01000000001")
				.param("toZipcode", "12345")
				.param("toAddr", "addraddr")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 비밀번호 Valid
		resultActions = mockMvc.perform(post("/api/orders/guest/post")
				.param("ordersNo", "2019-07-11_000259")
				.param("guestPassword", "guestpw3")
				
				.param("toName", "guest")
				.param("toPhone", "01000000001")
				.param("toZipcode", "12345")
				.param("toAddr", "addraddr")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 존재하지 않는 주문
		resultActions = mockMvc.perform(post("/api/orders/guest/post")
				.param("ordersNo", "2019-07-11_999999")
				.param("guestPassword", "guestpw3!")
				
				.param("toName", "guest")
				.param("toPhone", "01000000001")
				.param("toZipcode", "12345")
				.param("toAddr", "addraddr")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 주문은 존재하지만 비회원 비밀번호가 틀린 경우
		resultActions = mockMvc.perform(post("/api/orders/guest/post")
				.param("ordersNo", "2019-07-11_000259")
				.param("guestPassword", "guestpw123!")
				
				.param("toName", "guest")
				.param("toPhone", "01000000001")
				.param("toZipcode", "12345")
				.param("toAddr", "addraddr")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(post("/api/orders/guest/post")
				.param("ordersNo", "2019-07-11_000259")
				.param("guestPassword", "guestpw3!")
				
				.param("toName", "guest")
				.param("toPhone", "01000000001")
				.param("toZipcode", "12345")
				.param("toAddr", "addraddr")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.toName", is("guest")))
		.andExpect(jsonPath("$.data.toPhone", is("01000000001")))
		.andExpect(jsonPath("$.data.toZipcode", is("12345")))
		.andExpect(jsonPath("$.data.toAddr", is("addraddr")));
		
	}
	
	
	
}
