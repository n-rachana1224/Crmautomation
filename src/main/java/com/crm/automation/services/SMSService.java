package com.crm.automation.services;

import org.springframework.stereotype.Service;

@Service
public class SMSService {

    // TODO: Replace with  SMS provider 
    public static String sendSMS(String phoneNumber, String message) {
        System.out.println("SMS Sent!");
        System.out.println("To: " + phoneNumber);
        System.out.println("Message: " + message);

        // In real implementation → call SMS API here
        return "SMS successfully sent to " + phoneNumber;
    }

	
}
