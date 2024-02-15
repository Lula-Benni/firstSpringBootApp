package com.lulamile.firstSpringBootApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class AppConfig {
    @Value("${mail.transport.protocol}")
    private String mail_transport_protocol;
    @Value("${spring.mail.host}")
    private String spring_mail_host;
    @Value("${spring.mail.port}")
    private String spring_mail_port;
    @Value("${spring.mail.username}")
    private String spring_mail_username;
    @Value("${spring.mail.password}")
    private String spring_mail_password;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String spring_mail_properties_mail_smtp_auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String spring_mail_properties_mail_smtp_starttls_enable;
    @Value("${spring.mail.smtp.ssl.trust}")
    private String spring_mail_smtp_ssl_trust;
    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(spring_mail_host);
        javaMailSender.setPort(Integer.parseInt(spring_mail_port));
        javaMailSender.setUsername(spring_mail_username);
        javaMailSender.setPassword(spring_mail_password);

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol",mail_transport_protocol);
        properties.put("mail.smtp.auth",spring_mail_properties_mail_smtp_auth);
        properties.put("mail.smtp.starttls.enable",spring_mail_properties_mail_smtp_starttls_enable);
        properties.put("mail.debug","true");
        properties.put("mail.smtp.ssl.trust",spring_mail_smtp_ssl_trust);

        return javaMailSender;
    }
}
