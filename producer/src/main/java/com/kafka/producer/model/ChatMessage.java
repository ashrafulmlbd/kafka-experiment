package com.kafka.producer.model;

import lombok.*;

import java.io.Serializable;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage implements Serializable {
    private String message;
    private Integer messageId;
    private String msgFrom;
    private String msgTo;

}
