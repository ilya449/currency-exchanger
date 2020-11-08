package com.currencyexchangertest.controller;

import com.currencyexchangertest.entity.CurrencyRequestDto;
import com.currencyexchangertest.service.ChangeService;
import com.currencyexchangertest.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {
    private final CurrencyService currencyService;
    private final ChangeService changeService;

    public UserController(CurrencyService currencyService, ChangeService changeService) {
        this.currencyService = currencyService;
        this.changeService = changeService;
    }

    @GetMapping("/user")
    public String index(Model model) {
        model.addAttribute("currencies", currencyService.getExchangeRates());
        return "user";
    }

    @PostMapping("/user/currency")
    public String getResultAmount(@ModelAttribute("currencyDto") CurrencyRequestDto currencyDto,
                                  Model model) {
        String enteredCurrency = currencyDto.getCurrencyType();
        model = changeService.operate(currencyDto, model, enteredCurrency);
        model.addAttribute("currencies", currencyService.getExchangeRates());
        return "user";
    }
}
