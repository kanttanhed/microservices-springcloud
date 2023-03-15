package com.github.kanttanhed.mscards.domain.dtos;

import com.github.kanttanhed.mscards.domain.entity.Cards;
import com.github.kanttanhed.mscards.domain.entity.TypeCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardsDTO {
    private String name;
    private TypeCard type;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Cards toModel(){
        return new Cards(name, type, income, basicLimit);
    }
}
