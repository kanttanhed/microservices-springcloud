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
    public void save(Customer customer){
        try{
            customerRepository.save(customer);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Optional<Customer> getByCPF(String cpf){
        return customerRepository.findByCpf(cpf);
    }
}
