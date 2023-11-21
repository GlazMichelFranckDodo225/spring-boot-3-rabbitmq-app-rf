package com.dgmf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // Spring Bean for RabbitMQ Queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    // Spring Bean for RabbitMQ Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }
    // Binding between "queue()" and "exchange()" using "Routing Key"
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    // Spring Boot Autoconfiguration will automatically configure the
    // three following Beans for us :
    //  - Spring Bean for RabbitMQ Connection Factory
    //  - Spring Bean for RabbitMQ Template
    //  - Spring Bean for RabbitMQ Admin
}
