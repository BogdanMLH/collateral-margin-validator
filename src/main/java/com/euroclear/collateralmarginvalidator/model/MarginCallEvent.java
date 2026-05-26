package com.euroclear.collateralmarginvalidator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "margin_call_events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarginCallEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankId;

    private BigDecimal valueDifference;

    private LocalDateTime timestamp;
}
