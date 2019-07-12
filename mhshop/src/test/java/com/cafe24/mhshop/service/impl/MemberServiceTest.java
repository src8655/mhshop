package com.cafe24.mhshop.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
import com.cafe24.mhshop.service.MemberService;
import com.cafe24.mhshop.vo.MemberVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class MemberServiceTest {
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	MemberService memberService;
	
	@Before
	public void setup() {
		
		// DI테스트
		assertNotNull(memberService);
		

		// DB Member 테이블 초기화
		// DB 테스트용 데이터 insert
		
		// member insert
		sqlSession.delete("test_member.deleteall");
		sqlSession.insert("test_member.insert", new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key"));
		sqlSession.insert("test_member.insert", new MemberVo("test_id2", "testpassword2!", "test2", "01000000002", "test_email2@naver.com", "test_zipcode2", "test_addr2", "2019-07-11", "ADMIN", "mhshop_key"));
	
	}
	
	

	// 아이디 중복확인
	@Test
	public void testAIdCheck() throws Exception {
		
		// 중복했을 때
		assertThat(memberService.idCheck("test_id1"), is(true));
		
		// 중복이 아닐 때
		assertThat(memberService.idCheck("test_id3"), is(false));
		
	}
	

	// 회원등록
	@Test
	public void testBAdd() throws Exception {
		
		MemberVo memberVo1 = new MemberVo("test_id3", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key");
		MemberVo memberVo2 = new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key");
		
		// 성공했을 때
		assertThat(memberService.add(memberVo1), is(true));
		
		// 실패했을 때(id중복)
		assertThat(memberService.add(memberVo2), is(false));
	}

	// 로그인
	@Test
	public void testCLogin() throws Exception {
		
		// 성공했을 때
		assertThat(memberService.login("test_id1",  "testpassword1!"), Matchers.notNullValue());
		
		// 실패했을 때(null)
		assertThat(memberService.login("test_id3",  "testpassword3!"), Matchers.nullValue());
	}
	
	

	// 회원삭제
	@Test
	public void testDDelete() throws Exception {
		
		// 성공했을 때
		assertThat(memberService.delete("test_id1"), is(true));
		
		// 실패했을 때(없는 아이디 삭제)
		assertThat(memberService.delete("test_id3"), is(false));
	}
	
	
	
	@After
	public void finish() {
		sqlSession.delete("test_member.deleteall");
	}
	
	
}
