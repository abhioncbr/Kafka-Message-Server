package com.kafka.message.server.example.launch;

import com.kafka.message.server.example.core.KafkaMailConsumer;
import com.kafka.message.server.example.core.KafkaMailProperties;

/**
 * The Class MailConsumerDemo.
 * 
 * @author Abhishek Sharma
 */
public class MailConsumerDemo implements KafkaMailProperties {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		KafkaMailConsumer consumerThread = new KafkaMailConsumer(KafkaMailProperties.topic);
		consumerThread.start();
	}
	
}
