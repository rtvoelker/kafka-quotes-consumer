package de.ite.dus.quotes.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quote {
    private PriceEurMWh bidPrice;
    private PriceEurMWh askPrice;
}
