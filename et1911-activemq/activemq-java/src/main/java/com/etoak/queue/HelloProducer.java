package com.etoak.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloProducer {
	public static void main(String[] args) throws JMSException {
		//1.创建ConnectionFactory(用户名,密码,连接地址)
		ConnectionFactory factory = new ActiveMQConnectionFactory(null,null,"tcp://localhost:61616");
		//2.创建Connection
		Connection connection = factory.createConnection();
		connection.start();
		//3.通过Connection创建session(是否启动事务,)
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		//4.通过session创建Destination(创建队列)
		//如果队列不存在,那么activemq会自动创建
		Queue helloQueue = session.createQueue("Hello");
		//5.通过session创建消息生产者
		MessageProducer producer = session.createProducer(helloQueue);
		//6.通过session创建消息
		TextMessage msg = session.createTextMessage("这是第一个Hello World消息");
		//7.使用消息发送者发送消息
		producer.send(msg);
		
		producer.close();
		session.close();
		connection.close();
		System.out.println("OK");
	}

}
