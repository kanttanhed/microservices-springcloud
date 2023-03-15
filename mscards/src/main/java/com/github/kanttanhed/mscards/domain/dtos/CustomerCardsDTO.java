package com.github.kanttanhed.mscards.domain.dtos;

import com.github.kanttanhed.mscards.domain.entity.CustomerCards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCardsDTO {
    private String name;
    private String type;
    private BigDecimal basicLimit;

    public static CustomerCardsDTO fromModel (CustomerCards cards){
        return new CustomerCardsDTO(
                cards.getCards().getName(),
                cards.getCards().getType().toString(),
                cards.getBasicLimit()
        );

    }
}
