package com.notification.producer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.producer.exception.MapperException;
import com.notification.producer.model.Notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleNotificationServiceImpl implements NotificationService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final ProducerService brokerProducerService;
    private final Environment env;

    @Override
    public void send(Notification notification) {
        brokerProducerService.sendMessage(env.getProperty("producer.kafka.topic-name"), toJson(notification));
    }

    private <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}
