package com.micro.notification.consumer;

import com.micro.notification.services.EmailSender;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class NotificationConsumer {

    @Bean
    public Consumer<Message<String>> input() {
        return message -> new EmailSender().sendEmail(message.getPayload());
    }

}
