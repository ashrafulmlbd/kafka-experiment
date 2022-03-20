package com.kafka.consumer.service;

import com.kafka.consumer.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.kafka.consumer.constants.ConsumerConstants.GROUP_ID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsumerListenerService {

    @KafkaListener(topics = "${topic.name.consumer}", groupId = GROUP_ID)
    public void consume(ConsumerRecord<String, ChatMessage> payload){
        log.info("Topic: {}", payload.topic());
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partition: {}", payload.partition());
        log.info("payload: {}", payload.value());
    }
}
