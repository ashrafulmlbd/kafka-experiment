# kafka-Experiment

##Kafka Installation on MAC:

Ref: https://kafka.apache.org/quickstart

## Run project : 
**Start the kafka environment first:** 

Start the ZooKeeper service: \
$ bin/zookeeper-server-start.sh config/zookeeper.properties

Start the Kafka broker service: \
$ bin/kafka-server-start.sh config/server.properties

**Run producer and consumer application:** 
1. producer application runs on 8080 \
   $ cd producer/ \
   $ mvn spring-boot:run -DskipTests

2. consumer application runs on 8081 \
   $ cd producer/ \
   $ mvn spring-boot:run -DskipTests

3. Make a POST request to producer(8080) : \
   $ curl -X POST -H "Content-Type: application/json" \
   -d '{"message": "whats up?", "messageId": 10, "msgFrom":"ashraf", "msgTo":"Dip"}'  http://localhost:8080/producer/chat

4. From consumer application console, we can see our consumer application consume that message from kafka topic \
   ![screenshot](./docs/consumer-console.png)

**Sample architecture diagram of (/chat) :** \
   ![architecture](./docs/producer-consumer-architecture.png)

