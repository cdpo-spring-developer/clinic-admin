package com.springlessons.clinicadmin.examples.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendExample {
    private static final Logger log = LoggerFactory.getLogger(SendExample.class);
    private final JavaMailSender javaMailSender;

    public SendExample(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void send(){
        // interface MailSender
        // class MailSenderIml
        // SimpleMailMessage
        // MimeMessageHelper

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("admin@yandex.ru");
        mailMessage.setTo("user@yandex.ru");
        mailMessage.setSubject("Тема письма");
        mailMessage.setText("Текст письма");
        javaMailSender.send(mailMessage);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        FileSystemResource resource = new FileSystemResource("");
        try {
            helper.addAttachment("", resource);
        } catch (MessagingException e) {
            log.error("Файл прикрепить не удалось", e);
        }

        javaMailSender.send(mimeMessage);
    }
}
