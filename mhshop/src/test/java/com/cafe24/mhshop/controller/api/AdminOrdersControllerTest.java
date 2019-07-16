package com.cafe24.mhshop.controller.api;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminOrdersControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	// 주문 리스트
	@Test
	public void testA주문리스트페이지() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/admin/orders/list").contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		
		.andExpect(jsonPath("$.data[2].status", is("입금대기")))
		.andExpect(jsonPath("$.data[2].money", is(10000)))
		.andExpect(jsonPath("$.data[2].trackingNum", Matchers.nullValue()))
		.andExpect(jsonPath("$.data[2].toName", is("test_name1")))
		.andExpect(jsonPath("$.data[2].toPhone", is("01000000001")))
		.andExpect(jsonPath("$.data[2].toZipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data[2].toAddr", is("test_addr1")))
		.andExpect(jsonPath("$.data[2].memberId", is("test_id1")));
		
	}
	
	// 주문 상세보기
	@Test
	public void testB주문상세보기() throws Exception {
		ResultActions resultActions;
		
		
		// 회원일 때
		resultActions = mockMvc.perform(get("/api/admin/orders/view/{ordersNo}", "2019-07-11_000256").contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		
		.andExpect(jsonPath("$.data.ordersVo.status", is("입금대기")))
		.andExpect(jsonPath("$.data.ordersVo.money", is(10000)))
		.andExpect(jsonPath("$.data.ordersVo.trackingNum", Matchers.nullValue()))
		.andExpect(jsonPath("$.data.ordersVo.toName", is("test_name1")))
		.andExpect(jsonPath("$.data.ordersVo.toPhone", is("01000000001")))
		.andExpect(jsonPath("$.data.ordersVo.toZipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data.ordersVo.toAddr", is("test_addr1")))
		.andExpect(jsonPath("$.data.ordersVo.memberId", is("test_id1")))
		.andExpect(jsonPath("$.data.ordersVo.bankName", is("국민")))
		.andExpect(jsonPath("$.data.ordersVo.bankNum", is("123456789")))
		
		.andExpect(jsonPath("$.data.guestVo", Matchers.nullValue()))
		
		.andExpect(jsonPath("$.data.memberVo.id", is("test_id1")))
		.andExpect(jsonPath("$.data.memberVo.name", is("test1")))
		.andExpect(jsonPath("$.data.memberVo.phone", is("01000000001")))
		.andExpect(jsonPath("$.data.memberVo.email", is("test_email1@naver.com")))
		.andExpect(jsonPath("$.data.memberVo.zipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data.memberVo.addr", is("test_addr1")))
		.andExpect(jsonPath("$.data.memberVo.role", is("USER")))

		.andExpect(jsonPath("$.data.ordersItemList[0].no", is(1)))
		.andExpect(jsonPath("$.data.ordersItemList[0].optionNo", is(1)))
		.andExpect(jsonPath("$.data.ordersItemList[0].itemName", is("test_item1")))
		.andExpect(jsonPath("$.data.ordersItemList[0].itemThumbnail", is("test_thumbnail1")))
		.andExpect(jsonPath("$.data.ordersItemList[0].itemOptionDetail1", is("파란색")))
		.andExpect(jsonPath("$.data.ordersItemList[0].itemOptionDetail2", is("L")))
		.andExpect(jsonPath("$.data.ordersItemList[0].money", is(10000)))
		.andExpect(jsonPath("$.data.ordersItemList[0].cnt", is(1)));
		
		
		// 비회원일 때
		resultActions = mockMvc.perform(get("/api/admin/orders/view/{ordersNo}", "2019-07-11_000257").contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))

		.andExpect(jsonPath("$.data.guestVo.guestName", is("test_guest1")))
		.andExpect(jsonPath("$.data.guestVo.guestPhone", is("01000000001")));
		
	}
	

	//무통장 결제확인 상태변경
	@Test
	public void testC무통장결제확인상태변경() throws Exception {
		ResultActions resultActions;

		// 입금대기 상태가 아닐 때 실패
		resultActions = mockMvc.perform(put("/api/admin/orders/paycheck/{ordersNo}", "2019-07-11_000258")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 입금대기 상태일 때 성공
		resultActions = mockMvc.perform(put("/api/admin/orders/paycheck/{ordersNo}", "2019-07-11_000256")
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
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 운송장번호등록 성공
		resultActions = mockMvc.perform(put("/api/admin/orders/tnumcheck/{ordersNo}", "2019-07-11_000257")
				.param("trackingNum", "999988887777")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
}
