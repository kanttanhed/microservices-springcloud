package com.github.kanttanhed.mscards.controller;

import com.github.kanttanhed.mscards.domain.dtos.SaveCardDTO;
import com.github.kanttanhed.mscards.domain.entity.SaveCards;
import com.github.kanttanhed.mscards.domain.service.SaveCardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardsController {

    private final SaveCardsService saveCardsService;

    @GetMapping
    public String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity saveCard(@RequestBody SaveCardDTO saveCardDTO){
        var cards = saveCardDTO.toModel();
        saveCardsService.save(cards);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("name={name}")
                .buildAndExpand(cards.getName())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<SaveCards>> getCardsIncomeUntil(@RequestParam("income") Long income ){
        var list = saveCardsService.getCardsIncomeLessEquals(income);
        return ResponseEntity.ok(list);
    }
}
