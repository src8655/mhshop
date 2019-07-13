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

import com.cafe24.mhshop.config.TestAppConfig;
import com.cafe24.mhshop.config.TestWebConfig;

import io.swagger.annotations.ApiImplicitParam;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class AdminItemControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	// 관리자 상품 리스트
	@Test
	public void testAItemList() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/admin/item/list").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.forward", is("admin/item_list")));
		
	}
	

	// [관리자 상품 작성 페이지]
	@Test
	public void testBItemWriteForm() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/admin/item/write").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.forward", is("admin/item_write_form")));
		
	}
	
	// 관리자 상품 DB에 저장 이름 Valid
	@Test
	public void testCItemWriteNameValid() throws Exception {
		ResultActions resultActions;
		

		// 성공했을 때
		resultActions = mockMvc.perform(post("/api/admin/item/write")
				.param("name", "")
				.param("description", "test_description2")
				.param("money", "30000")
				.param("thumbnail", "test_thumbnail3")
				.param("categoryNo", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	

	// 관리자 상품 DB에 저장 금액 Valid
	@Test
	public void testCItemWriteMoneyValid() throws Exception {
		ResultActions resultActions;
		

		// 성공했을 때
		resultActions = mockMvc.perform(post("/api/admin/item/write")
				.param("name", "test_name")
				.param("description", "test_description2")
				.param("money", "")
				.param("thumbnail", "test_thumbnail3")
				.param("categoryNo", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}

	
	// 관리자 상품 DB에 수정 NO Valid
	@Test
	public void testFItemEditNoValid() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(put("/api/admin/item/edit")
				.param("no", "")
				.param("name", "test_item11")
				.param("description", "test_description11")
				.param("money", "11000")
				.param("thumbnail", "test_thumbnail11")
				.param("categoryNo", "2")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패햇는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	
	// 관리자 상품 DB에 수정 이름 Valid
	@Test
	public void testFItemEditNameValid() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(put("/api/admin/item/edit")
				.param("no", "1")
				.param("name", "")
				.param("description", "test_description11")
				.param("money", "11000")
				.param("thumbnail", "test_thumbnail11")
				.param("categoryNo", "2")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패햇는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	
	

	// 관리자 상품 진열여부 DB에 수정 Valid
	@Test
	public void testGItemEditDisplayValid() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(put("/api/admin/item/edit/display/{no}", 2L)
				.param("display", "")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패햇는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	


	// 관리자 상품 이미지를 DB에 저장 상품번호 Valid
	@Test
	public void testHAddItemImgNoValid() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(post("/api/admin/item/itemimg")
				.param("itemNo", "")
				.param("itemImg", "test_img3")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	
	// 관리자 상품 이미지를 DB에 저장 상품이미지 Valid
	@Test
	public void testHAddItemImgImgValid() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(post("/api/admin/item/itemimg")
				.param("itemNo", "1")
				.param("itemImg", "")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	
	// 관리자 상품 상세옵션 추가 레벨 Valid
	@Test
	public void testJAddOptionDetailLevelValid() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(post("/api/admin/item/optiondetail")
				.param("optionName", "초록색")
				.param("level", "")
				.param("itemNo", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	
	// 관리자 상품 옵션 추가 수량 Valid
	@Test
	public void testLAddOptionCntValid() throws Exception {
		ResultActions resultActions;

		resultActions = mockMvc.perform(post("/api/admin/item/option")
				.param("itemNo", "1")
				.param("optionDetail1", "1")
				.param("optionDetail2", "4")
				.param("cnt", "-5")
				.contentType(MediaType.APPLICATION_JSON));

		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	
}
