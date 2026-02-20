package com.example.HardwarePrimjer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hardware {
    private Integer id;
    private String name;
    private String code;
    private BigDecimal price;
    private Integer quantity;
    private Category category;
}

