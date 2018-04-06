package de.ite.dus.quotes.model;

import lombok.Getter;

import java.time.Year;

@Getter
public class YearProduct implements Product {

    private Year year;

    private Quote baseQuote;
    private Quote peakQuote;

}
