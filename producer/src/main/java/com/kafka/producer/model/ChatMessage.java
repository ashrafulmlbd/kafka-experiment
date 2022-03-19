package com.kafka.producer.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Builder
@Data
@ToString
public class ChatMessage implements Serializable {
    private String message;
    private Integer messageId;
    private String msgFrom;
    private String msgTo;

}
