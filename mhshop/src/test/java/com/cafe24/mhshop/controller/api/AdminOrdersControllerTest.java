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
public class AdminOrdersControllerTest {
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
		
		// member insert
		// insert into member(id, password, name, phone, email, zipcode, addr, regDate, role) values('test_id1', 'testpassword1!', 'test1', '01000000001', 'test_email1@naver.com', 'test_zipcode1', 'test_addr1', '2019-07-11', 'USER')
		// insert into member(id, password, name, phone, email, zipcode, addr, regDate, role) values('test_id2', 'testpassword2!', 'test2', '01000000002', 'test_email2@naver.com', 'test_zipcode2', 'test_addr2', '2019-07-11', 'ADMIN')

		
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
		
		
		// orders insert
		// insert into orders(orders_no, reg_date, status, pay, money, tracking_num, to_name, to_phone, to_zipcode, to_addr, member_id)
		// 			values('2019-07-11_000256', '2019-07-11', '입금대기', '무통장입금', 10000, null, 'test_name1', '01000000001', 'test_zipcode1', 'test_addr1', 'test_id1')
		// insert into orders(orders_no, reg_date, status, pay, money, tracking_num, to_name, to_phone, to_zipcode, to_addr, member_id)
		// 			values('2019-07-11_000257', '2019-07-11', '결제완료', '카카오페이', 20000, null, 'test_name2', '01000000002', 'test_zipcode2', 'test_addr2', null)
		// insert into orders(orders_no, reg_date, status, pay, money, tracking_num, to_name, to_phone, to_zipcode, to_addr, member_id)
		// 			values('2019-07-11_000258', '2019-07-11', '배송중', '카카오페이', 30000, null, 'test_name3', '01000000003', 'test_zipcode3', 'test_addr3', null)
		
		
		// pay_bank insert
		// insert into pay_bank(orders_no, bank_name, bank_number, pay_date) values('2019-07-11_000256', '국민은행', '10000-00-000000', '2019-07-11')
		
		
		// pay_kakao insert
		// insert into pay_kakao(orders_no, tid, aid, pay_date) values('2019-07-11_000257', 'DI201022333', 'AP32292938', '2019-07-11')
		// insert into pay_kakao(orders_no, tid, aid, pay_date) values('2019-07-11_000258', 'DI202231226', 'AP98755653', '2019-07-11')
		
		
		// guest insert
		// insert into guest(orders_no, guest_name, guest_phone, guest_password) values('2019-07-11_000257', 'test_guest1', '01000000001', 'guestpw1!')
		// insert into guest(orders_no, guest_name, guest_phone, guest_password) values('2019-07-11_000258', 'test_guest2', '01000000002', 'guestpw2!')
		
		
		// orders_item insert
		// insert into orders_item(no, orders_no, option_no, item_name, item_thumbnail, item_option_detail1, item_option_detail2, money, cnt)
		// 			values(1, '2019-07-11_000256', 1, 'test_item1', 'test_thmbnail1', '파란색', 'L', 10000, 1)
		// insert into orders_item(no, orders_no, option_no, item_name, item_thumbnail, item_option_detail1, item_option_detail2, money, cnt)
		// 			values(2, '2019-07-11_000257', 2, 'test_item2', 'test_thmbnail2', '파란색', 'XL', 20000, 1)
		// insert into orders_item(no, orders_no, option_no, item_name, item_thumbnail, item_option_detail1, item_option_detail2, money, cnt)
		// 			values(3, '2019-07-11_000258', 1, 'test_item1', 'test_thmbnail3', '파란색', 'L', 10000, 1)
		// insert into orders_item(no, orders_no, option_no, item_name, item_thumbnail, item_option_detail1, item_option_detail2, money, cnt)
		// 			values(4, '2019-07-11_000258', 2, 'test_item2', 'test_thmbnail4', '파란색', 'XL', 20000, 1)
		
	}
	
	
	// 주문 리스트
	@Test
	public void testAOrdersList() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(get("/api/adminorders/list").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 주문리스트 데이터 확인
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		
		.andExpect(jsonPath("$.data.ordersList[0].status", is("입금대기")))
		.andExpect(jsonPath("$.data.ordersList[0].pay", is("무통장입금")))
		.andExpect(jsonPath("$.data.ordersList[0].money", is(10000)))
		.andExpect(jsonPath("$.data.ordersList[0].trackingNum", Matchers.nullValue()))
		.andExpect(jsonPath("$.data.ordersList[0].toName", is("test_name1")))
		.andExpect(jsonPath("$.data.ordersList[0].toPhone", is("01000000001")))
		.andExpect(jsonPath("$.data.ordersList[0].toZipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data.ordersList[0].toAddr", is("test_addr1")))
		.andExpect(jsonPath("$.data.ordersList[0].memberId", is("test_id1")))
		
		.andExpect(jsonPath("$.data.forward", is("admin/orders_list")));
		
	}
	

	
	// 주문 상세보기
	@Test
	public void testBOrdersView() throws Exception {
		ResultActions resultActions;
		
		
		
		// 회원이고 무통장입금일 때
		resultActions = mockMvc.perform(get("/api/adminorders/view/{ordersNo}", "2019-07-11_000256").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 주문리스트 데이터 확인
		// 무통장결제 정보 확인
		// 카카오결제 null인지 확인
		// 비회원 null인지 확인
		// 회원정보 확인
		// 주문상품 리스트 확인
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		
		.andExpect(jsonPath("$.data.ordersVo.status", is("입금대기")))
		.andExpect(jsonPath("$.data.ordersVo.pay", is("무통장입금")))
		.andExpect(jsonPath("$.data.ordersVo.money", is(10000)))
		.andExpect(jsonPath("$.data.ordersVo.trackingNum", Matchers.nullValue()))
		.andExpect(jsonPath("$.data.ordersVo.toName", is("test_name1")))
		.andExpect(jsonPath("$.data.ordersVo.toPhone", is("01000000001")))
		.andExpect(jsonPath("$.data.ordersVo.toZipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data.ordersVo.toAddr", is("test_addr1")))
		.andExpect(jsonPath("$.data.ordersVo.memberId", is("test_id1")))
		
		.andExpect(jsonPath("$.data.payBankVo.bankName", is("국민은행")))
		.andExpect(jsonPath("$.data.payBankVo.bankNumber", is("10000-00-000000")))

		.andExpect(jsonPath("$.data.payKakaoVo", Matchers.nullValue()))

		.andExpect(jsonPath("$.data.guestVo", Matchers.nullValue()))
		
		.andExpect(jsonPath("$.data.memberVo.id", is("test_id1")))
		.andExpect(jsonPath("$.data.memberVo.name", is("test1")))
		.andExpect(jsonPath("$.data.memberVo.phone", is("01000000001")))
		.andExpect(jsonPath("$.data.memberVo.email", is("test_email1@naver.com")))
		.andExpect(jsonPath("$.data.memberVo.zipcode", is("test_zipcode1")))
		.andExpect(jsonPath("$.data.memberVo.addr", is("test_addr1")))
		.andExpect(jsonPath("$.data.memberVo.role", is("USER")))

		.andExpect(jsonPath("$.data.ordersItemList[0].no", is(1)))
		.andExpect(jsonPath("$.data.ordersItemList[0].optionNo", is(1)))
		.andExpect(jsonPath("$.data.ordersItemList[0].itemName", is("test_item1")))
		.andExpect(jsonPath("$.data.ordersItemList[0].itemThumbnail", is("test_thmbnail1")))
		.andExpect(jsonPath("$.data.ordersItemList[0].itemOptionDetail1", is("파란색")))
		.andExpect(jsonPath("$.data.ordersItemList[0].itemOptionDetail2", is("L")))
		.andExpect(jsonPath("$.data.ordersItemList[0].money", is(10000)))
		.andExpect(jsonPath("$.data.ordersItemList[0].cnt", is(1)))
		
		.andExpect(jsonPath("$.data.forward", is("admin/orders_view")));
		
		
		
		
		// 비회원이고 카카오페이일 때
		resultActions = mockMvc.perform(get("/api/adminorders/view/{ordersNo}", "2019-07-11_000257").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// 카카오결제 확인
		// 비회원 확인
		// 포워드할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))

		.andExpect(jsonPath("$.data.payKakaoVo.tid", is("DI201022333")))
		.andExpect(jsonPath("$.data.payKakaoVo.aid", is("AP32292938")))

		.andExpect(jsonPath("$.data.guestVo.guestName", is("test_guest1")))
		.andExpect(jsonPath("$.data.guestVo.guestPhone", is("01000000001")))
		
		.andExpect(jsonPath("$.data.forward", is("admin/orders_view")));

	}
	

	// 무통장 결제확인 상태변경 요청
	@Test
	public void testCOrdersPayCheck() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(put("/api/adminorders/paycheck/{ordersNo}", "2019-07-11_000256").contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// update 결과 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminorders/view")));
		
	}
	
	

	// 운송장번호 등록 요청
	@Test
	public void testDOrdersTrackingNumberCheck() throws Exception {
		
		ResultActions resultActions = mockMvc.perform(put("/api/adminorders/trackingnumbercheck/{ordersNo}", "2019-07-11_000257")
				.param("trackingNum", "9885321457")
				.contentType(MediaType.APPLICATION_JSON));
		
		// 응답이 200 인지
		// 결과가 성공햇는지
		// update 결과 확인
		// 분기할 페이지를 리턴하는지
		resultActions
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")))
		.andExpect(jsonPath("$.data.result", is(true)))
		.andExpect(jsonPath("$.data.redirect", is("/api/adminorders/view")));
		
	}
	
	
	
	
	@AfterClass
	public static void finish() {
		// DB 초기화
	}
	
	
}
