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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback(value = true)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminMemberControllerTest {
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
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"test_id2\","
						+ "\"password\":\"testpassword2!\""
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
	
	
	// 회원 리스트
	@Test
	public void testA관리자회원리스트페이지() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/admin/member/list")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 데이터 확인
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		
		.andExpect(jsonPath("$.data[0].id", is("test_id2")))
		.andExpect(jsonPath("$.data[0].name", is("test2")))
		.andExpect(jsonPath("$.data[0].phone", is("01000000002")))
		.andExpect(jsonPath("$.data[0].email", is("test_email2@naver.com")))
		.andExpect(jsonPath("$.data[0].zipcode", is("test_zipcode2")))
		.andExpect(jsonPath("$.data[0].addr", is("test_addr2")))
		.andExpect(jsonPath("$.data[0].role", is("ROLE_ADMIN")))
		
		.andExpect(jsonPath("$.data[1].id", is("test_id1")))
		.andExpect(jsonPath("$.data[1].name", is("test1")))
		.andExpect(jsonPath("$.data[1].phone", is("01000000001")))
		.andExpect(jsonPath("$.data[1].email", is("test_email1@naver.com")))
		.andExpect(jsonPath("$.data[1].zipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data[1].addr", is("test_addr1")))
		.andExpect(jsonPath("$.data[1].role", is("ROLE_USER")));
		
	}
	
	
	

	// 회원 상세보기
	@Test
	public void testB회원상세보기() throws Exception {
		ResultActions resultActions;
		
		// 아이디 Valid
		resultActions = mockMvc.perform(get("/api/admin/member/view/{id}", "1test_id1")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 없는 아이디
		resultActions = mockMvc.perform(get("/api/admin/member/view/{id}", "test_id9999")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", Matchers.nullValue()));
		
		
		// 있는 아이디
		resultActions = mockMvc.perform(get("/api/admin/member/view/{id}", "test_id1")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.id", is("test_id1")))
		.andExpect(jsonPath("$.data.name", is("test1")))
		.andExpect(jsonPath("$.data.phone", is("01000000001")))
		.andExpect(jsonPath("$.data.email", is("test_email1@naver.com")))
		.andExpect(jsonPath("$.data.zipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data.addr", is("test_addr1")))
		.andExpect(jsonPath("$.data.role", is("ROLE_USER")));
	}


	

	// 회원 삭제
	@Test
	public void testC회원삭제() throws Exception {
		ResultActions resultActions;
		
		// 아이디 Valid
		resultActions = mockMvc.perform(delete("/api/admin/member")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"1test_id1\""
						+ "}"));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 삭제 실패(없는 아이디)
		resultActions = mockMvc.perform(delete("/api/admin/member")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"test_id3\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(false)));
		

		// 삭제 성공
		resultActions = mockMvc.perform(delete("/api/admin/member")
				.header("Authorization", "Basic " + authorization)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"id\":\"test_id2\""
						+ "}"));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
}
