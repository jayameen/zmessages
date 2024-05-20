package com.jayameen.zmessages.api;

import com.jayameen.zmessages.BaseController;
import com.jayameen.zmessages.dto.AppResponse;
import com.jayameen.zmessages.dto.EmailDetails;
import com.jayameen.zmessages.dto.SMSDetails;
import com.jayameen.zmessages.factory.EmailService;
import com.jayameen.zmessages.factory.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madan KN
 */

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class ZSmsAPI extends BaseController {

    @Value("${app.smser}") protected String SMSER;

     private final SmsService smsService;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping()
    public ResponseEntity<AppResponse> sendSMS(@RequestBody SMSDetails details) throws Exception {
        AppResponse appResponse = new AppResponse<>();
        handleObjectSuccess(appResponse, smsService.sendSMS(details,SMSER));
        return ResponseEntity.ok(appResponse);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
