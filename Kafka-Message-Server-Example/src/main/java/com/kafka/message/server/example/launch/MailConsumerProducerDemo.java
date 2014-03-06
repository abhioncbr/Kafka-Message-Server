package com.kafka.message.server.example.launch;

import com.kafka.message.server.example.core.KafkaMailConsumer;
import com.kafka.message.server.example.core.KafkaMailProducer;
import com.kafka.message.server.example.core.KafkaMailProperties;

/**
 * The Class MailConsumerProducerDemo.
 * 
 * @author Abhishek Sharma
 */
public class MailConsumerProducerDemo implements KafkaMailProperties {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		KafkaMailProducer producerThread = new KafkaMailProducer(KafkaMailProperties.topic, 	KafkaMailProperties.mailDirectory);
		producerThread.start();

		KafkaMailConsumer consumerThread = new KafkaMailConsumer(KafkaMailProperties.topic);
		consumerThread.start();

	}
}
