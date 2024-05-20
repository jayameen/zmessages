package com.jayameen.zmessages.api;

import com.jayameen.zmessages.BaseController;
import com.jayameen.zmessages.dto.AppResponse;
import com.jayameen.zmessages.dto.EmailDetails;
import com.jayameen.zmessages.factory.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Madan KN
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ZMailAPI extends BaseController {

    @Value("${app.mailer}") protected String MAILER;

     private final EmailService emailService;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/mail")
    public ResponseEntity<AppResponse> sendMail(@RequestBody EmailDetails details) {
        AppResponse appResponse = new AppResponse<>();
        handleObjectSuccess(appResponse, emailService.sendSimpleMail(details,MAILER));
        return ResponseEntity.ok(appResponse);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/mail/base64")
    public ResponseEntity<AppResponse> sendMailBase64(@RequestBody EmailDetails details) {
        AppResponse appResponse = new AppResponse<>();
        handleObjectSuccess(appResponse, emailService.sendMailWithAttachment(details,MAILER));
        return ResponseEntity.ok(appResponse);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
