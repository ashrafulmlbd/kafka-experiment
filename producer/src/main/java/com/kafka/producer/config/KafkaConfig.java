package com.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${topic.name}")
    private String topicName;

    @Value("${topic.partitions-num}")
    private Integer partitions;

    @Value("${topic.replication-factor}")
    private short replicationFactor;

    @Value("${topic.avro-person-topic-name}")
    private String avroPersonTopicName;

    @Value("${topic.partitions-num}")
    private Integer avroPersonPartitions;

    @Value("${topic.replication-factor}")
    private short avroPersonReplicationFactor;

    @Bean
    public NewTopic producerConsumerTopic() {
        return new NewTopic(topicName, partitions, replicationFactor);
    }

    @Bean
    public NewTopic producerAvroTestTopic() {
        return new NewTopic(avroPersonTopicName, avroPersonPartitions, avroPersonReplicationFactor);
    }

}
