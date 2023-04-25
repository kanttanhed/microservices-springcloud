package com.github.kanttanhed.mscreditevaluater.infrastructure.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kanttanhed.mscreditevaluater.domain.entity.DatasRequestIssueCard;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestIssueCardPublish {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueIssueCard;

    public void cardRequest(DatasRequestIssueCard datasRequestIssueCard) throws JsonProcessingException {
        var json = convertIntoJson(datasRequestIssueCard);
        rabbitTemplate.convertAndSend(queueIssueCard.getName(), json);
    }

    private String convertIntoJson(DatasRequestIssueCard datasRequestIssueCard) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(datasRequestIssueCard);
        return json;
    }
}
