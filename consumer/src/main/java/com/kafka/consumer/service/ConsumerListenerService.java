package com.kafka.consumer.service;

import com.kafka.consumer.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.kafka.consumer.Person;
import static com.kafka.consumer.constants.ConsumerConstants.GROUP_ID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsumerListenerService {

    @KafkaListener(topics = "${topic.name}", groupId = GROUP_ID)
    public void consume(ConsumerRecord<String, ChatMessage> payload){
        log.info("Topic: {}", payload.topic());
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partition: {}", payload.partition());
        log.info("payload: {}", payload.value());
    }

    @KafkaListener(topics = "${topic.avro-person-topic-name}", groupId = GROUP_ID)
    public void consumeAvro(ConsumerRecord<String, Person> payload){
        log.info("Topic: {}", payload.topic());
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partition: {}", payload.partition());
        log.info("payload: {}", payload.value());
    }
}
