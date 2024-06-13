package com.emailservice.consumers;

import com.emailservice.dtos.SendEmailMessageDto;
import com.emailservice.services.EmailUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${EMAIL_USERNAME}")
    private String emailUsername;
    @Value("${EMAIL_PASSWORD}")
    private String emailPassword;
    @KafkaListener(topics = "sendEmail", groupId = "emailService")
    public void handleSendEmail(String message) throws Exception{
        System.out.println("Received send email message: " + message);
        SendEmailMessageDto sendEmailMessageDto =
                objectMapper.readValue(message, SendEmailMessageDto.class);
        System.out.println("Sending email to: " + sendEmailMessageDto.getTo());
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(emailUsername, emailPassword);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, sendEmailMessageDto.getTo(), sendEmailMessageDto.getSubject(), sendEmailMessageDto.getBody());

    }
}
