Kafka-Message-Server Example Application
========================================

Apache kafka is yet another precious gem from Apache Software Foundation. Kafka was originally developed at Linkedin and later on became a member of Apache project.  Apache Kafka is a distributed publish-subscribe messaging system. Kafka differs from traditional messaging system as it is designed as distributed system, persists messages on disk and supports multiple subscribers. 

Kafka-Message-Server is an sample application for demonstrating kafka usage as message-server. Please follow the below instructions  for productive use of the sample application.

1) Download Apache kafka version 0.8.0 zip file from kafka download page and extract it.

2) There is no need to set hadoop or zookeper in your system. You can use zookeper startup script present in bin folder of Kafka.

3) For the execution of the sample application - copy 'kafka-message-server-example-0.8.0.jar' in to the kafka folder where  'kafka_2.8.0-0.8.0.jar' is present. Sample application is dependent on 'commons-cli-1.1.jar'. Copy 'commons-cli-1.1.jar' in to the 'libs' folder of the Apache Kafka.

4) Copy following scripts from 'Kafka-Message-Server-Example/config' folder in to 'bin' folder of kafka
   a) java-mail-content-producer.sh
   b) java-mail-consumer-demo.sh
   c) java-mail-producer-consumer-demo.sh
   d) java-mail-producer-demo.sh
   
   five execution permission to the scripts using chmod command.
   
5) Copy 'commons-cli-1.1.jar' in to the Kafka 'libs' folder.

6) Start Zookeper server using command - bin/zookeeper-server-start.sh config/zookeeper.properties

7) Start Kafka server using command - bin/kafka-server-start.sh config/server.properties

8) Start mail content creation program using command - bin/java-mail-content-producer.sh -path [directory-path]

9) Start message server mail producer using command - bin/java-mail-producer-demo.sh -path [same directory path given above] -topic [topic name]

10) Start message server mail consumer using command - bin/java-mail-consumer-demo.sh -topic [same topic name given above]
