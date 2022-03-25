package com.kafka.producer.controller;

import com.kafka.producer.model.Person;
import com.kafka.producer.service.GenericProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/person")
@ConditionalOnProperty(name = "serializer.avro", havingValue = "T")
public class ProducerAvroController {

    @Value("${topic.avro-person-topic-name}")
    private String avroTestTopic;

    private final GenericProducerService<String, Person> personGenericProducerService;

    /**
     For avro serializer/deserializer. Activated when profile- avro
     **/
    @PostMapping(value = "/test/avro")
    public ResponseEntity<?> testAvroSchemaRegistry(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        personGenericProducerService.send(name, new Person(name,age),avroTestTopic);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
