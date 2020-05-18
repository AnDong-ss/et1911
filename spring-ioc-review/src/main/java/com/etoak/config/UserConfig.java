package com.etoak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.etoak.action.UserAction;
import com.etoak.service.UserService;

@Configuration //相当于xml的根元素<beans>标签 表示一个spring ioc容器<BR>
public class UserConfig {
	//使用@bean注册spring bean
	@Bean("userService")
	public UserService userService() {
		return new UserService();
	}
	
	@Bean
	public UserAction userAction(@Qualifier("userService")UserService userService) {
		UserAction userAction = new UserAction();
		//配合@Qualifier 相当于省略Autowired
		userAction.setUserService(userService);
		return userAction;
	}
	
}
