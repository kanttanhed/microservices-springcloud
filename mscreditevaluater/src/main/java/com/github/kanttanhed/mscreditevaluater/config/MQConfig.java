package com.github.kanttanhed.mscreditevaluater.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.create-cards}")
    private String IssueCardFila;

    public Queue queueIssueCard(){
        return new Queue(IssueCardFila, true);
    }
}
