package de.ite.dus.quotes.model;

import lombok.Getter;

import java.time.Year;

@Getter
public class QuarterProduct implements Product {
    public enum Quarter {
        Q1, Q2, Q3, Q4
    }

    private Year year;
    private Quarter quarter;

    private Quote baseQuote;
    private Quote peakQuote;
}
