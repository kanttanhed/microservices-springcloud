package com.github.kanttanhed.mscards.infrastructure.mqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kanttanhed.mscards.domain.entity.Cards;
import com.github.kanttanhed.mscards.domain.entity.CustomerCards;
import com.github.kanttanhed.mscards.domain.entity.DatasRequestIssueCard;
import com.github.kanttanhed.mscards.domain.repository.CardsRepository;
import com.github.kanttanhed.mscards.domain.repository.CustomerCardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateCardSubscriber {

    private final CardsRepository cardsRepository;
    private final CustomerCardsRepository customerCardsRepository;

    @RabbitListener(queues = "${mq.queues.create-cards}")
    public void receiveIssuanceRequest(@Payload String payload){
        try{
            var mapper = new ObjectMapper();

            DatasRequestIssueCard datasRequestIssueCard = mapper.readValue(payload, DatasRequestIssueCard.class);
            Cards cards = cardsRepository.findById(datasRequestIssueCard.getIdCard()).orElseThrow();

            CustomerCards customerCards = new CustomerCards();
            customerCards.setCards(cards);
            customerCards.setCpf(datasRequestIssueCard.getCpf());
            customerCards.setBasicLimit(datasRequestIssueCard.getBasicLimit());

            customerCardsRepository.save(customerCards);

        }catch (Exception e){
            log.error("Erro ao receber solicitacao de emissao de cartao: {} ", e.getMessage());
        }
    }
}
