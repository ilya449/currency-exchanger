package com.currencyexchangertest.service;

import com.currencyexchangertest.entity.Currency;
import com.currencyexchangertest.entity.CurrencyRequestDto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ChangeService {
    public static final String UAN = " UAN";

    private final CurrencyService currencyService;

    public ChangeService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public Model operate(CurrencyRequestDto currencyDto, Model model, String enteredCurrency) {
        if (enteredCurrency.equalsIgnoreCase("Other")) {
            calculateToUan(currencyDto, model);
        } else {
            calculateToSetCurrency(currencyDto, model);
        }
        return model;
    }

    private void calculateToUan(CurrencyRequestDto currencyDto, Model model) {
        double result;
        String operation = currencyDto.getOperation();
        if ("buy".equalsIgnoreCase(operation)) {
            result = getUanAmountBuyingCurrency(currencyDto.getName(),
                    currencyDto.getAmount());
        } else {
            result = getUanAmountSellingCurrency(currencyDto.getName(),
                    currencyDto.getAmount());
        }

        if (result >= 0) {
            if ("buy".equalsIgnoreCase(operation)) {
                model.addAttribute("message", "You will pay " + result + UAN);
            } else {
                model.addAttribute("message", "You will receive " + result + UAN);
            }
        } else {
            model.addAttribute("invalidMessage", "Can't sell currency, please try one more time");
        }
    }

    private void calculateToSetCurrency(CurrencyRequestDto currencyDto, Model model) {
        double result;
        String operation = currencyDto.getOperation();
        if ("buy".equalsIgnoreCase(operation)) {
            result = getCurrencyAmountBuyingUan(currencyDto.getName(),
                    currencyDto.getAmount());
        } else {
            result = getCurrencyAmountSellingUan(currencyDto.getName(),
                    currencyDto.getAmount());
        }

        if (result >= 0) {
            if ("buy".equalsIgnoreCase(operation)) {
                model.addAttribute("message", "You will pay " + result + " "
                        + currencyDto.getName());
            } else {
                model.addAttribute("message", "You will receive " + result + " "
                        + currencyDto.getName());
            }
        } else {
            model.addAttribute("invalidMessage", "Can't sell currency, please try one more time");
        }
    }

    private double getUanAmountSellingCurrency(String currencyName, double amount) {
        Optional<Currency> foundCurrency = currencyService.getByName(currencyName);
        if (foundCurrency.isEmpty()) {
            return -1.0;
        }
        return getRoundedValue(foundCurrency.get().getValueSell() * amount);
    }

    private double getUanAmountBuyingCurrency(String currencyName, double amount) {
        Optional<Currency> foundCurrency = currencyService.getByName(currencyName);
        if (foundCurrency.isEmpty()) {
            return -1.0;
        }
        return getRoundedValue(foundCurrency.get().getValueBuy() * amount);
    }

    private double getCurrencyAmountBuyingUan(String currencyName, double amount) {
        Optional<Currency> foundCurrency = currencyService.getByName(currencyName);
        if (foundCurrency.isEmpty()) {
            return -1.0;
        }
        return getRoundedValue(amount / foundCurrency.get().getValueBuy());
    }

    private double getCurrencyAmountSellingUan(String currencyName, double amount) {
        Optional<Currency> foundCurrency = currencyService.getByName(currencyName);
        if (foundCurrency.isEmpty()) {
            return -1.0;
        }
        return getRoundedValue(amount / foundCurrency.get().getValueSell());
    }

    private double getRoundedValue(double value) {
        return new BigDecimal(Double.toString(value))
                .setScale(2, RoundingMode.HALF_EVEN)
                .doubleValue();
    }
}
