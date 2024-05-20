package com.jayameen.zmessages.factory.impl;

import com.jayameen.zmessages.dto.EmailDetails;
import com.jayameen.zmessages.factory.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Base64;

/**
 * @author Madan KN
 */
public class SMTPEmailServiceImpl implements EmailService {


    @Autowired private JavaMailSender gmailJavaMailSender;
    @Autowired private JavaMailSender zohoJavaMailSender;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String sendSimpleMail(EmailDetails details, String smtpSender) {
        JavaMailSender javaMailSender = getMailSender(smtpSender);
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(details.getFromAddress());
            mailMessage.setTo(details.getToAddress().toArray(new String[0]));
            mailMessage.setCc(details.getCcAddress().toArray(new String[0]));
            mailMessage.setBcc(details.getBccAddress().toArray(new String[0]));
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String sendMailWithAttachment(EmailDetails details, String smtpSender) {
        JavaMailSender javaMailSender = getMailSender(smtpSender);
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to be send
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(details.getFromAddress());
            mimeMessageHelper.setTo(details.getToAddress().toArray(new String[0]));
            mimeMessageHelper.setCc(details.getCcAddress().toArray(new String[0]));
            mimeMessageHelper.setBcc(details.getBccAddress().toArray(new String[0]));

            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            // Adding the attachment
            byte[] decodedBytes = Base64.getDecoder().decode(details.getAttachmentBase64());
            mimeMessageHelper.addAttachment(details.getAttachmentName(), new ByteArrayResource(decodedBytes));

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (Exception e) {
            e.printStackTrace();
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private JavaMailSender getMailSender(String type) {
        switch (type) {
            case "gmail":
                return gmailJavaMailSender;
            case "zoho":
                return zohoJavaMailSender;
            default:
                return null;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
