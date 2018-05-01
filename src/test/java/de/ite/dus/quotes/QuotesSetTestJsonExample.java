package de.ite.dus.quotes;

public class QuotesSetTestJsonExample {
    private static final String MONTH_PRODUCT = "{" +
            "\"year\":\"2021\"," +
            "\"month\":\"MAY\"," +
            "\"base\":{\"bid\":\"0.5001\",\"ask\":\"0.5201\"}," +
            "\"peak\":{\"bid\":\"2.0001\",\"ask\":\"2.0201\"}" +
            "}";

    private static final String QUARTER_PRODUCT = "{" +
            "\"year\":\"2021\"," +
            "\"quarter\":\"Q2\"," +
            "\"base\":{\"bid\":\"0.4999\",\"ask\":\"0.5099\"}," +
            "\"peak\":{\"bid\":\"1.9998\",\"ask\":\"2.0999\"}" +
            "}";

    private static final String YEAR_PRODUCT = "{" +
            "\"year\":\"2021\"," +
            "\"base\":{\"bid\":\"0.4899\",\"ask\":\"0.4998\"}," +
            "\"peak\":{\"bid\":\"1.9898\",\"ask\":\"2.0899\"}" +
            "}";

    static final String QUOTESET_MONTHPRODUCT = "{\"timeStamp\":\"2018-05-01T14:08:17.135\"," +
            "\"country\":\"DE\"," +
            "\"products\":[" + MONTH_PRODUCT + "]}";

    static final String QUOTESET_QUARTERPRODUCT = "{\"timeStamp\":\"2018-05-01T14:08:17.135\"," +
            "\"country\":\"DE\"," +
            "\"products\":[" + QUARTER_PRODUCT + "]}";

    static final String QUOTESET_YEARPRODUCT = "{\"timeStamp\":\"2018-05-01T14:08:17.135\"," +
            "\"country\":\"DE\"," +
            "\"products\":[" + YEAR_PRODUCT + "]}";

    static final String QUOTESET_MONTH_QUARTER_YEAR_PRODUCTS= "{\"timeStamp\":\"2018-05-01T14:08:17.135\"," +
            "\"country\":\"DE\"," +
            "\"products\":[" +
            MONTH_PRODUCT + "," +
            QUARTER_PRODUCT + "," +
            YEAR_PRODUCT + "]}";

}
