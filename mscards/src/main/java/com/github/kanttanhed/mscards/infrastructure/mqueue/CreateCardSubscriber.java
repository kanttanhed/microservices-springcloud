package com.github.kanttanhed.mscards.infrastructure.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CreateCardSubscriber {

    @RabbitListener(queues = "${mq.queues.create-cards}")
    public void receiveIssuanceRequest(@Payload String payload){
        System.out.println(payload);
    }
}
