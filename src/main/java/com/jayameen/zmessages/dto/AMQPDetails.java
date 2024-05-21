package com.jayameen.zmessages.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Madan KN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AMQPDetails {

    @JsonProperty("topic_exchange")
    private String topicExchange;

    @JsonProperty("routing_key")
    private String routingKey;

    @JsonProperty("message")
    private Object message;

}
