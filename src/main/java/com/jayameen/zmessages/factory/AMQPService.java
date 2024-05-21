package com.jayameen.zmessages.factory;

/**
 * @author Madan KN
 */

import com.jayameen.zmessages.dto.AMQPDetails;

public interface AMQPService {

    String publishMessage(AMQPDetails details, String amqp) throws Exception;

}
