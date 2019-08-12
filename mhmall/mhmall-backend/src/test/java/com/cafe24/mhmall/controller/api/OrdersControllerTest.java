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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	private String myAuthorization;

	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		

		ResultActions resultActions;
		
		
		// 사용자 로그인
		resultActions = mockMvc.perform(post("/api/member/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"test_id1\","
						+ "\"password\":\"test\""
						+ "}"));
		// 응답이 200 인지
		MvcResult mvcResult = resultActions
		.andExpect(status().isOk())
		.andReturn();

		// 로그인키 가져오기
		String content = mvcResult.getResponse().getContentAsString();
		JsonParser Parser = new JsonParser();
		JsonObject jsonObj = (JsonObject) Parser.parse(content);
		myAuthorization = jsonObj.get("data").getAsJsonObject().get("mockToken").getAsString();
	}
	
	// 비회원 주문
	@Test
	public void testA비회원주문() throws Exception {
		ResultActions resultActions;

		// 없는 옵션일 때
		resultActions = mockMvc.perform(post("/api/orders/guest")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"guestSession\":\"ODIJOSAIDPBV132012ID9V823V\","
						+ "\"guestName\":\"guest\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"snrnsnrn1!\","
						+ "\"optionNos\":[999],"
						+ "\"optionCnts\":[5]"
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 재고가 부족할 때
		resultActions = mockMvc.perform(post("/api/orders/guest")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"guestSession\":\"ODIJOSAIDPBV132012ID9V823V\","
						+ "\"guestName\":\"guest\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"snrnsnrn1!\","
						+ "\"optionNos\":[1],"
						+ "\"optionCnts\":[11]"
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 판매중인 상품이 아닐 때
		resultActions = mockMvc.perform(post("/api/orders/guest")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"guestSession\":\"ODIJOSAIDPBV132012ID9V823V\","
						+ "\"guestName\":\"guest\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"snrnsnrn1!\","
						+ "\"optionNos\":[3],"
						+ "\"optionCnts\":[2]"
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공, 주문번호를 리턴하는지
		resultActions = mockMvc.perform(post("/api/orders/guest")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"guestSession\":\"ODIJOSAIDPBV132012ID9V823V\","
						+ "\"guestName\":\"guest\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"snrnsnrn1!\","
						+ "\"optionNos\":[1,2],"
						+ "\"optionCnts\":[1,1]"
						+ "}"));
		// 응답이 200 인지
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", Matchers.notNullValue()));
		
	}
	
	
	

	// 비회원 주문 완료
	@Test
	public void testB비회원주문완료() throws Exception {
		ResultActions resultActions;
		

		// 주문번호 Valid
		resultActions = mockMvc.perform(put("/api/orders/guest")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"\","
						+ "\"guestPassword\":\"guestpw3!\","
						
						+ "\"toName\":\"guest\","
						+ "\"toPhone\":\"01000000001\","
						+ "\"toZipcode\":\"12345\","
						+ "\"toAddr\":\"addraddr\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 비밀번호 Valid
		resultActions = mockMvc.perform(put("/api/orders/guest")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000259\","
						+ "\"guestPassword\":\"guestpw3\","
						
						+ "\"toName\":\"guest\","
						+ "\"toPhone\":\"01000000001\","
						+ "\"toZipcode\":\"12345\","
						+ "\"toAddr\":\"addraddr\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 존재하지 않는 주문
		resultActions = mockMvc.perform(put("/api/orders/guest")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_999999\","
						+ "\"guestPassword\":\"guestpw3!\","
						
						+ "\"toName\":\"guest\","
						+ "\"toPhone\":\"01000000001\","
						+ "\"toZipcode\":\"12345\","
						+ "\"toAddr\":\"addraddr\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 주문은 존재하지만 비회원 비밀번호가 틀린 경우
		resultActions = mockMvc.perform(put("/api/orders/guest")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000259\","
						+ "\"guestPassword\":\"guestpw123!\","
						
						+ "\"toName\":\"guest\","
						+ "\"toPhone\":\"01000000001\","
						+ "\"toZipcode\":\"12345\","
						+ "\"toAddr\":\"addraddr\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(put("/api/orders/guest")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000259\","
						+ "\"guestPassword\":\"guestpw3!\","
						
						+ "\"toName\":\"guest\","
						+ "\"toPhone\":\"01000000001\","
						+ "\"toZipcode\":\"12345\","
						+ "\"toAddr\":\"addraddr\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.toName", is("guest")))
		.andExpect(jsonPath("$.data.toPhone", is("01000000001")))
		.andExpect(jsonPath("$.data.toZipcode", is("12345")))
		.andExpect(jsonPath("$.data.toAddr", is("addraddr")));
		
	}
	
	
	
	
	

	// 회원 주문
	@Test
	public void testC회원주문() throws Exception {
		ResultActions resultActions;

		// 없는 옵션일 때
		resultActions = mockMvc.perform(post("/api/orders/member")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"optionNos\":[999],"
						+ "\"optionCnts\":[5]"
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 판매중인 상품이 아닐 때
		resultActions = mockMvc.perform(post("/api/orders/member")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"optionNos\":[3],"
						+ "\"optionCnts\":[2]"
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 재고가 부족할 때
		resultActions = mockMvc.perform(post("/api/orders/member")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"optionNos\":[1],"
						+ "\"optionCnts\":[11]"
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공, 주문번호를 리턴하는지
		resultActions = mockMvc.perform(post("/api/orders/member")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"optionNos\":[1],"
						+ "\"optionCnts\":[1]"
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", Matchers.notNullValue()));
		
	}
	
	
	

	// 회원 주문 완료
	@Test
	public void testD회원주문완료() throws Exception {
		ResultActions resultActions;
		

		// 주문번호 Valid
		resultActions = mockMvc.perform(put("/api/orders/member")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"\","
						
						+ "\"toName\":\"guet\","
						+ "\"toPhone\":\"01000000001\","
						+ "\"toZipcode\":\"12345\","
						+ "\"toAddr\":\"addraddr\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 존재하지 않는 주문
		resultActions = mockMvc.perform(put("/api/orders/member")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_999999\","
						
						+ "\"toName\":\"guet\","
						+ "\"toPhone\":\"01000000001\","
						+ "\"toZipcode\":\"12345\","
						+ "\"toAddr\":\"addraddr\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 주문은 존재하지만 본인의 주문이 아닌 경우
		resultActions = mockMvc.perform(put("/api/orders/member")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000259\","
						
						+ "\"toName\":\"guet\","
						+ "\"toPhone\":\"01000000001\","
						+ "\"toZipcode\":\"12345\","
						+ "\"toAddr\":\"addraddr\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(put("/api/orders/member")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000260\","
						
						+ "\"toName\":\"memb\","
						+ "\"toPhone\":\"01000000005\","
						+ "\"toZipcode\":\"12345\","
						+ "\"toAddr\":\"addraddr\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.toName", is("memb")))
		.andExpect(jsonPath("$.data.toPhone", is("01000000005")))
		.andExpect(jsonPath("$.data.toZipcode", is("12345")))
		.andExpect(jsonPath("$.data.toAddr", is("addraddr")));
		
	}
	
	
	
	

	// 비회원 주문 상세
	@Test
	public void testE비회원주문상세() throws Exception {
		ResultActions resultActions;

		// 존재하지 않거나 비회원 비밀번호가 다른 경우
		resultActions = mockMvc.perform(post("/api/orders/guest/view")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestPassword\":\"guestpw12!\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(post("/api/orders/guest/view")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestPassword\":\"guestpw1!\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.ordersVo.ordersNo", is("2019-07-11_000257")))
		.andExpect(jsonPath("$.data.ordersItemList[0].ordersNo", is("2019-07-11_000257")));
		
	}
	
	
	
	

	// 회원 주문 리스트
	@Test
	public void testF회원주문리스트() throws Exception {
		ResultActions resultActions;

		// 성공
		resultActions = mockMvc.perform(get("/api/orders/member/list")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].ordersNo", is("2019-07-11_000256")))
		.andExpect(jsonPath("$.data[0].status", is("입금대기")))
		.andExpect(jsonPath("$.data[0].memberId", is("test_id1")));
		
	}
	
	

	// 회원 주문 상세
	@Test
	public void testG회원주문상세() throws Exception {
		ResultActions resultActions;

		// 존재하지 않거나 잘못된 주문
		resultActions = mockMvc.perform(get("/api/orders/member/view/{ordersNo}", "2019-07-11_0002596")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 성공
		resultActions = mockMvc.perform(get("/api/orders/member/view/{ordersNo}", "2019-07-11_000256")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.ordersNo", is("2019-07-11_000256")))
		.andExpect(jsonPath("$.data.ordersItemList[0].ordersNo", is("2019-07-11_000256")));
		
	}
	
	
	
	

	// 비회원 주문 취소
	@Test
	public void testH비회원주문취소() throws Exception {
		ResultActions resultActions;

		// 존재하지 않는 주문
		resultActions = mockMvc.perform(put("/api/orders/guest/cancel")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_99999\","
						+ "\"guestPassword\":\"guestpw1!\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 잘못된 비회원 비밀번호
		resultActions = mockMvc.perform(put("/api/orders/guest/cancel")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestPassword\":\"guestpw12!\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 취소할 수 없는 상태
		resultActions = mockMvc.perform(put("/api/orders/guest/cancel")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestPassword\":\"guestpw1!\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 성공
		resultActions = mockMvc.perform(put("/api/orders/guest/cancel")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000261\","
						+ "\"guestPassword\":\"guestpw4!\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	
	

	// 회원 주문 취소
	@Test
	public void testI회원주문취소() throws Exception {
		ResultActions resultActions;

		// 존재하지 않는 주문
		resultActions = mockMvc.perform(put("/api/orders/member/cancel")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_99999\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 회원 본인의 것이 아닐 때
		resultActions = mockMvc.perform(put("/api/orders/member/cancel")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000258\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 성공
		resultActions = mockMvc.perform(put("/api/orders/member/cancel")
				.header("MyAuthorization", "Basic " + myAuthorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000256\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	

	// 주문번호 찾기
	@Test
	public void testJ주문번호찾기() throws Exception {
		ResultActions resultActions;

		// 비회원 이름 Valid
		resultActions = mockMvc.perform(post("/api/orders/guest/ordersno")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"guestName\":\"testtesttesttest1\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"guestpw1!\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 없는 주문정보
		resultActions = mockMvc.perform(post("/api/orders/guest/ordersno")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"guestName\":\"none1\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"guestpw1!\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.length()", is(0)));
		
		
		// 성공
		resultActions = mockMvc.perform(post("/api/orders/guest/ordersno")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"guestName\":\"test1\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"guestpw1!\""
						+ "}"));
		// 응답이200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].ordersNo", is("2019-07-11_000257")))
		.andExpect(jsonPath("$.data[0].status", is("결제완료")));
		
	}
	
	

	// 비회원 주문 비밀번호 찾기
	@Test
	public void testK비회원주문비밀번호찾기() throws Exception {
		ResultActions resultActions;

		// 비회원 이름 Valid
		resultActions = mockMvc.perform(post("/api/orders/guest/password")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestName\":\"testtesttest1\","
						+ "\"guestPhone\":\"01000000001\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 존재하지 않는 비회원
		resultActions = mockMvc.perform(post("/api/orders/guest/password")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestName\":\"taad\","
						+ "\"guestPhone\":\"01000000001\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(false)));
		

		// 성공
		resultActions = mockMvc.perform(post("/api/orders/guest/password")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestName\":\"test1\","
						+ "\"guestPhone\":\"01000000001\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	
	

	// 비회원 주문 비밀번호 변경
	@Test
	public void testL비회원주문비밀번호변경() throws Exception {
		ResultActions resultActions;

		// 비회원 이름 Valid
		resultActions = mockMvc.perform(post("/api/orders/guest/password")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestName\":\"testtesttest1\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"snrnsnrn1!\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 존재하지 않는 비회원
		resultActions = mockMvc.perform(post("/api/orders/guest/password")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestName\":\"taad\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"snrnsnrn1!\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(false)));
		

		// 성공
		resultActions = mockMvc.perform(post("/api/orders/guest/password")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"ordersNo\":\"2019-07-11_000257\","
						+ "\"guestName\":\"test1\","
						+ "\"guestPhone\":\"01000000001\","
						+ "\"guestPassword\":\"snrnsnrn1!\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	
	
	
	
}
