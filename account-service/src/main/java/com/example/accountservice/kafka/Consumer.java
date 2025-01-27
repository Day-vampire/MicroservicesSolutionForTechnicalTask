package com.example.accountservice.kafka;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final AccountService accountService;

    @KafkaListener(topics = "user_create_topic", groupId = "account_consumers")
    public void listenCreateUserId(ConsumerRecord<String, Long> record) {
        System.out.println(" \n ##################################################  \n" );
        System.out.println(record.value());
        System.out.println(record.key());
        accountService.createAccount(record.value());
        System.out.println(" \n ##################################################  \n" );
    }

    @KafkaListener(topics = "user_delete_topic", groupId = "account_consumers")
    public void listenDeleteUserId(ConsumerRecord<String, Long> record) {
        System.out.println(" \n ##################################################  \n" );
        System.out.println(record.value());
        System.out.println(record.key());
        accountService.deleteAllByUserId(record.value());
        System.out.println(" \n ##################################################  \n" );
    }
}
