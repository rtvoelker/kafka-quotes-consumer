package de.ite.dus.quotes;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ite.dus.quotes.model.MonthProduct;
import de.ite.dus.quotes.model.Product;
import de.ite.dus.quotes.model.QuotesSet;
import org.junit.Test;

import java.io.IOException;
import java.time.Year;

import static de.ite.dus.quotes.QuotesSetTestJsonExample.SINGLE_MONTH_PRODUCT;
import static de.ite.dus.quotes.model.Country.DE;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;


public class QuotesSetDeserializerTest {

    @Test
    public void deserializeQuotesSet_singleMonthProduct() throws IOException {
        QuotesSet quotesSet = new ObjectMapper().readValue(SINGLE_MONTH_PRODUCT, QuotesSet.class);
        assertThat(quotesSet).isNotNull();
        assertThat(quotesSet.getTimestamp()).isNotNull();
        assertThat(quotesSet.getCountry()).isEqualTo(DE);
        assertThat(quotesSet.getProducts()).isNotEmpty();
        assertMonthProduct(quotesSet.getProducts().get(0));
    }

    private void assertMonthProduct(Product product) {
        assertThat(product).isNotNull();
        assertThat(product).isExactlyInstanceOf(MonthProduct.class);
        MonthProduct monthProduct = (MonthProduct) product;
        assertThat(monthProduct.getMonth()).isEqualTo(MAY);
        assertThat(monthProduct.getYear()).isEqualTo(Year.of(2021));
        assertThat(monthProduct.getBaseQuote().getBidPrice().getValue().doubleValue()).isEqualTo(0.5001);
        assertThat(monthProduct.getBaseQuote().getAskPrice().getValue().doubleValue()).isEqualTo(0.5201);
        assertThat(monthProduct.getPeakQuote().getBidPrice().getValue().doubleValue()).isEqualTo(2.0001);
        assertThat(monthProduct.getPeakQuote().getAskPrice().getValue().doubleValue()).isEqualTo(2.0201);
    }

}