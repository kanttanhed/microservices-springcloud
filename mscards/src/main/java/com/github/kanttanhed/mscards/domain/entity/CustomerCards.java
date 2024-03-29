package com.github.kanttanhed.mscards.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class CustomerCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_cards")
    private Cards cards;
    @Column
    private BigDecimal basicLimit;

}
