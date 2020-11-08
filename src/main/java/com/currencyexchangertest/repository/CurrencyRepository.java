package com.currencyexchangertest.repository;

import com.currencyexchangertest.entity.Currency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> getByName(String name);
}
