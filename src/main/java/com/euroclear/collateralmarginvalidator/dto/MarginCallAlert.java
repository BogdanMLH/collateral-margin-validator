package com.euroclear.collateralmarginvalidator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarginCallAlert {
    private String bankId;
    private BigDecimal valueDifference;
    private LocalDateTime timestamp;
}
