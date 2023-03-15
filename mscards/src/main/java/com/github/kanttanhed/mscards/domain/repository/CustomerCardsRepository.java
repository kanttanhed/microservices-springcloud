package com.github.kanttanhed.mscards.domain.repository;

import com.github.kanttanhed.mscards.domain.entity.CustomerCards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCardsRepository extends JpaRepository <CustomerCards, Long> {
    List<CustomerCards> findByCpf(String cpf);
}
