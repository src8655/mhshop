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

import io.swagger.annotations.ApiImplicitParam;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback(value = true)
@Transactional
public class ItemControllerTest {
	private MockMvc mockMvc;
	private String mockToken;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	
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
	
	
	// 상품 상세
	@Test
	public void testA상품상세() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/item/{no}",1L)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.no", is(1)))
		.andExpect(jsonPath("$.data.name", is("test_item1")))
		.andExpect(jsonPath("$.data.description", is("test_description1")))
		.andExpect(jsonPath("$.data.money", is(10000)))
		.andExpect(jsonPath("$.data.thumbnail", is("test_thumbnail1")))
		.andExpect(jsonPath("$.data.display", is("FALSE")))
		.andExpect(jsonPath("$.data.categoryNo", is(1)));
		
	}
	
	
	// 상품이미지 리스트
	@Test
	public void testB상품이미지리스트() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/item/img/{itemNo}",1L)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].no", is(1)))
		.andExpect(jsonPath("$.data[0].itemNo", is(1)))
		.andExpect(jsonPath("$.data[0].itemImg", is("test_img1")))

		.andExpect(jsonPath("$.data[1].no", is(2)))
		.andExpect(jsonPath("$.data[1].itemNo", is(1)))
		.andExpect(jsonPath("$.data[1].itemImg", is("test_img2")));
		
	}
	
	
	// 옵션리스트
	@Test
	public void testC옵션리스트() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/item/option/{itemNo}", 1L)
				.param("optionDetailNo1", "")
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].no", is(1)))
		.andExpect(jsonPath("$.data[0].itemNo", is(1)))
		.andExpect(jsonPath("$.data[0].optionDetailNo1", is(1)))
		.andExpect(jsonPath("$.data[0].optionDetailNo2", is(2)))
		.andExpect(jsonPath("$.data[0].cnt", is(10)));
		
	}
	
	
	
	
}
