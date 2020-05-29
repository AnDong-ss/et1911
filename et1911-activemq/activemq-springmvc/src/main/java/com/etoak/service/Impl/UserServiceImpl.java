package com.etoak.service.Impl;



import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.etoak.bean.Email;
import com.etoak.bean.User;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Override
	public void addUser(User user) {
		String userId = UUID.randomUUID().toString().replace("-","");
		user.setUserId(userId);
		
		userMapper.addUser(user);
		
		jmsTemplate.send("email",new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				Email email = new Email(
						user.getEmail(),
						"注册激活邮件",
						"请点击右侧链接:http://localhost:8080/activemq-springmvc/user/active/"+userId);
				
				return session.createTextMessage(JSONObject.toJSONString(email));
			}
		});
	}

}
