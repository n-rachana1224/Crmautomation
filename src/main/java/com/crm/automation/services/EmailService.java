package com.crm.automation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
    private JavaMailSender mailSender;

    /**
     * Send Simple Email Notification
     */
    public void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        try {
            mailSender.send(message);
            System.out.println(" Email Sent Successfully to " + toEmail);
        } catch (Exception e) {
            System.err.println(" Failed to send email to " + toEmail);
            System.err.println("Error: " + e.getMessage());
        }
    }


}
