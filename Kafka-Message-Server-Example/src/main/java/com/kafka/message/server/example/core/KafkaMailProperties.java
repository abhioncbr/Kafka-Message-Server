package com.kafka.message.server.example.core;

/**
 * The Interface KafkaMailProperties.
 * 
 * @author Abhishek Sharma
 */
public interface KafkaMailProperties{
	final static String topic = "topic1";
	final static String groupId = "group1";
	final static String zkConnect = "127.0.0.1:2181";
    final static String kafkaServerURL = "localhost";
  
    final static int kafkaServerPort = 9092;
    final static int kafkaProducerBufferSize = 64*1024;
    final static int connectionTimeOut = 100000;
    final static int reconnectInterval = 10000;
    
    final static String topic2 = "topic2";
    final static String topic3 = "topic3";
    final static String clientId = "MailConsumerDemoClient";

}
