package com.github.kanttanhed.mscreditevaluater.domain.service;

import com.github.kanttanhed.mscreditevaluater.domain.entity.*;
import com.github.kanttanhed.mscreditevaluater.domain.exception.CardRequestException;
import com.github.kanttanhed.mscreditevaluater.domain.exception.CustomerDataNotFoundException;
import com.github.kanttanhed.mscreditevaluater.domain.exception.MicroserviceCommunicationException;
import com.github.kanttanhed.mscreditevaluater.domain.feignClient.CardsResourceClient;
import com.github.kanttanhed.mscreditevaluater.domain.feignClient.CustomerResourceClient;
import com.github.kanttanhed.mscreditevaluater.infrastructure.mqueue.RequestIssueCardPublish;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvaluateCreditService {

    private final CustomerResourceClient customerResourceClient;
    private final CardsResourceClient cardsResourceClient;
    private final RequestIssueCardPublish requestIssueCardPublish;

    public CustomerStatus obtainCustomerStatus(String cpf)
            throws CustomerDataNotFoundException, MicroserviceCommunicationException{
        try {
            ResponseEntity<CustomerData> customerDataResponse = customerResourceClient.CustomerData(cpf);
            ResponseEntity<List<CustomerCard>> cardDataResponse = cardsResourceClient.getCardsByCustomer(cpf);

            return CustomerStatus
                    .builder()
                    .customerData(customerDataResponse.getBody())
                    .cards(cardDataResponse.getBody())
                    .build();

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new MicroserviceCommunicationException(e.getMessage(), status);
        }
    }

    public EvaluationCustomerReturn executeEvaluation(String cpf, Long income)
            throws CustomerDataNotFoundException, MicroserviceCommunicationException {

        try {
            ResponseEntity<CustomerData> customerDataResponse = customerResourceClient.CustomerData(cpf);
            ResponseEntity<List<Card>> cardsResponse = cardsResourceClient.getCardsIncomeUntil(income);

            List<Card> cards = cardsResponse.getBody();
            List<ApprovedCard> listApprovedCards = cards.stream().map(card -> {

                CustomerData customerData = customerDataResponse.getBody();

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageBD = BigDecimal.valueOf(customerData.getAge());
                var factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(basicLimit);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setType(card.getType());
                approvedCard.setBasicLimit(approvedLimit);

                return approvedCard;

            }).collect(Collectors.toList());

            return new EvaluationCustomerReturn(listApprovedCards);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFoundException();
            }
            throw new MicroserviceCommunicationException(e.getMessage(), status);
        }
    }

    public ProtocolCardRequest requestIssueCard(DatasRequestIssueCard datasRequestIssueCard){
        try {
            requestIssueCardPublish.cardRequest(datasRequestIssueCard);
            var protocol = UUID.randomUUID().toString();
            return new ProtocolCardRequest(protocol);
        }catch (Exception e){
            throw new CardRequestException(e.getMessage());
        }
    }
}
