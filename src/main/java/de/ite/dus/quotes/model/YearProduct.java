package de.ite.dus.quotes.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
public class YearProduct implements Product {

    private Year year;

    private Quote baseQuote;
    private Quote peakQuote;

}
