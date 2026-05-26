package com.euroclear.collateralmarginvalidator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioUpdate {
    private String bankId;
    private String assetId;
    private BigDecimal currentValue;
    private BigDecimal marginCallMinValue;
}
