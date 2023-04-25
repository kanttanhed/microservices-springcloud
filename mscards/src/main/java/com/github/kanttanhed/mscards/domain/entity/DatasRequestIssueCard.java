package com.github.kanttanhed.mscards.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DatasRequestIssueCard {
    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal basicLimit;
}