Kafka-Message-Server Example Application
========================================

Apache kafka is yet another precious gem from Apache Software Foundation. Kafka was originally developed at Linkedin and later on became a member of Apache project.  Apache Kafka is a distributed publish-subscribe messaging system. Kafka differs from traditional messaging system as it is designed as distributed system, persists messages on disk and supports multiple subscribers. 

Kafka-Message-Server is an sample application for demonstrating kafka usage as message-server. Please follow the below instructions  for productive use of the sample application.

1) Download Apache kafka version 0.8.0 zip file from kafka download page and extract it.

2) There is no need to set hadoop or zookeper in your system. You can use zookeper startup script present in bin folder of Kafka.

3) For the execution of the sample application - copy 'kafka-message-server-example-0.8.0.jar' in to the kafka folder where  'kafka_2.8.0-0.8.0.jar' is present.

4) Copy following scripts from 'Kafka-Message-Server-Example/config' folder in to 'bin' folder of kafka
   a) java-mail-content-producer.sh
   b) java-mail-consumer-demo.sh
   c) java-mail-producer-consumer-demo.sh
   d) java-mail-producer-demo.sh
   
   five execution permission to the scripts using chmod command.
   
5)     
