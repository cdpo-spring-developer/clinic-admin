package com.springlessons.clinicadmin.examples.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

// Simple Mail Transfer Protocol (smtp) — простой протокол передачи писем. Используется для рассылки писем.
// SMTP использует порт 25 или 587. А его защищенная версия SMTPS слушает порт 465.

// Post Office Protocol v3 (POP3) — почтовый протокол. Используется для чтения писем.
// POP3 при выкачивании письма на клиентский компьютер удаляет письмо с почтового сервера.
// При просмотре письма на одном клиентском компьютере это письмо уже нельзя будет посмотреть с другого устройства.
// POP3 слушает порт 110. А его защищенная версия POP3S слушает порт 995.

// Internet Message Access Protocol (IMAP) — протокол доступа к электронной почте. Альтернатива протоколу POP3.
// IMAP подгружает на клиент только мета-информацию письма, а остальные данные предоставляет по требованию.
// IMAP слушает порт 143. А его защищенная версия IMAPS слушает порт 993.

// Multipurpose Internet Mail Extensions (MIME) — многоцелевые расширения интернет-почты.
// MIME используется, для того чтобы обозначить тип передаваемого контента

public class SendExample {
    private static final Logger log = LoggerFactory.getLogger(SendExample.class);
    private final JavaMailSender javaMailSender;

    public SendExample(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void send() throws MessagingException {
        // interface MailSender | class MailSenderIml
        // SimpleMailMessage | MimeMessageHelper

        // письмо без вложений
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("admin@yandex.ru");
        mailMessage.setTo("user@yandex.ru");
        mailMessage.setSubject("Тема письма");
        mailMessage.setText("Текст письма");
        javaMailSender.send(mailMessage); // отправка

        // письмо с вложениями
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom("admin@yandex.ru");
        // ...
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        FileSystemResource resource = new FileSystemResource("путь к файлу");
        try {
            helper.addAttachment("filename_in_mail", resource);
        } catch (MessagingException e) {
            log.error("Файл прикрепить не удалось", e);
        }
        javaMailSender.send(mimeMessage); // отправка
    }
}
