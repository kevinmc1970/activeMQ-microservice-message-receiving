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
		// can get spring context from .run call and then get JmsTemplate using it
		ApplicationContext ctx = SpringApplication.run(MessageReceivingApplication.class, args);
		
		// simple testing of JMS recieve here
		JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
		
//		Message message = jmsTemplate.receive("positionQueue");
		String msg = jmsTemplate.receiveAndConvert("positionQueue").toString();
//		TextMessage textMessage = (TextMessage)message;
		
//		System.out.println(textMessage.getText() + " - message from queue and message will be consumed (deleted) from queue");
		System.out.println(msg + " - message from queue and message will be consumed (deleted) from queue");
		
		System.out.println("if queue empty this code will sit and wait indefinitely ( default 0) for message to appear - " + jmsTemplate.getReceiveTimeout());
		System.out.println("so can go to console and add one or run messagetesting application");
		
		// N.B. springboot will create an ActiveMQ embedded queue if haven't create an external broker (i.e. "positionQueue")
		// need to edit application.properties
		// spring.activemq.broker-url = localhost:61616
		
		// when running this springboot app the message 'this is a test' will appear in the retrieved from the quueu and output to the console 
		// http://localhost:8161/admin/browse.jsp?JMSDestination=positionQueue
		// after running broker C:\Users\becky\Documents\apache-activemq-5.15.8\bin>activemq start
		// this queue is persisted BTW as created in admin console
	}

}

