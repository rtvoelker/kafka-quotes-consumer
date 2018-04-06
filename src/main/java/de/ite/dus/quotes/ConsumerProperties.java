package de.ite.dus.quotes;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Component
public class ConsumerProperties {

    public Map<String, Object> get() {
        Map<String, Object> props = new HashMap<>();
        props.put(GROUP_ID_CONFIG, "quotes-data");
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

}
