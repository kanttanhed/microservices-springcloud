package com.github.kanttanhed.mscards.domain.repository;

import com.github.kanttanhed.mscards.domain.entity.SaveCards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface SaveCardsRepository extends JpaRepository <SaveCards, Long> {
    List<SaveCards> findByIncomeLessThanEqual(BigDecimal income);
}
