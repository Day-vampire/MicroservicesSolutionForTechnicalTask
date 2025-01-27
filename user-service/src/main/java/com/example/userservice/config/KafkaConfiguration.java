package com.example.userservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Bean
    public NewTopic userDeleteTopic() {
        return new NewTopic("user_delete_topic", 1, (short) 1);
    }

    @Bean
    public NewTopic userCreateTopic() {
        return new NewTopic("user_create_topic", 1, (short) 1);
    }
}
