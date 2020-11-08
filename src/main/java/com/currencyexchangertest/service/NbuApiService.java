package com.currencyexchangertest.service;

import com.currencyexchangertest.entity.Currency;
import com.currencyexchangertest.entity.CurrencyResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class NbuApiService {
    @Value("${link}")
    private String link;
    @Value("${currencies}")
    private String[] currencies;

    public List<Currency> getCurrentRate() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<CurrencyResponseDto> currenciesFromResponse = new ArrayList<>();

        try {
            currenciesFromResponse = mapper
                    .readValue(new URL(link), new TypeReference<>() {});
        } catch (IOException e) {
            log.info("Exception while getting currencies rate:", e);
        }

        List<String> nameOfCurrencies = Arrays.asList(currencies);

        log.info("Successfully updated currencies rates");

        return currenciesFromResponse.stream()
                .filter(c -> nameOfCurrencies.contains(c.getName()))
                .map(this::mapToCurrency)
                .collect(Collectors.toList());
    }

    private Currency mapToCurrency(CurrencyResponseDto dto) {
        return new Currency(dto.getName(), dto.getValueSell());
    }
}
