package com.fishcount.api.config.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailBean {

    @Autowired
    public JavaMailSender mailSender;

    public JavaMailSender getMailSender() {
        return mailSender;
    }
}
