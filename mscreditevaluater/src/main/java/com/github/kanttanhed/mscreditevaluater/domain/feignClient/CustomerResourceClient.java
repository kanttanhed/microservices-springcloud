package com.github.kanttanhed.mscreditevaluater.domain.feignClient;

import com.github.kanttanhed.mscreditevaluater.domain.entity.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomer", path = "/customer")
public interface CustomerResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<CustomerData> CustomerDatas(@RequestParam("cpf") String cpf);
}
