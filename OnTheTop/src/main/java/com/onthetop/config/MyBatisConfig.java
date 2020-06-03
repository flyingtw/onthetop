package com.onthetop.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(basePackages = "com.onthetop.repository")
public class MyBatisConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		sqlSessionFactory.setMapperLocations(resolver.getResources("mappers/*Mapper.xml"));

		sqlSessionFactory.setTypeAliasesPackage("com.onthetop.domain");
		return sqlSessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);

		sqlSessionTemplate.getConfiguration().setMapUnderscoreToCamelCase(true);

		sqlSessionTemplate.getConfiguration().setUseGeneratedKeys(true);

		return sqlSessionTemplate;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
