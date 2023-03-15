package com.github.kanttanhed.mscards.domain.repository;

import com.github.kanttanhed.mscards.domain.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardsRepository extends JpaRepository <Cards, Long> {
    List<Cards> findByIncomeLessThanEqual(BigDecimal income);
}
