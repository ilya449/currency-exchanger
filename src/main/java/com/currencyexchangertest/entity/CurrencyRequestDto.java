package com.currencyexchangertest.entity;

import lombok.Data;

@Data
public class CurrencyRequestDto {
    private String name;
    private Double amount;
    private String operation;
    private String currencyType;
}
