package de.ite.dus.quotes;

import de.ite.dus.quotes.model.QuotesSet;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@SpringBootApplication
public class QuotesApplication {

	private ConsumerProperties consumerProperties;

	public QuotesApplication(ConsumerProperties consumerProperties) {
		this.consumerProperties = consumerProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}

	@Bean
	public ConsumerFactory<String, QuotesSet> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(
				consumerProperties.get(),
				new StringDeserializer(),
				new JsonDeserializer<>(QuotesSet.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, QuotesSet> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, QuotesSet> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
