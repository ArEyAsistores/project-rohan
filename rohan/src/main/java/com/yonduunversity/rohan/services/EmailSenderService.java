package com.yonduunversity.rohan.services;

import jakarta.mail.MessagingException;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
    void sendCert(String to, String subject, String message, String certPath) throws MessagingException;
}
