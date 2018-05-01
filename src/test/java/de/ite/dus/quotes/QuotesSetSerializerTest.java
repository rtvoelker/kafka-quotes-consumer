package de.ite.dus.quotes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ite.dus.quotes.model.MonthProduct;
import de.ite.dus.quotes.model.PriceEurMWh;
import de.ite.dus.quotes.model.Quote;
import de.ite.dus.quotes.model.QuotesSet;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Collections;

import static de.ite.dus.quotes.QuotesSetTestJsonExample.SINGLE_MONTH_PRODUCT;
import static de.ite.dus.quotes.model.Country.DE;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

public class QuotesSetSerializerTest {

    @Test
    public void serializeQuotesSet_singleMonthProduct() throws JsonProcessingException {
        String strJson = new ObjectMapper().writeValueAsString(createQuotesSet());
        assertThat(strJson).isEqualTo(SINGLE_MONTH_PRODUCT);
    }

    private QuotesSet createQuotesSet() {
        QuotesSet quotesSet = new QuotesSet();
        quotesSet.setTimestamp(LocalDateTime.of(2018, 05, 01, 14, 8, 17, 135000000));
        quotesSet.setCountry(DE);
        quotesSet.setProducts(Collections.singletonList(createMonthProduct()));
        return quotesSet;
    }

    private MonthProduct createMonthProduct() {
        MonthProduct product = new MonthProduct();
        product.setYear(Year.of(2021));
        product.setMonth(MAY);
        product.setBaseQuote(createQuote(0.5001, 0.5201));
        product.setPeakQuote(createQuote(2.0001, 2.0201));
        return product;
    }

    private Quote createQuote(double bidPrice, double askPrice) {
        Quote quote = new Quote();
        quote.setBidPrice(new PriceEurMWh(bidPrice));
        quote.setAskPrice(new PriceEurMWh(askPrice));
        return quote;
    }


}