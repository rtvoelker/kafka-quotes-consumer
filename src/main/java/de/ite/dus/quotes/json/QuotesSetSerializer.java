package de.ite.dus.quotes.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.ite.dus.quotes.model.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@JsonComponent
public class QuotesSetSerializer extends JsonSerializer<QuotesSet> {

    @Override
    public void serialize(QuotesSet quotesSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("timeStamp", quotesSet.getTimestamp().format(ISO_LOCAL_DATE_TIME));
        jsonGenerator.writeStringField("country", quotesSet.getCountry().name());
        serializeProducts(quotesSet.getProducts(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }

    private void serializeProducts(List<Product> products, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeArrayFieldStart("products");
        for (Product p : products) {
            serializeProduct(p, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }

    private void serializeProduct(Product product, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("year", product.getYear().toString());
        serializeProductTypeDependentFields(product, jsonGenerator);
        serializeQuote("base", product.getBaseQuote(), jsonGenerator);
        serializeQuote("peak", product.getPeakQuote(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }

    private void serializeProductTypeDependentFields(Product product, JsonGenerator jsonGenerator) throws IOException {
        if(product instanceof MonthProduct) {
            jsonGenerator.writeStringField("month", ((MonthProduct) product).getMonth().toString());
        } else if(product instanceof QuarterProduct) {
            jsonGenerator.writeStringField("quarter", ((QuarterProduct) product).getQuarter().toString());
        }
    }

    private void serializeQuote(String quoteType, Quote quote, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeObjectFieldStart(quoteType);
        jsonGenerator.writeStringField("bid", quote.getBidPrice().getValue().toString());
        jsonGenerator.writeStringField("ask", quote.getAskPrice().getValue().toString());
        jsonGenerator.writeEndObject();
    }
}
