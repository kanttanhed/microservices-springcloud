package com.github.kanttanhed.mscreditevaluater.domain.service;

import com.github.kanttanhed.mscreditevaluater.domain.entity.CustomerData;
import com.github.kanttanhed.mscreditevaluater.domain.entity.CustomerStatus;
import com.github.kanttanhed.mscreditevaluater.domain.feignClient.CustomerResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluateCreditService {

    private final CustomerResourceClient customerResourceClient;

    //Getting Customer data - MSCustomer
    public CustomerStatus obtainCustomerStatus(String cpf){

        ResponseEntity<CustomerData> customerDataResponse =  customerResourceClient.CustomerDatas(cpf);

        return CustomerStatus
                .builder()
                .customerData(customerDataResponse.getBody())
                .build();
    }
}
