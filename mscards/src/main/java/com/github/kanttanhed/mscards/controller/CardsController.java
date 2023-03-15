package com.github.kanttanhed.mscards.controller;

import com.github.kanttanhed.mscards.domain.dtos.CustomerCardsDTO;
import com.github.kanttanhed.mscards.domain.dtos.CardsDTO;
import com.github.kanttanhed.mscards.domain.entity.Cards;
import com.github.kanttanhed.mscards.domain.entity.CustomerCards;
import com.github.kanttanhed.mscards.domain.service.CardsService;
import com.github.kanttanhed.mscards.domain.service.CustomerCardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardsController {

    private final CardsService cardsService;
    private final CustomerCardsService customerCardsService;

    @GetMapping
    public String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity saveCard(@RequestBody CardsDTO cardsDTO){
        var cards = cardsDTO.toModel();
        cardsService.save(cards);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("name={name}")
                .buildAndExpand(cards.getName())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Cards>> getCardsIncomeUntil(@RequestParam("income") Long income ){
        var list = cardsService.getCardsIncomeLessEquals(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CustomerCardsDTO>> getCardsByCustomer(
            @RequestParam("cpf") String cpf){

        List<CustomerCards> list = customerCardsService.listCardsByCPF(cpf);
        List<CustomerCardsDTO> resultList = list.stream()
                .map(CustomerCardsDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);

    }

}
