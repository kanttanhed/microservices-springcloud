package com.github.kanttanhed.mscards.domain.dtos;

import com.github.kanttanhed.mscards.domain.entity.SaveCards;
import com.github.kanttanhed.mscards.domain.entity.TypeCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaveCardDTO {
    private String name;
    private TypeCard type;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public SaveCards toModel(){
        return new SaveCards(name, type, income, basicLimit);
    }
}
