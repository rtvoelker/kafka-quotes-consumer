package de.ite.dus.quotes.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceEurMWh {
    private BigDecimal value;

    public PriceEurMWh(double value) {
        this.value = BigDecimal.valueOf(value);
    }
}
