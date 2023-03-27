package com.github.kanttanhed.mscreditevaluater.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EvaluationCustomerReturn {
    private List<ApprovedCard> approvedCards;
}
