package neb;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Value("${kafka.bootstrap-address}")
    private String bootstrapAddress;

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ReviewProcessedMessage>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReviewProcessedMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        return factory;
    }

    @Bean
    public Map<String, Object> props() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        configProps.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );
        configProps.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class
        );
        configProps.put(
                JsonDeserializer.TRUSTED_PACKAGES,
                "neb"
        );

        return configProps;
    }

    @Bean
    public ProducerFactory<String, CheckReviewMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<>(props());
    }

    @Bean
    public ConsumerFactory<String, ReviewProcessedMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(props());
    }

    @Bean
    public KafkaTemplate<String, CheckReviewMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}