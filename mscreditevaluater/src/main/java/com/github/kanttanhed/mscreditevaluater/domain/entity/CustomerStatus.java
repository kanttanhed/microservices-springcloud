package com.github.kanttanhed.mscreditevaluater.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class CustomerStatus {
    private CustomerData customerData;
    private List<CustomerCard> cards;
}
