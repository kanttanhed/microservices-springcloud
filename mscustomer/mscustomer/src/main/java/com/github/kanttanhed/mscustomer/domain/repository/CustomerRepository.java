package com.github.kanttanhed.mscustomer.domain.repository;

import com.github.kanttanhed.mscustomer.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCpf(String cpf);
}
