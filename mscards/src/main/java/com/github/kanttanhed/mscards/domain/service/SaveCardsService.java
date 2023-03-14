package com.github.kanttanhed.mscards.domain.service;

import com.github.kanttanhed.mscards.domain.entity.SaveCards;
import com.github.kanttanhed.mscards.domain.repository.SaveCardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveCardsService {

    private final SaveCardsRepository saveCardsRepository;

    @Transactional
    public SaveCards save(SaveCards saveCards){
        return saveCardsRepository.save(saveCards);
    }

    public List<SaveCards> getCardsIncomeLessEquals(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return saveCardsRepository.findByIncomeLessThanEqual(incomeBigDecimal);
    }
}
