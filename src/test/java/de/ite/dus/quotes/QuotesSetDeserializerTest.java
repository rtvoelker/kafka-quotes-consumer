package de.ite.dus.quotes;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ite.dus.quotes.model.*;
import org.junit.Test;

import java.io.IOException;
import java.time.Year;
import java.util.function.Consumer;

import static de.ite.dus.quotes.QuotesSetTestJsonExample.*;
import static de.ite.dus.quotes.model.Country.DE;
import static de.ite.dus.quotes.model.QuarterProduct.Quarter.Q2;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;


public class QuotesSetDeserializerTest {

    @Test
    public void deserializeQuotesSet_singleMonthProduct() throws IOException {
        executeDeserializeQuotesSet(QUOTESET_MONTHPRODUCT, this::assertSingleMonthProduct);
    }

    private void assertSingleMonthProduct(QuotesSet quotesSet) {
        assertThat(quotesSet.getProducts()).hasSize(1);
        assertMonthProduct(quotesSet.getProducts().get(0));
    }

    @Test
    public void deserializeQuotesSet_singleQuarterProduct() throws IOException {
        executeDeserializeQuotesSet(QUOTESET_QUARTERPRODUCT, this::assertSingleQuarterProduct);
    }

    private void assertSingleQuarterProduct(QuotesSet quotesSet) {
        assertThat(quotesSet.getProducts()).hasSize(1);
        assertQuarterProduct(quotesSet.getProducts().get(0));
    }

    @Test
    public void deserializeQuotesSet_singleYearProduct() throws IOException {
        executeDeserializeQuotesSet(QUOTESET_YEARPRODUCT, this::assertSingleYearProduct);
    }

    private void assertSingleYearProduct(QuotesSet quotesSet) {
        assertThat(quotesSet.getProducts()).hasSize(1);
        assertYearProduct(quotesSet.getProducts().get(0));
    }

    @Test
    public void deserializeQuotesSet_monthQuartAndYearProducts() throws IOException {
        executeDeserializeQuotesSet(QUOTESET_MONTH_QUARTER_YEAR_PRODUCTS,
                this::assertMonthQuarterAndYearProducts);
    }

    private void assertMonthQuarterAndYearProducts(QuotesSet quotesSet) {
        assertThat(quotesSet.getProducts()).hasSize(3);
        assertMonthProduct(quotesSet.getProducts().get(0));
        assertQuarterProduct(quotesSet.getProducts().get(1));
        assertYearProduct(quotesSet.getProducts().get(2));
    }

    private void executeDeserializeQuotesSet(String jsonString, Consumer<QuotesSet> specificAsserter) throws IOException {
        QuotesSet quotesSet = new ObjectMapper().readValue(jsonString, QuotesSet.class);
        assertThat(quotesSet).isNotNull();
        assertThat(quotesSet.getTimestamp()).isNotNull();
        assertThat(quotesSet.getCountry()).isEqualTo(DE);
        specificAsserter.accept(quotesSet);
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

    private void assertQuarterProduct(Product product) {
        assertThat(product).isNotNull();
        assertThat(product).isExactlyInstanceOf(QuarterProduct.class);
        QuarterProduct quarterProduct = (QuarterProduct) product;
        assertThat(quarterProduct.getQuarter()).isEqualTo(Q2);
        assertThat(quarterProduct.getYear()).isEqualTo(Year.of(2021));
        assertThat(quarterProduct.getBaseQuote().getBidPrice().getValue().doubleValue()).isEqualTo(0.4999);
        assertThat(quarterProduct.getBaseQuote().getAskPrice().getValue().doubleValue()).isEqualTo(0.5099);
        assertThat(quarterProduct.getPeakQuote().getBidPrice().getValue().doubleValue()).isEqualTo(1.9998);
        assertThat(quarterProduct.getPeakQuote().getAskPrice().getValue().doubleValue()).isEqualTo(2.0999);
    }

    private void assertYearProduct(Product product) {
        assertThat(product).isNotNull();
        assertThat(product).isExactlyInstanceOf(YearProduct.class);
        assertThat(product.getYear()).isEqualTo(Year.of(2021));
        assertThat(product.getBaseQuote().getBidPrice().getValue().doubleValue()).isEqualTo(0.4899);
        assertThat(product.getBaseQuote().getAskPrice().getValue().doubleValue()).isEqualTo(0.4998);
        assertThat(product.getPeakQuote().getBidPrice().getValue().doubleValue()).isEqualTo(1.9898);
        assertThat(product.getPeakQuote().getAskPrice().getValue().doubleValue()).isEqualTo(2.0899);
    }
}