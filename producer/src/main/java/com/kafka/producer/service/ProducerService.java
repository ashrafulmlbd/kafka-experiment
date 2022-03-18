package com.kafka.producer.service;


import com.kafka.producer.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public void send(ChatMessage msg){

        // actually send the message
        ListenableFuture<SendResult<String, ChatMessage>> listenableFuture =
                kafkaTemplate.send(topicName, msg.getMsgTo(), msg);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, ChatMessage>>() {

            @Override
            public void onSuccess(SendResult<String, ChatMessage> result) {
                log.info("message sent, partition={}, offset={},  Payload: {}", result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset(),result.getProducerRecord().value());
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.warn("failed to send, message={}", msg, throwable);
            }
        });
    }
}
