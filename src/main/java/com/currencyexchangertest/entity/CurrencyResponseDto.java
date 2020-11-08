package com.currencyexchangertest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyResponseDto {
    @JsonProperty("cc")
    private String name;
    @JsonProperty("rate")
    private Double valueSell;
}
