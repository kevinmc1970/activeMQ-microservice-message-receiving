package com.virtualpairprogrammers.messagetesting;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverMDP {
	
	@JmsListener(destination="positionQueue")
	public void processMessage(String msg) {
		System.out.println("message from MDP - " + msg);
		
		// MDP - Message-Driven POJO - listens to queue and subscribes to it and runs callback method 
		
		//simuate message fail going onto DLQ 
		//N.B. message cannot be from console it has to be from messagetesting application because 'persistent'
//		if (0==0) {
//			System.out.println("simulated failure in callback - message will go onto DLQ");
//			throw new RuntimeException("simulated exception");
//		}
	}
}
