package de.ite.dus.quotes.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.ite.dus.quotes.json.QuotesSetDeserializer;
import de.ite.dus.quotes.json.QuotesSetSerializer;
import de.ite.dus.quotes.model.Country;
import de.ite.dus.quotes.model.Product;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@JsonSerialize(using = QuotesSetSerializer.class)
@JsonDeserialize(using = QuotesSetDeserializer.class)
@Data
public class QuotesSet {
    private LocalDateTime timestamp;
    private Country country;
    private List<Product> products;
}
