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
public class AdminMemberControllerTest {
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
		
		
		// DB Member 테이블 초기화
		// DB 테스트용 데이터 insert
		
		// member insert
		// insert into member(id, password, name, phone, email, zipcode, addr, regDate, role) values('test_id1', 'testpassword1!', 'test1', '01000000001', 'test_email1@naver.com', 'test_zipcode1', 'test_addr1', '2019-07-11', 'USER')
		// insert into member(id, password, name, phone, email, zipcode, addr, regDate, role) values('test_id2', 'testpassword2!', 'test2', '01000000002', 'test_email2@naver.com', 'test_zipcode2', 'test_addr2', '2019-07-11', 'ADMIN')

	}
	
	
	// 회원 리스트
	@Test
	public void testAAdminMemberList() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/adminmember/list").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 회원리스트 데이터 확인
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		
		.andExpect(jsonPath("$.data.memberList[0].id", is("test_id1")))
		.andExpect(jsonPath("$.data.memberList[0].name", is("test1")))
		.andExpect(jsonPath("$.data.memberList[0].phone", is("01000000001")))
		.andExpect(jsonPath("$.data.memberList[0].email", is("test_email1@naver.com")))
		.andExpect(jsonPath("$.data.memberList[0].zipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data.memberList[0].addr", is("test_addr1")))
		.andExpect(jsonPath("$.data.memberList[0].role", is("USER")))

		.andExpect(jsonPath("$.data.memberList[1].id", is("test_id2")))
		.andExpect(jsonPath("$.data.memberList[1].name", is("test2")))
		.andExpect(jsonPath("$.data.memberList[1].phone", is("01000000002")))
		.andExpect(jsonPath("$.data.memberList[1].email", is("test_email2@naver.com")))
		.andExpect(jsonPath("$.data.memberList[1].zipcode", is("test_zipcode2")))
		.andExpect(jsonPath("$.data.memberList[1].addr", is("test_addr2")))
		.andExpect(jsonPath("$.data.memberList[1].role", is("ADMIN")))
		
		.andExpect(jsonPath("$.data.forward", is("admin/member_list")));
		
	}
	
	
	// 회원 상세보기
	@Test
	public void testBAdminMemberView() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/adminmember/view/{id}", "test_id1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 리턴한 회원정보 확인
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))

		.andExpect(jsonPath("$.data.memberVo.id", is("test_id1")))
		.andExpect(jsonPath("$.data.memberVo.name", is("test1")))
		.andExpect(jsonPath("$.data.memberVo.phone", is("01000000001")))
		.andExpect(jsonPath("$.data.memberVo.email", is("test_email1@naver.com")))
		.andExpect(jsonPath("$.data.memberVo.zipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data.memberVo.addr", is("test_addr1")))
		.andExpect(jsonPath("$.data.memberVo.role", is("USER")))
		
		.andExpect(jsonPath("$.data.forward", is("admin/member_view")));
		
	}


	// 회원 삭제
	@Test
	public void testCAdminMemberDelete() throws Exception {
		ResultActions resultActions;
		
		
		// 삭제 성공하는 경우
		resultActions = mockMvc.perform(delete("/api/adminmember/{id}", "test_id1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// delete 결과 확인
		// 리다이렉트할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminmember/list")));
	}
	
	
	
	
	
	@AfterClass
	public static void finish() {
		// DB 초기화
	}
	
	
}
