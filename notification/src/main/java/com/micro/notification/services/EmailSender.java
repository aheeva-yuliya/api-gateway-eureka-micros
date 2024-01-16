package com.micro.notification.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSender {

    public void sendEmail(String orderNumber) {
        log.info("Order placed Successfully - Order Number is " + orderNumber);
        log.info("Email sent");
    }
}
