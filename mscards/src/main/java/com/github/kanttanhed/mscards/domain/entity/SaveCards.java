package com.github.kanttanhed.mscards.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class SaveCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Enumerated(EnumType.STRING)
    private TypeCard type;
    @Column
    private BigDecimal income;
    @Column
    private BigDecimal basicLimit;

    public SaveCards(String name, TypeCard type, BigDecimal income, BigDecimal basicLimit) {
        this.name = name;
        this.type = type;
        this.income = income;
        this.basicLimit = basicLimit;
    }
}
