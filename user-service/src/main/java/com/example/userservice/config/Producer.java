package com.example.userservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, Long> kafkaTemplate;

    public void sendCreateUserId(Long userId) {
        ProducerRecord<String, Long> producerRecord = new ProducerRecord<>("user_create_topic", "userIdKey", userId);
        kafkaTemplate.send(producerRecord);
    }

    public void sendDeleteUserId(Long userId) {
        ProducerRecord<String, Long> producerRecord = new ProducerRecord<>("user_delete_topic", "userIdKey", userId);
        kafkaTemplate.send(producerRecord);
    }

}
