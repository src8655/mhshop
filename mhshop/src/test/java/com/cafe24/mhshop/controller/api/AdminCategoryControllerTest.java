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

import com.cafe24.mhshop.config.AppConfig;
import com.cafe24.mhshop.config.TestWebConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class AdminCategoryControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeClass
	public static void classSetup() {
		// DB 초기화
	}
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		

		// DB category, item 테이블 초기화
		// DB 테스트용 데이터 insert

		// category insert1
		// ("no", 1)
		// ("name", "test_category1")
		
		// category insert2
		// ("no", 2)
		// ("name", "test_category2")
		
		
		
		// item insert1
		// ("no", 1)
		// ("name", "test_item1")
		// ("description", "test_description1")
		// ("money", 10000)
		// ("thumbnail", "test_thumbnail1")
		// ("display", "FALSE")
		// ("category_no", 1)
		
		// item insert2
		// ("no", 2)
		// ("name", "test_item2")
		// ("description", "test_description2")
		// ("money", 20000)
		// ("thumbnail", "test_thumbnail2")
		// ("display", "FALSE")
		// ("category_no", 1)
	}
	
	
	// [관리자 카테고리 작성 페이지]
	@Test
	public void testACategoryWriteForm() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/admincategory/write_form").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.forward", is("admin/category_write_form")));
		
	}
	
	
	// 관리자 카테고리 등록
	@Test
	public void testBCategoryWrite() throws Exception {
		ResultActions resultActions;
		
		
		// 중복된 카테고리인 경우
		resultActions = mockMvc.perform(post("/api/admincategory/write")
				.param("name", "test_category1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 리턴한 카테고리 확인
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		
		.andExpect(jsonPath("$.data.categoryVo", Matchers.nullValue()))
		
		.andExpect(jsonPath("$.data.redirect", is("/api/admincategory/category_list")));
		
		
		
		
		// 성공한 경우
		resultActions = mockMvc.perform(post("/api/admincategory/write")
				.param("name", "test_category3")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 리턴한 카테고리 확인
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))

		.andExpect(jsonPath("$.data.categoryVo", Matchers.notNullValue()))
		.andExpect(jsonPath("$.data.categoryVo.name", is("test_category3")))
		
		.andExpect(jsonPath("$.data.redirect", is("/api/admincategory/category_list")));
		
	}
	

	// 카테고리 리스트
	@Test
	public void testCCategoryList() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/admincategory/list").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 리턴한 리스트를 확인
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))

		.andExpect(jsonPath("$.data.categoryList[0].no", is(1)))
		.andExpect(jsonPath("$.data.categoryList[0].name", is("test_category1")))
		.andExpect(jsonPath("$.data.categoryList[1].no", is(2)))
		.andExpect(jsonPath("$.data.categoryList[1].name", is("test_category2")))
		
		.andExpect(jsonPath("$.data.forward", is("admin/category_list")));
		
	}
	
	

	// 관리자 카테고리 수정
	@Test
	public void testDCategoryEdit() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(put("/api/admincategory/edit")
				.param("no", "1")
				.param("name", "test_category")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 리턴한 카테고리 확인
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))

		.andExpect(jsonPath("$.data.result", is(true)))
		
		.andExpect(jsonPath("$.data.redirect", is("/api/admincategory/category_list")));
		
	}
	
	

	// 관리자 카테고리 삭제
	@Test
	public void testECategoryDelete() throws Exception {
		ResultActions resultActions;
		
		
		// 삭제 성공하는 경우
		resultActions = mockMvc.perform(delete("/api/admincategory/delete")
				.param("no", "2")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 리턴한 삭제결과 확인
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))

		.andExpect(jsonPath("$.data.result", is(true)))
		
		.andExpect(jsonPath("$.data.redirect", is("/api/admincategory/category_list")));
		
		
		

		// 카테고리에 속한 아이템이 존재하여 실패하는 경우
		resultActions = mockMvc.perform(delete("/api/admincategory/delete")
				.param("no", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail"))).andDo(print());
		
	}
	
	
	
	
	
	@AfterClass
	public static void finish() {
		// DB 초기화
	}
	
	
}
