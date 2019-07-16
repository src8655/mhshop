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
		
		// member
		sqlSession.delete("member.test_deleteall");
		sqlSession.insert("member.test_insert", new MemberVo("test_id1", "testpassword1!", "test1", "01000000001", "test_email1@naver.com", "test_zipcode1", "test_addr1", "2019-07-11", "USER", "mhshop_key"));
		sqlSession.insert("member.test_insert", new MemberVo("test_id2", "testpassword2!", "test2", "01000000002", "test_email2@naver.com", "test_zipcode2", "test_addr2", "2019-07-11", "ADMIN", "mhshop_key"));
	
		
		// category
		sqlSession.delete("category.test_deleteall");
		sqlSession.insert("category.test_insert", new CategoryVo(1L, "test_category1"));
		sqlSession.insert("category.test_insert", new CategoryVo(2L, "test_category2"));

		
		
		return sqlSession;
	}
}
