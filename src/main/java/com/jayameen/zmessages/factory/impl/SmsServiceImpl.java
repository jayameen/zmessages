package com.jayameen.zmessages.factory.impl;

import com.jayameen.zmessages.dto.SMSDetails;
import com.jayameen.zmessages.factory.SmsService;
import com.plivo.api.PlivoClient;
import com.plivo.api.models.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Madan KN
 */
public class SmsServiceImpl implements SmsService {

    @Autowired @Qualifier("plivoClient") private PlivoClient plivoClient;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String sendSMS(SMSDetails details, String smsSender) throws Exception {
        switch (smsSender) {
            case "plivo":
                return sendSMSUsingPlivo(details);
            default:
                return null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String sendSMSUsingPlivo(SMSDetails details) throws Exception{
        Message.creator(details.getSenderName(), details.getPhonePrefix()+details.getPhoneNumber(), details.getContent())
                .client(plivoClient)
                .create();
        return "OK";
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
