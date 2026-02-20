package com.example.HardwarePrimjer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
public class SearchHardwareDTO extends HardwareDTO {
    private BigDecimal lowerPrice;
    private BigDecimal upperPrice;

    public SearchHardwareDTO(String hardwareName,
                             String hardwareCode,
                             BigDecimal lowerPrice,
                             BigDecimal upperPrice,
                             String categoryName) {
        super.setHardwareName(hardwareName);
        super.setHardwareCode(hardwareCode);
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
        super.setCategoryName(categoryName);
    }

}
