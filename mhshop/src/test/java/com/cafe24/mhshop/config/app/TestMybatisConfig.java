package com.cafe24.mhshop.config.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cafe24.mhshop.vo.CategoryVo;
import com.cafe24.mhshop.vo.GuestVo;
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.MemberVo;
import com.cafe24.mhshop.vo.OptionDetailVo;
import com.cafe24.mhshop.vo.OptionVo;
import com.cafe24.mhshop.vo.OrdersItemVo;
import com.cafe24.mhshop.vo.OrdersVo;

@Configuration
public class TestMybatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/cafe24/config/app/mybatis/configuration.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(sqlSessionFactory);
/*
		sqlSession.delete("ordersitem.test_deleteall");
		sqlSession.delete("option.test_deleteall");
		sqlSession.delete("optiondetail.test_deleteall");
		sqlSession.delete("guest.test_deleteall");
		sqlSession.delete("orders.test_deleteall");
		sqlSession.delete("item.test_deleteall");
		sqlSession.delete("category.test_deleteall");
		sqlSession.delete("member.test_deleteall");
		
		// member
		sqlSession.insert("member.test_insert", new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2030-07-11", "USER", "mhshop_key"));
		sqlSession.insert("member.test_insert", new MemberVo("test_id2", "testpassword2!", "test2", "01000000002", "test_email2@naver.com", "test_zipcode2", "test_addr2", "2030-07-11", "ADMIN", "mhshop_key"));
	
		
		// category
		sqlSession.insert("category.test_insert", new CategoryVo(1L, "test_category1"));
		sqlSession.insert("category.test_insert", new CategoryVo(2L, "test_category2"));

		
		// item
		sqlSession.insert("item.test_insert", new ItemVo(1L, "test_item1", "test_description1", 10000L, "test_thumbnail1", "FALSE", 1L, null));
		sqlSession.insert("item.test_insert", new ItemVo(2L, "test_item2", "test_description2", 20000L, "test_thumbnail2", "FALSE", 1L, null));
		
		
		// orders
		sqlSession.insert("orders.test_insert", new OrdersVo("2019-07-11_000256", "2019-07-11", "입금대기", "국민", "123456789", null, 10000L, null, "test_name1", "01000000001", "test_zipcode1", "test_addr1", "test_id1", "mhshop_key"));
		sqlSession.insert("orders.test_insert", new OrdersVo("2019-07-11_000257", "2019-07-11", "결제완료", "기업", "987654321", null, 20000L, null, "test_name2", "01000000002", "test_zipcode2", "test_addr2", null, "mhshop_key"));
		sqlSession.insert("orders.test_insert", new OrdersVo("2019-07-11_000258", "2019-07-11", "배송중", "국민", "111111111", null, 30000L, null, "test_name3", "01000000003", "test_zipcode3", "test_addr3", null, "mhshop_key"));

		
		// guest
		sqlSession.insert("guest.test_insert", new GuestVo("2019-07-11_000257", "test_guest1", "01000000001", "guestpw1!", "mhshop_key"));
		sqlSession.insert("guest.test_insert", new GuestVo("2019-07-11_000258", "test_guest2", "01000000002", "guestpw2!", "mhshop_key"));
		
		
		// optionDetail
		sqlSession.insert("optiondetail.test_insert", new OptionDetailVo(1L, "파란색", 1L, 1L));
		sqlSession.insert("optiondetail.test_insert", new OptionDetailVo(2L, "L", 2L, 1L));
		sqlSession.insert("optiondetail.test_insert", new OptionDetailVo(3L, "XL", 2L, 1L));
		sqlSession.insert("optiondetail.test_insert", new OptionDetailVo(4L, "XXL", 2L, 1L));
		
		
		// option
		sqlSession.insert("option.test_insert", new OptionVo(1L, 1L, 1L, 2L, 10));
		sqlSession.insert("option.test_insert", new OptionVo(2L, 1L, 1L, 3L, -1));
		
		
		// ordersItem
		sqlSession.insert("ordersitem.test_insert", new OrdersItemVo(1L, "2019-07-11_000256", 1L, "test_item1", "test_thumbnail1", "파란색", "L", 10000L, 1L));
		sqlSession.insert("ordersitem.test_insert", new OrdersItemVo(2L, "2019-07-11_000257", 2L, "test_item2", "test_thumbnail2", "파란색", "XL", 20000L, 1L));
		sqlSession.insert("ordersitem.test_insert", new OrdersItemVo(3L, "2019-07-11_000258", 1L, "test_item1", "test_thumbnail3", "파란색", "L", 10000L, 1L));
		sqlSession.insert("ordersitem.test_insert", new OrdersItemVo(4L, "2019-07-11_000258", 2L, "test_item2", "test_thumbnail4", "파란색", "XL", 20000L, 1L));
		*/
		
		return sqlSession;
	}
}
