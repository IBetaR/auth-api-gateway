package com.ibetar.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class KafkaListenerService {

    @Bean
    public Consumer<KStream<Object, String>> listenMessages() {
        return input -> {
            input.foreach((k, v) -> {
                var msgParts = v.split(":");
                log.info("Kafka listener service is performing message {} with content: {}.",
                        msgParts[0],
                        msgParts[1]
                );
            });
        };
    }
//    @KafkaListener(topics = "mails")
//    public void listenMailMessages(String message) {
//        var msgParts = message.split(":");
//        log.info("Kafka listener service is performing message {} with content: {}.",
//                msgParts[0],
//                msgParts[1]
//        );
//    }

}