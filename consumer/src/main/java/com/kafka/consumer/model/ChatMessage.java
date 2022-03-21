package com.kafka.consumer.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessage implements Serializable {
    private String message;
    private Integer messageId;
    private String msgFrom;
    private String msgTo;
    private Date date = new Date();

}
