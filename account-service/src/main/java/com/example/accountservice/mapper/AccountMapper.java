package com.example.accountservice.mapper;


import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "user.id", target = "userId")
    AccountDto toAccountDto(Account account);
    Account toAccount(AccountDto accountDto);
}
