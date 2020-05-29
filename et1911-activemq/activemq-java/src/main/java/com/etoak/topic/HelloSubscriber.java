package com.etoak.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloSubscriber {

	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(
				null,null,"tcp://localhost:61616");
		Connection connection = factory.createConnection();
		connection.start();
		Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("topic");
		MessageConsumer consumer = session.createConsumer(topic);
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				if(message instanceof MapMessage) {
					MapMessage msg = (MapMessage)message;
					try {
						int id = msg.getInt("id");
						String name = msg.getString("name");
						System.out.println(id+" - "+name);
					}catch (JMSException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

}
