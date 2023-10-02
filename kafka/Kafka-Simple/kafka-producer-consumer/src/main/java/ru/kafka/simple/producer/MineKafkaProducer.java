package ru.kafka.simple.producer;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.kafka.simple.model.Person;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MineKafkaProducer {

    @NonFinal
    @Value("${topics}")
    List<String> topics;

    KafkaTemplate<String, Person> kafkaTemplate;

    public void sendMessage(Person person) {
        List<CompletableFuture<SendResult<String, Person>>> completableFutures = topics.stream()
                .map(topic -> kafkaTemplate.send(topic, person.getId(), person))
                .toList();

        completableFutures.forEach(completableFuture ->
                                           completableFuture.whenCompleteAsync((result, throwable) -> {
                                               if (throwable != null) {
                                                   log.error("Can not send message: {}, error: message: {}",
                                                             result,
                                                             throwable.getMessage(),
                                                             throwable);
                                                   return;
                                               }

                                               log.info("sent message = {} with offset = {}",
                                                        result,
                                                        result.getRecordMetadata().offset());
                                           }));
    }

}
