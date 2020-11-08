package com.currencyexchangertest.controller;

import com.currencyexchangertest.entity.Currency;
import com.currencyexchangertest.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AdminController {
    private final CurrencyService currencyService;

    public AdminController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/admin")
    public String index(Model model) {
        model.addAttribute("currencies", currencyService.getExchangeRates());
        return "admin";
    }

    @GetMapping(value = "/admin/update")
    public String update(Model model) {
        currencyService.updateCurrency();
        model.addAttribute("currencies", currencyService.getExchangeRates());
        return "admin";
    }

    @PostMapping("/admin/set")
    public String set(@ModelAttribute("currency") Currency currency, Model model) {
        if (currencyService.setCurrency(currency)) {
            model.addAttribute("message", "New currency rate was successfully set!");
        } else {
            model.addAttribute("invalidMessage",
                    "Can't set new currency rate, please try to reset it!");
        }
        model.addAttribute("currencies", currencyService.getExchangeRates());
        return "admin";
    }
}
