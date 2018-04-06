package de.ite.dus.quotes.product;

import de.ite.dus.quotes.Quote;

import java.time.Month;
import java.time.Year;

public class MonthProduct implements Product {

    private Year year;
    private Month month;

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
