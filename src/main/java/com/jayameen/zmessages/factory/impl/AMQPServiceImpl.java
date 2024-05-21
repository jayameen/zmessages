package com.jayameen.zmessages.factory.impl;

/**
 * @author Madan KN
 */

import com.jayameen.zmessages.dto.AMQPDetails;
import com.jayameen.zmessages.factory.AMQPService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AMQPServiceImpl implements AMQPService {

    @Autowired @Qualifier("amqpTemplate") private AmqpTemplate amqpTemplate;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String publishMessage(AMQPDetails details, String amqp) throws Exception {
        switch (amqp) {
            case "rabbitmq":
                publishMessageToRabbitMQ(details);
                return "OK";
            default:
                return null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void publishMessageToRabbitMQ(AMQPDetails details) throws Exception{
        amqpTemplate.convertAndSend(details.getTopicExchange(), details.getRoutingKey(), details.getMessage());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
