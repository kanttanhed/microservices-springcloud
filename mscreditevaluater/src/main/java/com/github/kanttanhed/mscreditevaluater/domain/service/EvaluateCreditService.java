package com.github.kanttanhed.mscreditevaluater.domain.service;

import com.github.kanttanhed.mscreditevaluater.domain.entity.CustomerCard;
import com.github.kanttanhed.mscreditevaluater.domain.entity.CustomerData;
import com.github.kanttanhed.mscreditevaluater.domain.entity.CustomerStatus;
import com.github.kanttanhed.mscreditevaluater.domain.exception.CustomerDataNotFoundException;
import com.github.kanttanhed.mscreditevaluater.domain.exception.MicroserviceCommunicationException;
import com.github.kanttanhed.mscreditevaluater.domain.feignClient.CardsResourceClient;
import com.github.kanttanhed.mscreditevaluater.domain.feignClient.CustomerResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluateCreditService {

    private final CustomerResourceClient customerResourceClient;
    private final CardsResourceClient cardsResourceClient;
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
}
