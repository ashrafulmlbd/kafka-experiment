package com.kafka.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericProducerService <K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public void send(K key, V value, String topicName){

        ListenableFuture<SendResult<K, V>> listenableFuture = key != null ? kafkaTemplate.send(topicName, key, value) : kafkaTemplate.send(topicName, value);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<K, V>>() {

            @Override
            public void onSuccess(SendResult<K, V> result) {
                log.info("message sent, partition={}, offset={},  Payload: {}", result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset(),result.getProducerRecord().value());
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.warn("failed to send, message={}", value, throwable);
            }
        });
    }
}
