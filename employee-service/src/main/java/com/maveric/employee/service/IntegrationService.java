package com.maveric.employee.service;

import com.maveric.employee.dto.EmployeeDto;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class IntegrationService {
    @ServiceActivator(inputChannel = "integration.gateway.channel")
    public void call(Message<String> message) throws MessagingException {
        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
        MessageBuilder.fromMessage(message);
        Message<String> newMessage = MessageBuilder.withPayload("Welcome " + message.getPayload() + " to spring Integration").build();
        Objects.requireNonNull(replyChannel).send(newMessage);
    }

    @ServiceActivator(inputChannel = "employee.gateway.channel")
    public void getEmployeeDetails(Message<EmployeeDto> message) throws MessagingException {
        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
        MessageBuilder.fromMessage(message);
        Message<EmployeeDto> newMessage = MessageBuilder.withPayload(message.getPayload()).build();
        Objects.requireNonNull(replyChannel).send(newMessage);
    }
}
