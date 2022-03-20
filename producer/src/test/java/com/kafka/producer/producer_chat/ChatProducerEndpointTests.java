package com.kafka.producer.producer_chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.producer.common.IntegrationTests;
import com.kafka.producer.model.ChatMessage;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ChatProducerEndpointTests extends IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String endpoint = "/producer/chat";
    private final String genericMsgProducerEndpoint = "/producer/generic/chat";

    @Test
    @DisplayName("Should throw 200 when produce msg to broker")
    public void should_Throw200_When_ProduceChatMessage() throws Exception {
        mockMvc.perform(post(endpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getChatMsgObj())
                        )
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should throw 200 when produce msg to broker using generic producer service")
    public void should_Throw200_When_ProduceChatMessageUsingGenericProducerService() throws Exception {
        mockMvc.perform(post(genericMsgProducerEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                getChatMsgObj())
                        )
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    public ChatMessage getChatMsgObj(){
        ChatMessage msg = ChatMessage
                .builder()
                .message("test msg")
                .messageId(12)
                .msgFrom("A")
                .msgTo("B")
                .build();
        return msg;
    }


}
