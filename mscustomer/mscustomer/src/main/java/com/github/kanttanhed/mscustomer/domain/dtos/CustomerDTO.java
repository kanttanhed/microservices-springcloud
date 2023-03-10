package com.github.kanttanhed.mscustomer.domain.dtos;

import com.github.kanttanhed.mscustomer.domain.entity.Customer;
import lombok.Data;

@Data
public class CustomerDTO {
    private String cpf;
    private String name;
    private Integer age;

    public Customer toModel(){
        return new Customer(cpf, name, age);
    }
}
