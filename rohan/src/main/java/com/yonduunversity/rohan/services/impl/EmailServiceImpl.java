package com.yonduunversity.rohan.services.impl;

import com.yonduunversity.rohan.services.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.FileSystem;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;
    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("rasistores@yondu.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        log.info("Email sent to: " + to);

        this.mailSender.send(simpleMailMessage);

    }

    @Override
    public void sendCert(String to, String subject, String message, String certPath) throws MessagingException {

        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
        mimeMessageHelper.setFrom("rasistores@yondu.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(message);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(certPath));
        mimeMessageHelper.addAttachment("Certificate.pdf", fileSystemResource);

        log.info("Certificate sent to: " + to);

        this.mailSender.send(mimeMailMessage);

    }
}
