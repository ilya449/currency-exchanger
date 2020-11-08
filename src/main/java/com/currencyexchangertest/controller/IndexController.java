package com.currencyexchangertest.controller;

import com.currencyexchangertest.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    private final CurrencyService currencyService;

    public IndexController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }
}
