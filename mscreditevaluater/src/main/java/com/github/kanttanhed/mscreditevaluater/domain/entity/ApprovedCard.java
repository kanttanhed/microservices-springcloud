package com.github.kanttanhed.mscreditevaluater.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCard {
    private String card;
    private String type;
    private BigDecimal basicLimit;
}
