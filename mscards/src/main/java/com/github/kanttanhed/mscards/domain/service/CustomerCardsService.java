package com.github.kanttanhed.mscards.domain.service;

import com.github.kanttanhed.mscards.domain.entity.CustomerCards;
import com.github.kanttanhed.mscards.domain.repository.CustomerCardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCardsService {
    private final CustomerCardsRepository customerCardsRepository;

    public List<CustomerCards> listCardsByCPF(String cpf){
        return customerCardsRepository.findByCpf(cpf);
    }
}
