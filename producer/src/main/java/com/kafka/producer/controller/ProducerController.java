package com.kafka.producer.controller;

import com.kafka.producer.model.ChatMessage;
import com.kafka.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/producer")
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping(value = "/chat")
    public String produceChatMsg(@RequestBody ChatMessage msg){
        producerService.send(msg);
        return "Published Successfully";
    }

}
