package com.fishcount.api.config.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

@Configuration
public class EmailBean {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String hostEmail;

    @Bean
    public void enviarEmailTexto() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setText("msg");
            message.setTo("email");
            message.setFrom(hostEmail);

            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public void enviarEmailHtml(){
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo("email");
            helper.setSubject("assunto");
            helper.setText("msg", true);

            mailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
