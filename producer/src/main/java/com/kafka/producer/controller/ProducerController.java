package com.kafka.producer.controller;

import com.kafka.producer.model.ChatMessage;
import com.kafka.producer.service.GenericProducerService;
import com.kafka.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kafka.producer.Person;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/producer")
public class ProducerController {

    @Value("${topic.name}")
    private String topicName;

    @Value("${topic.avro-person-topic-name}")
    private String avroTestTopic;

    private final ProducerService producerService;

    private final GenericProducerService<String, ChatMessage> genericProducerService;
    private final GenericProducerService<String, Person> personGenericProducerService;

    @PostMapping(value = "/chat")
    public ResponseEntity<?> produceChatMsg(@RequestBody ChatMessage msg){
        producerService.send(msg);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/generic/chat")
    public ResponseEntity<?> produceChatMsgUsingGenericProducer(@RequestBody ChatMessage msg){
        genericProducerService.send(msg.getMsgTo(),msg,topicName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/test/avro")
    public ResponseEntity<?> testAvroSchemaRegistry(@RequestParam("name") String name, @RequestParam("age") Integer age){
        personGenericProducerService.send(name, new Person(name,age),avroTestTopic);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
