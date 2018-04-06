package de.ite.dus.quotes.model;

public interface Product {
    Quote getBaseQuote();
    Quote getPeakQuote();
}
