package de.ite.dus.quotes.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import de.ite.dus.quotes.model.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@JsonComponent
public class QuotesSetDeserializer extends JsonDeserializer<QuotesSet> {

    @Override
    public QuotesSet deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        TreeNode rootNode = jsonParser.getCodec().readTree(jsonParser);

        QuotesSet quotesSet = new QuotesSet();
        quotesSet.setTimestamp(parseTimestamp(rootNode));
        quotesSet.setCountry(parseCountry(rootNode));
        quotesSet.setProducts(parseProducts(rootNode));
        return quotesSet;
    }

    private LocalDateTime parseTimestamp(TreeNode rootNode) {
        TextNode textNode = getTextNode(rootNode, "timeStamp");
        return textNode.isNull() ? null : LocalDateTime.parse(textNode.asText(), ISO_LOCAL_DATE_TIME);
    }

    private Country parseCountry(TreeNode rootNode) {
        TextNode textNode = getTextNode(rootNode, "country");
        return Country.valueOf(textNode.asText());
    }

    private List<Product> parseProducts(TreeNode rootNode) {
        ArrayNode arrayNode = getArrayNode(rootNode, "products");
        List<Product> products = new ArrayList<>();
        for(JsonNode productNode : arrayNode) {
            products.add(parseProduct((ObjectNode) productNode));
        }
        return products;
    }

    private Product parseProduct(ObjectNode productNode) {
        if(productNode.has("month")) {
            return parseMonthProduct(productNode);
        } else if(productNode.has("quarter")) {
            return parseQuarterProduct(productNode);
        } else {
            return parseYearProduct(productNode);
        }
    }

    private MonthProduct parseMonthProduct(ObjectNode productNode) {
        MonthProduct product = new MonthProduct();
        product.setMonth(parseMonth(productNode));
        product.setYear(parseYear(productNode));
        product.setBaseQuote(parseQuote(productNode, "base"));
        product.setPeakQuote(parseQuote(productNode, "peak"));
        return product;
    }

    private Month parseMonth(ObjectNode productNode) {
        TextNode monthNode = getTextNode(productNode, "month");
        return Month.valueOf(monthNode.asText());
    }

    private QuarterProduct parseQuarterProduct(ObjectNode productNode) {
        QuarterProduct product = new QuarterProduct();
        product.setQuarter(parseQuarter(productNode));
        product.setYear(parseYear(productNode));
        product.setBaseQuote(parseQuote(productNode, "base"));
        product.setPeakQuote(parseQuote(productNode, "peak"));
        return product;
    }

    private QuarterProduct.Quarter parseQuarter(ObjectNode productNode) {
        TextNode quarterNode = getTextNode(productNode, "quarter");
        return QuarterProduct.Quarter.valueOf(quarterNode.asText());
    }

    private YearProduct parseYearProduct(ObjectNode productNode) {
        YearProduct product = new YearProduct();
        product.setYear(parseYear(productNode));
        product.setBaseQuote(parseQuote(productNode, "base"));
        product.setPeakQuote(parseQuote(productNode, "peak"));
        return product;
    }

    private Year parseYear(ObjectNode productNode) {
        TextNode textNode = getTextNode(productNode, "year");
        return Year.parse(textNode.asText());
    }

    private Quote parseQuote(ObjectNode productNode, String quoteType) {
        ObjectNode quoteNode = getObjectNode(productNode, quoteType);
        Quote quote = new Quote();
        quote.setBidPrice(parsePrice(quoteNode, "bid"));
        quote.setAskPrice(parsePrice(quoteNode, "ask"));
        return quote;
    }

    private PriceEurMWh parsePrice(ObjectNode quoteNode, String priceType) {
        TextNode priceNode = getTextNode(quoteNode, priceType);
        return new PriceEurMWh(Double.valueOf(priceNode.asText()));
    }

    private TextNode getTextNode(TreeNode rootNode, String fieldName) {
        return (TextNode) rootNode.get(fieldName);
    }

    private ArrayNode getArrayNode(TreeNode rootNode, String fieldName) {
        return (ArrayNode) rootNode.get(fieldName);
    }

    private ObjectNode getObjectNode(TreeNode rootNode, String fieldName) {
        return (ObjectNode) rootNode.get(fieldName);
    }
}
