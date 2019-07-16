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
import com.cafe24.mhshop.vo.ItemVo;
import com.cafe24.mhshop.vo.MemberVo;

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
		
		sqlSession.delete("item.test_deleteall");
		sqlSession.delete("category.test_deleteall");
		sqlSession.delete("member.test_deleteall");
		
		// member
		sqlSession.insert("member.test_insert", new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key"));
		sqlSession.insert("member.test_insert", new MemberVo("test_id2", "testpassword2!", "test2", "01000000002", "test_email2@naver.com", "test_zipcode2", "test_addr2", "2019-07-11", "ADMIN", "mhshop_key"));
	
		
		// category
		sqlSession.insert("category.test_insert", new CategoryVo(1L, "test_category1"));
		sqlSession.insert("category.test_insert", new CategoryVo(2L, "test_category2"));

		
		// item
		sqlSession.insert("item.test_insert", new ItemVo(1L, "test_item1", "test_description1", 10000L, "test_thumbnail1", "FALSE", 1L, null));
		sqlSession.insert("item.test_insert", new ItemVo(2L, "test_item2", "test_description2", 20000L, "test_thumbnail2", "FALSE", 1L, null));

		
		return sqlSession;
	}
}
