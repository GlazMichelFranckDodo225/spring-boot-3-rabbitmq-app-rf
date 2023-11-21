package com.dgmf.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RabbitMQProducer.class);
    private RabbitTemplate rabbitTemplate;

    // Single Parameter Constructor ==> @Autowired can be omitted
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent -> %s", message));

        // The Message will be sent to the Exchange, and
        // the Exchange will use the RoutingKey to
        // route the Message to the Queue
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
