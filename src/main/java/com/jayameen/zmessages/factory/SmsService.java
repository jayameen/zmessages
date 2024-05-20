package com.jayameen.zmessages.factory;

/**
 * @author Madan KN
 */

import com.jayameen.zmessages.dto.SMSDetails;

public interface SmsService {

    String sendSMS(SMSDetails details, String smsSender) throws Exception;

}
