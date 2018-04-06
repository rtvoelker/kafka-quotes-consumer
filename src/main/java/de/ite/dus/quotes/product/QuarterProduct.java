package de.ite.dus.quotes.product;

import de.ite.dus.quotes.Quote;

import java.time.Month;
import java.time.Year;

public class QuarterProduct implements Product {

    private Year year;
    private int quarter;

    private Quote baseQuote;
    private Quote peakQuote;

    @Override
    public Quote getBaseQuote() {
        return baseQuote;
    }

    @Override
    public Quote getPeakQuote() {
        return peakQuote;
    }
}
