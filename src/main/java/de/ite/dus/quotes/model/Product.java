package de.ite.dus.quotes.model;

import java.time.Year;

public interface Product {
    Year getYear();

    Quote getBaseQuote();
    Quote getPeakQuote();
}
