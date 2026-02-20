package com.example.HardwarePrimjer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HardwareDTO {
    private String hardwareName;
    private String hardwareCode;
    private BigDecimal hardwarePrice;
    private Integer quantity;
    private String categoryName;
}
