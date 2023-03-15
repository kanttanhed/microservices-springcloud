package com.github.kanttanhed.mscards.domain.service;

import com.github.kanttanhed.mscards.domain.entity.Cards;
import com.github.kanttanhed.mscards.domain.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardsService {

    private final CardsRepository cardsRepository;

    @Transactional
    public Cards save(Cards cards){
        return cardsRepository.save(cards);
    }

    public List<Cards> getCardsIncomeLessEquals(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return cardsRepository.findByIncomeLessThanEqual(incomeBigDecimal);
    }
}
