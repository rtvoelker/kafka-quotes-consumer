package de.ite.dus.quotes.model;

import lombok.Getter;

import java.time.Month;
import java.time.Year;

@Getter
public class MonthProduct implements Product {

    private Year year;
    private Month month;

    private Quote baseQuote;
    private Quote peakQuote;

}
