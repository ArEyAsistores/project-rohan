package com.yonduunversity.rohan.services;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}
