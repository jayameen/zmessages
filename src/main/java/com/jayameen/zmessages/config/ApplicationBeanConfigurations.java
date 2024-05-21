package com.jayameen.zmessages.config;

import com.jayameen.zmessages.factory.AMQPService;
import com.jayameen.zmessages.factory.EmailService;
import com.jayameen.zmessages.factory.SmsService;
import com.jayameen.zmessages.factory.impl.AMQPServiceImpl;
import com.jayameen.zmessages.factory.impl.SMTPEmailServiceImpl;
import com.jayameen.zmessages.factory.impl.SmsServiceImpl;
import com.plivo.api.PlivoClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;


/**
 * @author Madan KN
 */

@Configuration
public class ApplicationBeanConfigurations {

    // Email Config
    @Value("${app.smtp.gmail.host}") protected String GMAIL_SMTP_HOST;
    @Value("${app.smtp.gmail.port}") protected Integer GMAIL_SMTP_PORT;
    @Value("${app.smtp.gmail.username}") protected String GMAIL_USER;
    @Value("${app.smtp.gmail.password}") protected String GMAIL_PASSWORD;

    @Value("${app.smtp.zoho.host}") protected String ZOHO_SMTP_HOST;
    @Value("${app.smtp.zoho.port}") protected Integer ZOHO_SMTP_PORT;
    @Value("${app.smtp.zoho.username}") protected String ZOHO_USER;
    @Value("${app.smtp.zoho.password}") protected String ZOHO_PASSWORD;

    @Value("${app.smtp.debug}") private Boolean debug;

    // Sms Config
    @Value("${app.sms.plivo.auth-id}") private String PLIVO_AUTH_ID;
    @Value("${app.sms.plivo.auth-token}") private String PLIVO_AUTH_TOKEN;


    // RabbitMQ Config
    @Value("${rabbitmq.host}") private String rmqHost;
    @Value("${rabbitmq.port}") private String rmqPort;
    @Value("${rabbitmq.username}") private String rmqUsername;
    @Value("${rabbitmq.password}") private String rmqPassword;
    @Value("${rabbitmq.vhost}") private String rmqVhost;

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
    @Bean("plivoClient")
    public PlivoClient plivoClient() {
        PlivoClient client = new PlivoClient(PLIVO_AUTH_ID, PLIVO_AUTH_TOKEN);
        return client;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Bean
    public EmailService emailService(){
        return new SMTPEmailServiceImpl();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Bean
    public SmsService smsService(){
        return new SmsServiceImpl();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Bean
    public AMQPService amqpService(){
        return new AMQPServiceImpl();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setVirtualHost(rmqVhost);
        connectionFactory.setHost(rmqHost);
        connectionFactory.setPort(Integer.parseInt(rmqPort));
        connectionFactory.setUsername(rmqUsername);
        connectionFactory.setPassword(rmqPassword);
        return connectionFactory;
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setUseDirectReplyToContainer(false);
        rabbitTemplate.setMessageConverter(objectToJsonConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter objectToJsonConverter(){
        return new Jackson2JsonMessageConverter();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
