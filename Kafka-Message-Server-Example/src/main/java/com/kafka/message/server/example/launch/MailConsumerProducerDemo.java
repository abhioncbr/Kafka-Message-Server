package com.kafka.message.server.example.launch;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import com.kafka.message.server.example.core.KafkaMailConsumer;
import com.kafka.message.server.example.core.KafkaMailProducer;
import com.kafka.message.server.example.core.KafkaMailProperties;
import com.kafka.message.server.example.util.KafkaExampleCommandLineHandler;
import com.kafka.message.server.example.util.KafkaExampleFileUtil;
import com.kafka.message.server.example.util.KafkaExampleProperty;
import com.kafka.message.server.example.util.KafkaExamplePropertyKey;

/**
 * The Class MailConsumerProducerDemo.
 * 
 * @author Abhishek Sharma
 */
public class MailConsumerProducerDemo implements KafkaMailProperties {
	private static final String PATH = "path";
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
			String path  = commandLine.getOption(PATH);
			
			// start producer thread
			KafkaMailProducer producerThread = new KafkaMailProducer(topic!=null? topic : KafkaMailProperties.topic, 
					path !=null? KafkaExampleFileUtil.getValidDirectoryPath(path) :	KafkaExampleProperty.getPropertyValue(KafkaExamplePropertyKey.MAIL_DIRECTORY));
			producerThread.start();
			
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
		Option pathOption = new Option(PATH, PATH, true, "directory path from where message content going to be consumed.");

		optionList.add(topicOption);
		optionList.add(pathOption);
		
		return optionList;
	}

}
