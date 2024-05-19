package com.jayameen.zmessages.factory.impl;

import com.jayameen.zmessages.dto.EmailDetails;
import com.jayameen.zmessages.factory.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Madan KN
 */
public class SMTPEmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender gmailJavaMailSender;


    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

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
            gmailJavaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details) {
        // Creating a mime message
        MimeMessage mimeMessage = gmailJavaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(details.getFromAddress());
            mimeMessageHelper.setTo(details.getToAddress().toArray(new String[0]));
            mimeMessageHelper.setCc(details.getCcAddress().toArray(new String[0]));
            mimeMessageHelper.setBcc(details.getBccAddress().toArray(new String[0]));

            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(file.getFilename(), file);

            // Sending the mail
            gmailJavaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (Exception e) {
            e.printStackTrace();
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

}
