package com.kafka.producer.controller;

import com.kafka.producer.model.ChatMessage;
import com.kafka.producer.service.GenericProducerService;
import com.kafka.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/producer")
@ConditionalOnProperty(name = "serializer.avro", havingValue = "F")
public class ProducerJsonController {

    @Value("${topic.name}")
    private String topicName;

    private final ProducerService producerService;
    private final GenericProducerService<String, ChatMessage> genericProducerService;

    /**
     For Json serializer/deserializer. Activated when profile not equal - avro
     **/
    @PostMapping(value = "/chat")
    public ResponseEntity<?> produceChatMsg(@RequestBody ChatMessage msg) {
        producerService.send(msg);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    /**
     For Json serializer/deserializer. Activated when profile not equal - avro
     **/
    @PostMapping(value = "/generic/chat")
    public ResponseEntity<?> produceChatMsgUsingGenericProducer(@RequestBody ChatMessage msg) {
        genericProducerService.send(msg.getMsgTo(),msg,topicName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
