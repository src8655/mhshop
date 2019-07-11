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

import io.swagger.annotations.ApiImplicitParam;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class AdminItemControllerTest {
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
		

		// DB 테이블 초기화
		// DB 테스트용 데이터 insert

		// category insert
		// insert into category(no, name) values(1, 'test_category1')
		// insert into category(no, name) values(2, 'test_category2')
		
		
		// item insert
		// insert into item(no, name, description, money, thmbnail, display, category_no) values(1, 'test_item1', 'test_description1', 10000, 'test_thumbnail1', 'FALSE', 1)
		// insert into item(no, name, description, money, thmbnail, display, category_no) values(2, 'test_item2', 'test_description2', 20000, 'test_thumbnail2', 'FALSE', 2)
		
		
		// itemimg insert
		// insert into item_img(no, item_no, item_img) values(1, 1, 'test_img1')
		// insert into item_img(no, item_no, item_img) values(2, 1, 'test_img2')
		
		
		// optiondetail insert
		// insert into option_detail(no, optionName, level, itemNo) values(1, '파란색', 1, 1)
		// insert into option_detail(no, optionName, level, itemNo) values(2, 'L', 2, 1)
		// insert into option_detail(no, optionName, level, itemNo) values(3, 'XL', 2, 1)
		// insert into option_detail(no, optionName, level, itemNo) values(4, 'XXL', 2, 1)
		
		
		// option insert
		// insert into option(no, itemNo, optionDetail1, optionDetail2, cnt) values(1, 1, 1, 2, 10)
		// insert into option(no, itemNo, optionDetail1, optionDetail2, cnt) values(2, 1, 1, 3, -1)
		
	}
	
	
	
	

	// 관리자 상품 리스트
	@Test
	public void testAItemList() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/adminitem/list").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 아이템 리스트를 확인
		// 카테고리 리스트를 확인
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))

		.andExpect(jsonPath("$.data.itemList[0].no", is(1)))
		.andExpect(jsonPath("$.data.itemList[0].name", is("test_item1")))
		.andExpect(jsonPath("$.data.itemList[0].description", is("test_description1")))
		.andExpect(jsonPath("$.data.itemList[0].money", is(10000)))
		.andExpect(jsonPath("$.data.itemList[0].thumbnail", is("test_thumbnail1")))
		.andExpect(jsonPath("$.data.itemList[0].display", is("FALSE")))
		.andExpect(jsonPath("$.data.itemList[0].categoryNo", is(1)))

		.andExpect(jsonPath("$.data.itemList[1].no", is(2)))
		.andExpect(jsonPath("$.data.itemList[1].name", is("test_item2")))
		.andExpect(jsonPath("$.data.itemList[1].description", is("test_description2")))
		.andExpect(jsonPath("$.data.itemList[1].money", is(20000)))
		.andExpect(jsonPath("$.data.itemList[1].thumbnail", is("test_thumbnail2")))
		.andExpect(jsonPath("$.data.itemList[1].display", is("FALSE")))
		.andExpect(jsonPath("$.data.itemList[1].categoryNo", is(1)))
		
		.andExpect(jsonPath("$.data.categoryList[0].no", is(1)))
		.andExpect(jsonPath("$.data.categoryList[0].name", is("test_category1")))
		.andExpect(jsonPath("$.data.categoryList[1].no", is(2)))
		.andExpect(jsonPath("$.data.categoryList[1].name", is("test_category2")))
		
		.andExpect(jsonPath("$.data.forward", is("admin/item_list")));
		
	}
	

	// [관리자 상품 작성 페이지]
	@Test
	public void testBItemWriteForm() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(get("/api/adminitem/write").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 카테고리가 null이 아니여야함
		// 카테고리 리스트 데이터 확인
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.categoryList", Matchers.notNullValue()))
		
		.andExpect(jsonPath("$.data.categoryList[0].no", is(1)))
		.andExpect(jsonPath("$.data.categoryList[0].name", is("test_category1")))
		.andExpect(jsonPath("$.data.categoryList[1].no", is(2)))
		.andExpect(jsonPath("$.data.categoryList[1].name", is("test_category2")))
		
		.andExpect(jsonPath("$.data.forward", is("admin/item_write_form")));
		
	}
	
	// 관리자 상품 DB에 저장
	@Test
	public void testCItemWrite() throws Exception {
		ResultActions resultActions;
		

		// 성공했을 때
		resultActions = mockMvc.perform(post("/api/adminitem/write")
				.param("name", "test_item3")
				.param("description", "test_description2")
				.param("money", "30000")
				.param("thumbnail", "test_thumbnail3")
				.param("categoryNo", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공했는지
		// insert 쿼리가 성공했는지
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/item_list")));
		
		

		// 없는 카테고리로 등록했을 때 실패
		resultActions = mockMvc.perform(post("/api/adminitem/write")
				.param("name", "test_item3")
				.param("description", "test_description2")
				.param("money", "30000")
				.param("thumbnail", "test_thumbnail3")
				.param("categoryNo", "3")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공했는지
		// insert 쿼리가 성공했는지
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail"))).andDo(print());
		
	}
	

	

	// 관리자 상품 DB에 삭제
	@Test
	public void testDItemDelete() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(delete("/api/adminitem/item/{no}", 1L)
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// delete 쿼리가 성공했는지
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/list")));
		
	}
	
	

	// [관리자 상품 수정 페이지]
	@Test
	public void testEItemEditForm() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/adminitem/edit/{no}", 1L)
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 상품 데이터 확인
		// 상품이미지 데이터 확인
		// 1차 상세옵션 데이터 확인
		// 2차 상세옵션 데이터 확인
		// 옵션 데이터 확인
		// 카테고리 데이터 확인
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))

		.andExpect(jsonPath("$.data.itemVo.no", is(1)))
		.andExpect(jsonPath("$.data.itemVo.name", is("test_item1")))
		.andExpect(jsonPath("$.data.itemVo.description", is("test_description1")))
		.andExpect(jsonPath("$.data.itemVo.money", is(10000)))
		.andExpect(jsonPath("$.data.itemVo.thumbnail", is("test_thumbnail1")))
		.andExpect(jsonPath("$.data.itemVo.display", is("FALSE")))
		.andExpect(jsonPath("$.data.itemVo.categoryNo", is(1)))
		

		.andExpect(jsonPath("$.data.itemImgList", Matchers.notNullValue()))
		.andExpect(jsonPath("$.data.optionDetailList1", Matchers.notNullValue()))
		.andExpect(jsonPath("$.data.optionDetailList2", Matchers.notNullValue()))
		.andExpect(jsonPath("$.data.optionList", Matchers.notNullValue()))
		.andExpect(jsonPath("$.data.categoryList", Matchers.notNullValue()))
		
		.andExpect(jsonPath("$.data.forward", is("admin/item_edit_form")));
		
	}
	
	
	
	// 관리자 상품 DB에 수정
	@Test
	public void testFItemEdit() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(put("/api/adminitem/edit")
				.param("no", "1")
				.param("name", "test_item11")
				.param("description", "test_description11")
				.param("money", "11000")
				.param("thumbnail", "test_thumbnail11")
				.param("categoryNo", "2")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// update 리턴 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/edit")));
		
	}
	
	

	// 관리자 상품 진열여부 DB에 수정
	@Test
	public void testGItemEditDisplay() throws Exception {
		ResultActions resultActions;
		
		

		// 성공했을 경우
		resultActions = mockMvc.perform(put("/api/adminitem/edit_display")
				.param("no", "1")
				.param("display", "TRUE")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// update 리턴 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/edit")));
		
		
		
		

		// 옵션이 존재하지 않아서 진열상태로 변경 못하는 경우
		resultActions = mockMvc.perform(put("/api/adminitem/edit_display")
				.param("no", "2")
				.param("display", "TRUE")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패해야함
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	


	// 관리자 상품 이미지를 DB에 저장
	@Test
	public void testHAddItemImg() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(post("/api/adminitem/itemimg")
				.param("itemNo", "1")
				.param("itemImg", "test_img3")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// insert 리턴 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/edit")));
	}
	
	
	


	// 관리자 상품 이미지를 DB에서 삭제
	@Test
	public void testIDeleteItemImg() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(delete("/api/adminitem/itemimg")
				.param("itemNo", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// delete 리턴 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/edit")));
	}
	
	


	// 관리자 상품 상세옵션 추가
	@Test
	public void testJAddOptionDetail() throws Exception {
		ResultActions resultActions;
		
		resultActions = mockMvc.perform(post("/api/adminitem/optiondetail")
				.param("optionName", "초록색")
				.param("level", "1")
				.param("itemNo", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// insert 리턴 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/edit")));
	}
	
	
	
	// 관리자 상품 상세옵션 삭제
	@Test
	public void testKDeleteOptionDetail() throws Exception {
		ResultActions resultActions;
		
		
		// 성공했을 때
		resultActions = mockMvc.perform(delete("/api/adminitem/optiondetail")
				.param("no", "4")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// delete 리턴 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/edit")));
		
		
		
		

		// 상세옵션번호를 가지는 옵션이 존재할 때 실패하는 경우
		resultActions = mockMvc.perform(delete("/api/adminitem/optiondetail")
				.param("no", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패해야함
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	
	// option insert1
	// ("no", 1)
	// ("itemNo", 1)
	// ("optionDetail1", 1)
	// ("optionDetail2", 2)
	// ("cnt", 10)
	

	// 관리자 상품 옵션 추가
	@Test
	public void testLAddOption() throws Exception {
		ResultActions resultActions;


		// 성공했을 때
		resultActions = mockMvc.perform(post("/api/adminitem/option")
				.param("itemNo", "1")
				.param("optionDetail1", "1")
				.param("optionDetail2", "4")
				.param("cnt", "20")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// insert 리턴 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/edit")));
		
		
		

		// 수량이 -1 보다 작아서 실패했을 때
		resultActions = mockMvc.perform(post("/api/adminitem/option")
				.param("itemNo", "1")
				.param("optionDetail1", "1")
				.param("optionDetail2", "4")
				.param("cnt", "-2")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패해야함
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	
	

	// 관리자 상품 옵션 삭제
	@Test
	public void testNDeleteOption() throws Exception {
		ResultActions resultActions;


		// 성공했을 때
		resultActions = mockMvc.perform(delete("/api/adminitem/option")
				.param("no", "1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// delete 리턴 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminitem/edit")));
	}
	
	
	
	@AfterClass
	public static void finish() {
		// DB 초기화
	}
	
	
}
