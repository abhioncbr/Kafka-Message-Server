package com.kafka.message.server.example.launch;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import com.kafka.message.server.example.core.KafkaMailConsumer;
import com.kafka.message.server.example.core.KafkaMailProperties;
import com.kafka.message.server.example.util.KafkaExampleCommandLineHandler;

/**
 * The Class MailConsumerDemo.
 * 
 * @author Abhishek Sharma
 */
public class MailConsumerDemo implements KafkaMailProperties {
	
	private static final String TOPIC_NAME = "topic";
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			KafkaExampleCommandLineHandler commandLine  = new  KafkaExampleCommandLineHandler(getProducerOptions(), args);
			
			String topic = commandLine.getOption(TOPIC_NAME);
			
			//start consumer thread
			KafkaMailConsumer consumerThread = new KafkaMailConsumer(topic!=null? topic : KafkaMailProperties.topic);
			consumerThread.start();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the producer options.
	 *
	 * @return the producer options
	 */
	private static List<Option> getProducerOptions(){
		List<Option> optionList = new ArrayList<Option>();
		
		Option topicOption = new Option(TOPIC_NAME, TOPIC_NAME, true, "topic name on which message is going to be published");

		optionList.add(topicOption);
		
		return optionList;
	}

	
}
