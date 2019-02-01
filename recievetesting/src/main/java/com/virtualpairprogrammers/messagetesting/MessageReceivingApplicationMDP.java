package com.virtualpairprogrammers.messagetesting;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class MessageReceivingApplicationMDP {

	public static void main(String[] args) throws JMSException {
		// from youtube tutorial https://www.youtube.com/watch?v=eqFgNouG490
		// and https://www.youtube.com/watch?v=QTVmsI-oYd0
		// the non-MDP version of this is app is the incorrect way to consume (it acts like a poller rather than subscriber)
		// -- wondering if that is actually ok as a poller
		// this time going to use a Message Driven POJO i.e. a class with a callback method - this is the correct way to consume!
		// code will look up POJOs containing JmsListener annotations and let their callbacks be called when required 
		SpringApplication.run(MessageReceivingApplicationMDP.class, args);
		
		// so callback could be in a separate microservice
		// and do something like get message then get data from it and persist it or validation it or pass it to another microservice
		// via another queue etc
		
		// if crash in callback the message is requeued so not lost!
		// code will retry a few times then add message Dead Letter Queue - ActiveMQ DLQ
		
	}

}

