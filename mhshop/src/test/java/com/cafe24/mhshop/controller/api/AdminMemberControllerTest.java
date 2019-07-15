package com.cafe24.mhshop.controller.api;

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
import com.cafe24.mhshop.vo.MemberVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class AdminMemberControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	SqlSession sqlSession;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


		// DB Member 테이블 초기화
		// DB 테스트용 데이터 insert
		
		// member insert
		sqlSession.delete("test_member.deleteall");
		sqlSession.insert("test_member.insert", new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key"));
		sqlSession.insert("test_member.insert", new MemberVo("test_id2", "testpassword2!", "test2", "01000000002", "test_email2@naver.com", "test_zipcode2", "test_addr2", "2019-07-11", "ADMIN", "mhshop_key"));
	
	}
	
	
	// 회원 리스트
	@Test
	public void testA관리자회원리스트페이지() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/admin/member/list").contentType(MediaType.APPLICATION_JSON));
		
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
		.andExpect(jsonPath("$.data[0].role", is("ADMIN")))
		
		.andExpect(jsonPath("$.data[1].id", is("test_id1")))
		.andExpect(jsonPath("$.data[1].name", is("test1")))
		.andExpect(jsonPath("$.data[1].phone", is("01000000001")))
		.andExpect(jsonPath("$.data[1].email", is("test_email1@naver.com")))
		.andExpect(jsonPath("$.data[1].zipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data[1].addr", is("test_addr1")))
		.andExpect(jsonPath("$.data[1].role", is("USER")));
		
	}
	
	
	

	// 회원 상세보기 ID Valid
	@Test
	public void testB회원상세보기_아이디_Valid() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/admin/member/view/{id}", "1test_id1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
		
	}
	
	// 회원 상세보기
	@Test
	public void testB회원상세보기() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/admin/member/view/{id}", "test_id1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		resultActions
		.andExpect(status().isOk());
		
	}


	

	// 회원 삭제 ID Valid
	@Test
	public void testC회원삭제_아이디_Valid() throws Exception {
		ResultActions resultActions;
		
		
		// 삭제 성공하는 경우
		resultActions = mockMvc.perform(delete("/api/admin/member/{id}", "1test_id1")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("fail")));
	}
	

	// 회원 삭제
	@Test
	public void testC회원삭제() throws Exception {
		ResultActions resultActions;
		
		
		// 삭제 성공하는 경우
		resultActions = mockMvc.perform(delete("/api/admin/member/{id}", "test_id5")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 실패했는지
		resultActions
		.andExpect(status().isOk());
	}
	
	
	
}
