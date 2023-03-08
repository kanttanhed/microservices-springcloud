package com.github.kanttanhed.mscustomer.domain.service;

import com.github.kanttanhed.mscustomer.domain.entity.Customer;
import com.github.kanttanhed.mscustomer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Optional<Customer> getByCPF(String cpf){
        return customerRepository.finfByCpf(cpf);
    }
}
