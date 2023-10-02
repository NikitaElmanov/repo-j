package ru.kafka.simple.config;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import ru.kafka.simple.model.Person;

import static lombok.AccessLevel.PRIVATE;


@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class KafkaConfig {

    KafkaProperties kafkaProperties;

//    @Bean
//    public KafkaTemplate<String, Person> kafkaTemplate() {
//        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties(),
//                                                                     StringSerializer::new,
//                                                                     JsonSerializer<Person>::new));
//    }

    @Bean
    public KafkaTemplate<String, Person> kafkaTemplate() {
        return new KafkaTemplate(
                new DefaultKafkaProducerFactory<String, Person>(
                        kafkaProperties.buildProducerProperties()));
    }

//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Person>> kafkaListenerContainerFactory() {
//        var kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, Person>();
//        kafkaListenerContainerFactory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(),
//                                                                                           StringDeserializer::new,
//                                                                                           JsonDeserializer<Person>::new));
//        return kafkaListenerContainerFactory;
//    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Person>> kafkaListenerContainerFactory() {
        var kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, Person>();
        kafkaListenerContainerFactory.setConsumerFactory(
                new DefaultKafkaConsumerFactory<>(
                        kafkaProperties.buildConsumerProperties()));
        return kafkaListenerContainerFactory;
    }

}
