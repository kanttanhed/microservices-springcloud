package com.github.kanttanhed.mscreditevaluater.domain.feignClient;

import com.github.kanttanhed.mscreditevaluater.domain.entity.Card;
import com.github.kanttanhed.mscreditevaluater.domain.entity.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CustomerCard>> getCardsByCustomer(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<Card>> getCardsIncomeUntil(@RequestParam("income") Long income );
}
