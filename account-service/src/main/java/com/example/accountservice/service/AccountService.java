package com.example.accountservice.service;


import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.dto.AccountFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//Функционал
//1. Добавление счета
//2. Закрытие счета (указание для счета что его нельзя использовать)
//3. Удаление счета
//4. Получение всех счетов пользователя по id пользователя
//5. Получение открытых счетов пользователя по id пользователя
//6. Получение закрытых счетов пользователя по id пользователя

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto closeAccount(Long id);
    void deleteAccount(Long id);
    void deleteAllByUserId(Long userId);
    Page<AccountDto> findAll(AccountFilterDto accountFilterDto, Pageable pageable);
    void createAccount(Long userId);
}
