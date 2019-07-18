package com.cafe24.mhshop.controller.api;

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
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mhshop.config.AppConfig;
import com.cafe24.mhshop.config.TestWebConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@Rollback(value = true)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminCategoryControllerTest {
	private MockMvc mockMvc;
	private String mockToken;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	
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
		mockToken = jsonObj.get("data").getAsString();
	}


	// 카테고리 리스트
	@Test
	public void testA카테고리리스트() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/admin/category/list")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		resultActions.andExpect(status().isOk())
		
		.andExpect(jsonPath("$.data[0].no", is(1)))
		.andExpect(jsonPath("$.data[0].name", is("test_category1")))
		.andExpect(jsonPath("$.data[1].no", is(2)))
		.andExpect(jsonPath("$.data[1].name", is("test_category2")));
	}
	
	// 관리자 카테고리 등록
	@Test
	public void testB카테고리작성() throws Exception {
		ResultActions resultActions;
		
		// 카테고리명 Valid
		resultActions = mockMvc.perform(post("/api/admin/category")
				.param("name", "")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions.andExpect(status().isBadRequest());

		
		// 작성성공
		resultActions = mockMvc.perform(post("/api/admin/category")
				.param("name", "category_name3")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	// 관리자 카테고리 수정
	@Test
	public void testC카테고리수정() throws Exception {
		ResultActions resultActions;
		
		// 카테고리번호 Valid
		resultActions = mockMvc.perform(put("/api/admin/category/{no}/{name}", "aa", "test")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions.andExpect(status().isBadRequest());
		
		
		// 카테고리번호가 없는 번호일 때
		resultActions = mockMvc.perform(put("/api/admin/category/{no}/{name}", 999999L, "test")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(false)));
		

		// 카테고리 수정 완료
		resultActions = mockMvc.perform(put("/api/admin/category/{no}/{name}", 1L, "test")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	// 관리자 카테고리 삭제
	@Test
	public void testD카테고리삭제() throws Exception {
		ResultActions resultActions;
		
		// 카테고리번호 Valid
		resultActions = mockMvc.perform(delete("/api/admin/category/{no}", "aa")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions.andExpect(status().isBadRequest());
		

		// 카테고리에 속한 상품이 있을 경우
		resultActions = mockMvc.perform(delete("/api/admin/category/{no}", 1L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions.andExpect(status().isBadRequest());
		
		
		// 없는 카테고리 번호로 실패
		resultActions = mockMvc.perform(delete("/api/admin/category/{no}", 9999L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(false)));
		
		
		// 카테고리 삭제 완료
		resultActions = mockMvc.perform(delete("/api/admin/category/{no}", 2L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	
	
}
