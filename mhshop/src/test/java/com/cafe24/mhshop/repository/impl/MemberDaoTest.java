package com.cafe24.mhshop.repository.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.apache.ibatis.session.SqlSession;
import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

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
import com.cafe24.mhshop.repository.MemberDao;
import com.cafe24.mhshop.service.MemberService;
import com.cafe24.mhshop.vo.MemberVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class MemberDaoTest {
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	MemberDao memberDao;
	
	@Before
	public void setup() {
		
		// DI테스트
		assertNotNull(memberDao);
		

		// DB Member 테이블 초기화
		// DB 테스트용 데이터 insert
		
		// member insert
		sqlSession.delete("test_member.deleteall");
		sqlSession.insert("test_member.insert", new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key"));
		sqlSession.insert("test_member.insert", new MemberVo("test_id2", "testpassword2!", "test2", "01000000002", "test_email2@naver.com", "test_zipcode2", "test_addr2", "2019-07-11", "ADMIN", "mhshop_key"));
	
	}
	
	

	// 아이디 중복확인
	@Test
	public void testACountById() throws Exception {

		// 중복했을 때
		assertThat(memberDao.countById("test_id1"), is(1));
		
		// 중복이 아닐 때
		assertThat(memberDao.countById("test_id3"), is(0));
		
	}
	
	
	// 입력
	@Test
	public void testBInsert() throws Exception {

		// 저장할 데이터 생성
		MemberVo memberVo = new MemberVo("test_id3", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "");
		
		
		// 입력 전에 TABLE의 개수
		int beforeCount = (Integer)sqlSession.selectOne("test_member.countall");
		// 입력
		memberDao.insert(memberVo);
		// 입력 후의 TABLE의 개수
		int afterCount = (Integer)sqlSession.selectOne("test_member.countall");

		
		// TABLE의 개수가 증가했으면 성공
		assertThat(afterCount, is(beforeCount+1));
		
	}

	
	// 로그인
	@Test
	public void testCSelectByIdAndPassword() throws Exception {

		// 로그인할 정보
		MemberVo inputVo = new MemberVo();
		inputVo.setId("test_id1");
		inputVo.setPassword("testpassword1!");
		
		
		// 로그인
		MemberVo memberVo = memberDao.selectByIdAndPassword(inputVo);
		// null이 아니어야하고
		assertThat(memberVo, Matchers.notNullValue());
		// 올바른 정보가 왔는지 확인
		assertThat(memberVo.getId(), is(inputVo.getId()));
		
	}
	

	// 회원리스트
	@Test
	public void testDSelectList() throws Exception {

		
		// 로그인
		List<MemberVo> memberList = memberDao.selectList();
		// null이 아니어야하고
		assertThat(memberList, Matchers.notNullValue());
		// 들어있는 개수가 2개여야 한다(테스트용 입력이 2개였기 때문)
		assertThat(memberList.size(), is(2));
		
	}
	

	// 아이디로 회원조회
	@Test
	public void testESelectOneById() throws Exception {

		// 로그인할 정보
		String id = "test_id1";
		
		
		// 조회
		MemberVo memberVo = memberDao.selectOneById(id);
		// null이 아니어야하고
		assertThat(memberVo, Matchers.notNullValue());
		// 올바른 정보가 왔는지 확인
		assertThat(memberVo.getId(), is(id));
		
	}
	
	
	// 회원삭제
	@Test
	public void testFDelete() throws Exception {

		// 삭제성공
		assertThat(memberDao.delete("test_id1"), is(1));
		
		// 삭제실패(없는 id)
		assertThat(memberDao.delete("test_id3"), is(0));
		
	}
	

	// 회원수정
	@Test
	public void testGUpdate() throws Exception {
		

		MemberVo memberVo1 = new MemberVo("test_id3", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key");
		MemberVo memberVo2 = new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key");

		// 삭제성공
		assertThat(memberDao.update(memberVo2), is(1));
		
		// 삭제실패(없는 id)
		assertThat(memberDao.update(memberVo1), is(0));
		
	}
	
	
	@After
	public void finish() {
		sqlSession.delete("test_member.deleteall");
	}
	
	
}
