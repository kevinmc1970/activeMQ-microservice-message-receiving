package com.virtualpairprogrammers.messagetesting;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class MessageReceivingApplication {

	public static void main(String[] args) throws JMSException {
		// from youtube tutorial https://www.youtube.com/watch?v=eqFgNouG490
		// and https://www.youtube.com/watch?v=QTVmsI-oYd0
		// simple testing of messaging in this class rather than a 'proper' application class
		// can get spring context from .run call below and then get JmsTemplate using context i.e. get the JMSTemplate from Spring context
		// provided by spring-boot-starter-activemq in pom.xml
		ApplicationContext ctx = SpringApplication.run(MessageReceivingApplication.class, args);
		
		// JMSTemplate used to send/recieve messages from the MQ
		JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
		
		// can get Message object from queue
		// Message message = jmsTemplate.receive("positionQueue");
		// TextMessage textMessage = (TextMessage)message;
		// System.out.println(textMessage.getText() + " - message from queue and message will be consumed (deleted) from queue");
		// or get and convert it to type in single step
		String msg = jmsTemplate.receiveAndConvert("positionQueue").toString();

		// message is deleted from queue when consumed
		System.out.println(msg + " - message from queue and message will be consumed (deleted) from queue");
		
		// if no message code will wait...
		System.out.println("if queue empty this code will sit and wait indefinitely ( default 0) for message to appear - " + jmsTemplate.getReceiveTimeout());
		System.out.println("so can go to console and add one or run my messagetesting application");
		
		// N.B. springboot will create an ActiveMQ embedded queue if haven't create an external broker (i.e. "positionQueue")
		// need to edit application.properties
		// spring.activemq.broker-url = localhost:61616
		
		// when running this springboot app the message 'this is a test' will appear in the console as consumed from the queue  
		// http://localhost:8161/admin/browse.jsp?JMSDestination=positionQueue
		// after running broker C:\Users\becky\Documents\apache-activemq-5.15.8\bin>activemq start
		// this queue is persisted BTW as created in admin console
		
		// The 'MDP' version of this application uses the correct way of consuming i.e. using a POJO with a method callback
		// which is called when something appears on the queue i.e. it listens and acts like a subscriber
	}

}

