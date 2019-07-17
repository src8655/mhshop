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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mhshop.config.AppConfig;
import com.cafe24.mhshop.config.TestWebConfig;

import io.swagger.annotations.ApiImplicitParam;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
@Rollback(value = true)
@Transactional
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
	public void testA관리자상품리스트페이지() throws Exception {
		
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
	public void testB상품작성페이지() throws Exception {
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
	public void testC상품작성_이름_Valid() throws Exception {
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
	public void testC상품작성_금액_Valid() throws Exception {
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
	

	// 관리자 상품 DB에 저장
	@Test
	public void testC상품작성() throws Exception {
		ResultActions resultActions;
		

		// 성공했을 때
		resultActions = mockMvc.perform(post("/api/admin/item/write")
				.param("name", "test_name")
				.param("description", "test_description2")
				.param("money", "1000")
				.param("thumbnail", "test_thumbnail3")
				.param("categoryNo", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk());
	}

	
	// 관리자 상품 DB에 수정 페이지
	@Test
	public void testD상품수정_페이지() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/admin/item/edit/{no}",1L)
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk());
		
	}
	
	// 관리자 상품 DB에 수정 NO Valid
	@Test
	public void testD상품수정_NO_Valid() throws Exception {
		
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
	public void testD상품수정_이름_Valid() throws Exception {
		
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

	// 관리자 상품 DB에 수정
	@Test
	public void testD상품수정() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(put("/api/admin/item/edit")
				.param("no", "1")
				.param("name", "test_items")
				.param("description", "test_description11")
				.param("money", "11000")
				.param("thumbnail", "test_thumbnail11")
				.param("categoryNo", "2")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk());
		
	}
	


	

	// 관리자 상품 진열여부 DB에 수정 Valid
	@Test
	public void testE상품진열여부수정_진열여부_Valid() throws Exception {
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

	// 관리자 상품 진열여부 DB에 수정
	@Test
	public void testE상품진열여부수정() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(put("/api/admin/item/edit/display/{no}", 2L)
				.param("display", "FALSE")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패햇는지
		resultActions
		.andExpect(status().isOk());
		
	}
	
	


	// 관리자 상품 이미지를 DB에 저장 상품번호 Valid
	@Test
	public void testF상품이미지작성_상품번호_Valid() throws Exception {
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
	public void testF상품이미지작성_상품이미지_Valid() throws Exception {
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
	
	

	// 관리자 상품 이미지를 DB에 저장
	@Test
	public void testF상품이미지작성() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(post("/api/admin/item/itemimg")
				.param("itemNo", "1")
				.param("itemImg", "test_img")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk());
	}
	
	
	// 관리자 상품 상세옵션 추가 레벨 Valid
	@Test
	public void testG상세옵션작성_레벨_Valid() throws Exception {
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
	

	// 관리자 상품 상세옵션 추가
	@Test
	public void testG상세옵션작성() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(post("/api/admin/item/optiondetail")
				.param("optionName", "초록색")
				.param("level", "1")
				.param("itemNo", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk());
	}
	
	
	// 관리자 상품 옵션 추가 수량 Valid
	@Test
	public void testH옵션작성_수량_Valid() throws Exception {
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
	

	// 관리자 상품 옵션 추가
	@Test
	public void testH옵션작성() throws Exception {
		ResultActions resultActions;

		resultActions = mockMvc.perform(post("/api/admin/item/option")
				.param("itemNo", "1")
				.param("optionDetail1", "1")
				.param("optionDetail2", "4")
				.param("cnt", "5")
				.contentType(MediaType.APPLICATION_JSON));

		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk());
	}
	
}
