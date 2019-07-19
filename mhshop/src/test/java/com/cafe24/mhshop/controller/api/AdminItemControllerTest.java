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
public class AdminItemControllerTest {
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
	

	// 관리자 상품 리스트
	@Test
	public void testA관리자상품리스트() throws Exception {
		ResultActions resultActions;
		
		
		resultActions = mockMvc.perform(get("/api/admin/item/list")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[1].no", is(1)))
		.andExpect(jsonPath("$.data[1].name", is("test_item1")))
		.andExpect(jsonPath("$.data[1].description", is("test_description1")))
		.andExpect(jsonPath("$.data[1].money", is(10000)))
		.andExpect(jsonPath("$.data[1].thumbnail", is("test_thumbnail1")))
		.andExpect(jsonPath("$.data[1].display", is("FALSE")))
		.andExpect(jsonPath("$.data[1].categoryNo", is(1)))

		.andExpect(jsonPath("$.data[0].no", is(2)))
		.andExpect(jsonPath("$.data[0].name", is("test_item2")))
		.andExpect(jsonPath("$.data[0].description", is("test_description2")))
		.andExpect(jsonPath("$.data[0].money", is(20000)))
		.andExpect(jsonPath("$.data[0].thumbnail", is("test_thumbnail2")))
		.andExpect(jsonPath("$.data[0].display", is("FALSE")))
		.andExpect(jsonPath("$.data[0].categoryNo", is(1)));
		
	}
	
	
	
	// 관리자 상품 등록
	@Test
	public void testB상품등록() throws Exception {
		ResultActions resultActions;
		
		// 상품이름 Valid
		resultActions = mockMvc.perform(post("/api/admin/item")
				.param("name", "")
				.param("description", "test_description2")
				.param("money", "30000")
				.param("thumbnail", "test_thumbnail3")
				.param("categoryNo", "1")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 상품금액 Valid
		resultActions = mockMvc.perform(post("/api/admin/item")
				.param("name", "test_name")
				.param("description", "test_description2")
				.param("money", "")
				.param("thumbnail", "test_thumbnail3")
				.param("categoryNo", "1")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공했을 때
		resultActions = mockMvc.perform(post("/api/admin/item")
				.param("name", "test_name")
				.param("description", "test_description2")
				.param("money", "1000")
				.param("thumbnail", "test_thumbnail3")
				.param("categoryNo", "1")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	

	// 관리자 상품 삭제
	@Test
	public void testC상품삭제() throws Exception {
		ResultActions resultActions;
		
		// 상품삭제
		resultActions = mockMvc.perform(delete("/api/admin/item/{no}", 1L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	


	// 관리자 상세옵션리스트
	@Test
	public void testD상세옵션리스트() throws Exception {
		ResultActions resultActions;
		
		// 옵션레벨 Valid
		resultActions = mockMvc.perform(get("/api/admin/item/optiondetail/{itemNo}", 1L)
				.param("level", "3")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 성공
		resultActions = mockMvc.perform(get("/api/admin/item/optiondetail/{itemNo}", 1L)
				.param("level", "1")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data[0].no", is(1)))
		.andExpect(jsonPath("$.data[0].optionName", is("파란색")))
		.andExpect(jsonPath("$.data[0].level", is(1)))
		.andExpect(jsonPath("$.data[0].itemNo", is(1)));
		
	}
	
	
	
	
	
	

	// 관리자 상품수정
	@Test
	public void testE상품수정() throws Exception {
		ResultActions resultActions;

		// 상품명 Valid
		resultActions = mockMvc.perform(put("/api/admin/item/{no}", 1L)
				.param("name", "")
				.param("description", "test_description11")
				.param("money", "11000")
				.param("thumbnail", "test_thumbnail11")
				.param("categoryNo", "2")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 상품금액 Valid
		resultActions = mockMvc.perform(put("/api/admin/item/{no}", 1L)
				.param("no", "1")
				.param("name", "change!!")
				.param("description", "test_description11")
				.param("money", "-1")
				.param("thumbnail", "test_thumbnail11")
				.param("categoryNo", "2")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(put("/api/admin/item/{no}", 1L)
				.param("no", "1")
				.param("name", "change!!")
				.param("description", "test_description11")
				.param("money", "11000")
				.param("thumbnail", "test_thumbnail11")
				.param("categoryNo", "2")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
		
	}
	


	

	// 관리자 상품진열여부정
	@Test
	public void testF상품진열여부수정() throws Exception {
		ResultActions resultActions;
		
		// 진열상태 Valid
		resultActions = mockMvc.perform(put("/api/admin/item/display/{no}", 2L)
				.param("display", "ttttrue")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(put("/api/admin/item/display/{no}", 1L)
				.param("display", "TRUE")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	
	
	
	// 관리자 상품이미지저장
	@Test
	public void testG상품이미지저장() throws Exception {
		ResultActions resultActions;

		// 상품번호 Valid
		resultActions = mockMvc.perform(post("/api/admin/item/img")
				.param("itemNo", "")
				.param("itemImg", "test_img")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 없는 상품번호 Valid
		resultActions = mockMvc.perform(post("/api/admin/item/img")
				.param("itemNo", "99")
				.param("itemImg", "test_img")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 상품이미지 Valid
		resultActions = mockMvc.perform(post("/api/admin/item/img")
				.param("itemNo", "1")
				.param("itemImg", "")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 성공
		resultActions = mockMvc.perform(post("/api/admin/item/img")
				.param("itemNo", "1")
				.param("itemImg", "test_img")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	

	// 관리자 상품이미지 삭제
	@Test
	public void testH상품이미지삭제() throws Exception {
		ResultActions resultActions;

		// 없는 이미지 삭제
		resultActions = mockMvc.perform(delete("/api/admin/item/img/{no}", 99L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(false)));
		

		// 성공
		resultActions = mockMvc.perform(delete("/api/admin/item/img/{no}", 1L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	
	
	
	// 관리자 상세옵션 저장
	@Test
	public void testI상세옵션저장() throws Exception {
		ResultActions resultActions;

		// 옵션이름 Valid
		resultActions = mockMvc.perform(post("/api/admin/item/optiondetail")
				.param("optionName", "")
				.param("level", "1")
				.param("itemNo", "1")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 옵션레벨 Valid
		resultActions = mockMvc.perform(post("/api/admin/item/optiondetail")
				.param("optionName", "option_name")
				.param("level", "3")
				.param("itemNo", "1")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 상품번호 Valid
		resultActions = mockMvc.perform(post("/api/admin/item/optiondetail")
				.param("optionName", "option_name")
				.param("level", "3")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 없는 상품번호 Valid
		resultActions = mockMvc.perform(post("/api/admin/item/optiondetail")
				.param("optionName", "option_name")
				.param("level", "3")
				.param("itemNo", "99")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 성공
		resultActions = mockMvc.perform(post("/api/admin/item/optiondetail")
				.param("optionName", "option_name")
				.param("level", "1")
				.param("itemNo", "1")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	

	
	// 관리자 상세옵션 삭제
	@Test
	public void testJ상세옵션삭제() throws Exception {
		ResultActions resultActions;

		// 옵션에 이미 사용중인 상세옵션 실패
		resultActions = mockMvc.perform(delete("/api/admin/item/optiondetail/{no}", 1L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());


		// 없는 상세옵션번호
		resultActions = mockMvc.perform(delete("/api/admin/item/optiondetail/{no}", 99L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(false)));
		
		
		// 성공
		resultActions = mockMvc.perform(delete("/api/admin/item/optiondetail/{no}", 4L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	
	
	

	// 관리자 옵션저장
	@Test
	public void testK옵션저장() throws Exception {
		ResultActions resultActions;

		// 수량 Valid
		resultActions = mockMvc.perform(post("/api/admin/item/option")
				.param("itemNo", "1")
				.param("optionDetailNo1", "1")
				.param("optionDetailNo2", "4")
				.param("cnt", "-5")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 존재하지 않는 상품번호 실패
		resultActions = mockMvc.perform(post("/api/admin/item/option")
				.param("itemNo", "99")
				.param("optionDetailNo1", "1")
				.param("optionDetailNo2", "4")
				.param("cnt", "5")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이400 인지
		resultActions
		.andExpect(status().isBadRequest());
		
		
		// 존재하지 않은 상세옵션번호 실패
		resultActions = mockMvc.perform(post("/api/admin/item/option")
				.param("itemNo", "1")
				.param("optionDetailNo1", "99")
				.param("cnt", "5")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 400 인지
		resultActions
		.andExpect(status().isBadRequest());
		

		// 성공
		resultActions = mockMvc.perform(post("/api/admin/item/option")
				.param("itemNo", "1")
				.param("optionDetailNo1", "1")
				.param("optionDetailNo2", "4")
				.param("cnt", "5")
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
	
	

	// 관리자 옵션삭제
	@Test
	public void testL옵션삭제() throws Exception {
		ResultActions resultActions;

		// 없는 옵션 삭제
		resultActions = mockMvc.perform(delete("/api/admin/item/option/{no}", 99L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(false)));
		

		// 성공
		resultActions = mockMvc.perform(delete("/api/admin/item/option/{no}", 1L)
				.param("mockToken", mockToken)
				.contentType(MediaType.APPLICATION_JSON));
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data", is(true)));
	}
	
}
