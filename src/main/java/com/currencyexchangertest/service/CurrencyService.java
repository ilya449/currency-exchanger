package com.currencyexchangertest.service;

import com.currencyexchangertest.entity.Currency;
import com.currencyexchangertest.repository.CurrencyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final NbuApiService nbuApiService;
    private final CurrencyRepository currencyRepository;

    public CurrencyService(NbuApiService nbuApiService, CurrencyRepository currencyRepository) {
        this.nbuApiService = nbuApiService;
        this.currencyRepository = currencyRepository;
        List<Currency> currentRate = nbuApiService.getCurrentRate();
        copyCurrenciesRates(currentRate);
        this.currencyRepository.saveAll(currentRate);
    }

    public List<Currency> getExchangeRates() {
        return currencyRepository.findAll();
    }

    public Optional<Currency> getByName(String name) {
        return currencyRepository.getByName(name);
    }

    public boolean setCurrency(Currency currency) {
        Optional<Currency> currencyFromRepository = getByName(currency.getName());
        currencyFromRepository.ifPresent(value -> currency.setId(value.getId()));
        currencyRepository.save(currency);
        return true;
    }

    public void updateCurrency() {
        currencyRepository.deleteAll();
        List<Currency> currentRate = nbuApiService.getCurrentRate();
        copyCurrenciesRates(currentRate);
        currencyRepository.saveAll(currentRate);
    }

    private void copyCurrenciesRates(List<Currency> currencies) {
        for (Currency c : currencies) {
            c.setValueBuy(c.getValueSell());
        }
    }
}
