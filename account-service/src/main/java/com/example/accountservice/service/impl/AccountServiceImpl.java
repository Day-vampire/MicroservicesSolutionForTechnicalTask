package com.example.accountservice.service.impl;


import com.example.accountservice.client.UserServiceClient;
import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.dto.AccountFilterDto;
import com.example.accountservice.entity.Account;
import com.example.accountservice.entity.AccountStatus;
import com.example.accountservice.exception.NotFoundException;
import com.example.accountservice.mapper.AccountMapper;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.service.AccountService;
import com.example.accountservice.specification.AccountSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Функционал
//1. Добавление счета
//2. Закрытие счета (указание для счета что его нельзя использовать)
//3. Удаление счета
//4. Получение всех счетов пользователя по id пользователя
//5. Получение открытых счетов пользователя по id пользователя
//6. Получение закрытых счетов пользователя по id пользователя

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserServiceClient userServiceClient;
    private final AccountSpecificationBuilder accountSpecificationBuilder;

    @Transactional
    public AccountDto createAccount(AccountDto accountDto) {

        userServiceClient.findUserById(accountDto.getUserId());
        Account account = Account
                .builder()
                .status(accountDto.getStatus())
                .userId(accountDto.getUserId())
                .build();
        return accountMapper.toAccountDto(accountRepository.save(account));
    }

    @Transactional
    public void createAccount(Long userId) {
        userServiceClient.findUserById(userId);
        Account account = Account
                .builder()
                .status(AccountStatus.OPENED)
                .userId(userId)
                .build();
        accountRepository.save(account);
    }


    @Override
    @Transactional
    public AccountDto closeAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Account with id %d not found".formatted(id)));
        if (account.getStatus().equals(AccountStatus.OPENED)) {
            account.setStatus(AccountStatus.CLOSED);
        }
        return accountMapper.toAccountDto(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        accountRepository.deleteAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Page<AccountDto> findAll(AccountFilterDto accountFilterDto, Pageable pageable) {
        userServiceClient.findUserById(accountFilterDto.getUserId());
        return accountRepository
                .findAll(accountSpecificationBuilder.build(accountFilterDto), pageable)
                .map(accountMapper::toAccountDto);
    }
}
