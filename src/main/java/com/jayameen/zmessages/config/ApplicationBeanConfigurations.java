package com.jayameen.zmessages.config;

import com.jayameen.zmessages.factory.EmailService;
import com.jayameen.zmessages.factory.impl.SMTPEmailServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


/**
 * @author Madan KN
 */

@Configuration
public class ApplicationBeanConfigurations {

    @Value("${app.smtp.gmail.host}") protected String GMAIL_SMTP_HOST;
    @Value("${app.smtp.gmail.port}") protected Integer GMAIL_SMTP_PORT;
    @Value("${app.smtp.gmail.username}") protected String GMAIL_USER;
    @Value("${app.smtp.gmail.password}") protected String GMAIL_PASSWORD;

    @Value("${app.smtp.zoho.host}") protected String ZOHO_SMTP_HOST;
    @Value("${app.smtp.zoho.port}") protected Integer ZOHO_SMTP_PORT;
    @Value("${app.smtp.zoho.username}") protected String ZOHO_USER;
    @Value("${app.smtp.zoho.password}") protected String ZOHO_PASSWORD;

    @Value("${app.smtp.debug}") private Boolean debug;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Bean
    public JavaMailSender gmailJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Set up Gmail config
        mailSender.setHost(GMAIL_SMTP_HOST);
        mailSender.setPort(GMAIL_SMTP_PORT);

        // Set up email config (using udeesa email)
        mailSender.setUsername(GMAIL_USER);
        mailSender.setPassword(GMAIL_PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", debug);
        return mailSender;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Bean
    public JavaMailSender zohoJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Set up Gmail config
        mailSender.setHost(ZOHO_SMTP_HOST);
        mailSender.setPort(ZOHO_SMTP_PORT);

        // Set up email config (using udeesa email)
        mailSender.setUsername(ZOHO_USER);
        mailSender.setPassword(ZOHO_PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", debug);
        return mailSender;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Bean
    public EmailService emailService(){
        return new SMTPEmailServiceImpl();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
