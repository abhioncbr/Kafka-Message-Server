package com.kafka.message.server.example.launch;

import com.kafka.message.server.example.core.KafkaMailProducer;
import com.kafka.message.server.example.core.KafkaMailProperties;

/**
 * The Class MailProducerDemo.
 * 
 * @author Abhishek Sharma
 */
public class MailProducerDemo implements KafkaMailProperties {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		KafkaMailProducer producerThread = new KafkaMailProducer(KafkaMailProperties.topic, 	KafkaMailProperties.mailDirectory);
		producerThread.start();
	}
}
