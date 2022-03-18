package com.kafka.producer.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ChatMessage {
    private String message;
    private Integer messageId;
    private String msgFrom;
    private String msgTo;
    private Date date = new Date();

}
