package com.cafe24.mhmall.controller.api;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
public class AdminOrdersControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private String authorization;

	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		

		ResultActions resultActions;
		
		// 관리자 로그인
		resultActions = mockMvc.perform(post("/api/member/login")
				.param("id", "test_id2")
				.param("password", "testpassword2!")
				.contentType(MediaType.APPLICATION_JSON));
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
	
	
	// 주문 리스트
	@Test
	public void testA주문리스트페이지() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/admin/orders/list")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		
		.andExpect(jsonPath("$.data[5].status", is("입금대기")))
		.andExpect(jsonPath("$.data[5].money", is(10000)))
		.andExpect(jsonPath("$.data[5].trackingNum", Matchers.nullValue()))
		.andExpect(jsonPath("$.data[5].toName", is("test_name1")))
		.andExpect(jsonPath("$.data[5].toPhone", is("01000000001")))
		.andExpect(jsonPath("$.data[5].toZipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data[5].toAddr", is("test_addr1")))
		.andExpect(jsonPath("$.data[5].memberId", is("test_id1")));
		
	}
	
	// 주문 상세
	@Test
	public void testB주문상세() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/admin/orders/view/{ordersNo}", "2019-07-11_000256")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		
		.andExpect(jsonPath("$.data.status", is("입금대기")))
		.andExpect(jsonPath("$.data.money", is(10000)))
		.andExpect(jsonPath("$.data.trackingNum", Matchers.nullValue()))
		.andExpect(jsonPath("$.data.toName", is("test_name1")))
		.andExpect(jsonPath("$.data.toPhone", is("01000000001")))
		.andExpect(jsonPath("$.data.toZipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data.toAddr", is("test_addr1")))
		.andExpect(jsonPath("$.data.memberId", is("test_id1")))
		.andExpect(jsonPath("$.data.bankName", is("국민")))
		.andExpect(jsonPath("$.data.bankNum", is("123456789")));
	}
	

	//무통장 결제확인 상태변경
	@Test
	public void testC무통장결제확인상태변경() throws Exception {
		ResultActions resultActions;
		
		// 입금대기 상태가 아닐 때 실패
		resultActions = mockMvc.perform(put("/api/admin/orders/paycheck/{ordersNo}", "2019-07-11_000258")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 입금대기 상태일 때 성공
		resultActions = mockMvc.perform(put("/api/admin/orders/paycheck/{ordersNo}", "2019-07-11_000256")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	

	// 운송장번호 등록 요청
	@Test
	public void testD운송장번호등록() throws Exception {
		ResultActions resultActions;
		
		// 운송장번호 Valid
		resultActions = mockMvc.perform(put("/api/admin/orders/tnumcheck/{ordersNo}", "2019-07-11_000257")
				.param("trackingNum", "")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 운송장번호등록 성공
		resultActions = mockMvc.perform(put("/api/admin/orders/tnumcheck/{ordersNo}", "2019-07-11_000257")
				.param("trackingNum", "999988887777")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	
	
	// 비회원상세
	@Test
	public void testE비회원상세() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/admin/orders/guest/{ordersNo}", "2019-07-11_000257")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.guestName", is("test1")))
		.andExpect(jsonPath("$.data.guestPhone", is("01000000001")));
		
	}
	
	

	// 주문상품리스트
	@Test
	public void testF주문상품리스트() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/admin/orders/item/{ordersNo}", "2019-07-11_000256")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].no", is(1)))
		.andExpect(jsonPath("$.data[0].optionNo", is(1)))
		.andExpect(jsonPath("$.data[0].itemName", is("test_item1")))
		.andExpect(jsonPath("$.data[0].itemThumbnail", is("test_thumbnail1")))
		.andExpect(jsonPath("$.data[0].itemOptionDetail1", is("파란색")))
		.andExpect(jsonPath("$.data[0].itemOptionDetail2", is("L")))
		.andExpect(jsonPath("$.data[0].money", is(10000)))
		.andExpect(jsonPath("$.data[0].cnt", is(1)));
	}
	
}
