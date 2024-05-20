package com.jayameen.zmessages.api;

import com.jayameen.zmessages.dto.EmailDetails;
import com.jayameen.zmessages.factory.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Madan KN
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ZMessagesAPI {

     private final EmailService emailService;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/mail")
    public String sendMail(@RequestBody EmailDetails details) {
        String status = emailService.sendSimpleMail(details,"zoho");
        return status;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/mail/base64")
    public String sendMailBase64(@RequestBody EmailDetails details) {
        String status = emailService.sendMailWithAttachment(details,"zoho");
        return status;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
