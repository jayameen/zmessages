package com.jayameen.zmessages.api;

import com.jayameen.zmessages.BaseController;
import com.jayameen.zmessages.dto.AMQPDetails;
import com.jayameen.zmessages.dto.AppResponse;
import com.jayameen.zmessages.dto.SMSDetails;
import com.jayameen.zmessages.factory.AMQPService;
import com.jayameen.zmessages.factory.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

/**
 * @author Madan KN
 */

@RestController
@RequestMapping("/api/amqp")
@RequiredArgsConstructor
public class ZAmqpAPI extends BaseController {

     @Value("${app.amqp}") protected String amqp;
     private final AMQPService amqpService;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/publish")
    public ResponseEntity<AppResponse> publish(@RequestBody AMQPDetails details) throws Exception {
        AppResponse appResponse = new AppResponse<>();
        handleObjectSuccess(appResponse, amqpService.publishMessage(details,amqp));
        return ResponseEntity.ok(appResponse);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
