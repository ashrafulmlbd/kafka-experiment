package com.kafka.consumer.service;

import com.kafka.consumer.model.avro.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.kafka.consumer.constants.ConsumerConstants.GROUP_ID;

@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "serializer.avro", havingValue = "T")
@Service
public class ConsumerAvroListenerService {
    /**
     For Avro serializer/deserializer. Activated when profile - avro
     **/
    @KafkaListener(topics = "${topic.avro-person-topic-name}", groupId = GROUP_ID)
    public void consumeAvro(ConsumerRecord<String, Person> payload){
        log.info("Topic: {}", payload.topic());
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partition: {}", payload.partition());
        log.info("payload: {}", payload.value());
    }
}
