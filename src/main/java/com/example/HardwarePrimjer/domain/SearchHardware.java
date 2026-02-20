package com.example.HardwarePrimjer.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class SearchHardware extends Hardware{
    private BigDecimal lowerPrice;
    private BigDecimal upperPrice;
    public SearchHardware(String name,
                         String code,
                         BigDecimal lowerPrice,
                         BigDecimal upperPrice,
                         Category categoryName)
    {
        super.setName(name);
        super.setCode(code);
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
        super.setCategory(categoryName);
    }
}
