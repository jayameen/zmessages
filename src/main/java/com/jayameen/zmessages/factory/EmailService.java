package com.jayameen.zmessages.factory;

/**
 * @author Madan KN
 */

import com.jayameen.zmessages.dto.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details, String smtpSender);
    String sendMailWithAttachment(EmailDetails details, String smtpSender);

}
