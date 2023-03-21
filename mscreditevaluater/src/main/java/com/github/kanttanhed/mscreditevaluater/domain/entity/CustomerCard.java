package com.github.kanttanhed.mscreditevaluater.domain.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CustomerCard {
    private String name;
    private String type;
    private BigDecimal basicLimit;
}
