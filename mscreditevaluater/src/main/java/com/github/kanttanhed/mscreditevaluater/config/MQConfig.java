package com.github.kanttanhed.mscreditevaluater.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.create-cards}")
    private String IssueCardFila;

    @Bean
    public Queue queueIssueCard(){
        return new Queue(IssueCardFila, true);
    }
}
