package de.ite.dus.quotes.product;

import de.ite.dus.quotes.PriceEurMWh;
import de.ite.dus.quotes.Quote;

public interface Product {
    Quote getBaseQuote();
    Quote getPeakQuote();
}
