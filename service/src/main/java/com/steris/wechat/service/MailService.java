package com.steris.wechat.service;

public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
