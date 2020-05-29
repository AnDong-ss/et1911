package com.etoak.config;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;

@Configuration
@ComponentScan(
		basePackages = {"com.etoak"},
		excludeFilters = {@Filter(type=FilterType.ANNOTATION,
		classes= {ControllerAdvice.class,RestController.class,
				ControllerAdvice.class,EnableWebMvc.class
		})},
		includeFilters = {@Filter(type=FilterType.ANNOTATION,
		classes= {Service.class,Repository.class}
		)}
)
@MapperScan(basePackages = "com.etoak.mapper")
public class RootConfig {
	
	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/et1911");
		dataSource.setUsername("root");
		dataSource.setPassword("etoak");
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSession(){
		SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
		sqlSession.setDataSource(this.dataSource());
		sqlSession.setTypeAliasesPackage("com.etoak.bean");
		
		try {
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource[] resources = resolver.getResources("classpath:/mappers/*.xml");
			sqlSession.setMapperLocations(resources);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PageInterceptor plugin = new PageInterceptor();
		Properties props = new Properties();
		props.setProperty("reasonable", "true");
		plugin.setProperties(props);
		sqlSession.setPlugins(new Interceptor[] {plugin});
		//数据源
		return sqlSession;
	}
}
