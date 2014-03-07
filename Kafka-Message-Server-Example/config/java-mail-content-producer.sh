#!/bin/bash
# 
base_dir=$(dirname $0)/..

for file in $base_dir/kafka*.jar;
do
  CLASSPATH=$CLASSPATH:$file
done

# classpath addition for release
for file in $base_dir/libs/commons*.jar;
do
  CLASSPATH=$CLASSPATH:$file
done

echo $CLASSPATH

# Which java to use
if [ -z "$JAVA_HOME" ]; then
  JAVA="java"
else
  JAVA="$JAVA_HOME/bin/java"
fi

$JAVA -cp $CLASSPATH com.kafka.message.server.example.other.launch.CreateFile $@
