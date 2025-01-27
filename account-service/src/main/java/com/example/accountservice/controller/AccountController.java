package com.example.accountservice.controller;


import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.dto.AccountFilterDto;
import com.example.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//Функционал
//1. Добавление счета
//2. Закрытие счета (указание для счета что его нельзя использовать)
//3. Удаление счета
//4. Получение всех счетов пользователя по id пользователя
//5. Получение открытых счетов пользователя по id пользователя
//6. Получение закрытых счетов пользователя по id пользователя

@RestController
@AllArgsConstructor
@RequestMapping("accounts")
@PreAuthorize("hasAuthority('USER_ROLE')")
public class AccountController {

    private final AccountService accountService;

    //1. Добавление счета
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new account")
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    //2. Закрытие счета (указание для счета что его нельзя использовать)
    @Operation(summary = "Close account by ID")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto closeAccount(@PathVariable Long id) {
        return accountService.closeAccount(id);
    }

    //3. Удаление счета
    @Operation(summary = "Delete account by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    //4-6 Получение всех/открытых/закрытых счетов пользователя по id пользователя
    @Operation(summary = "Get all/opened/closed user accounts by ID", description = "Use user id for find all user accounts. Use user id with status for find opened or closed user accounts. ")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<AccountDto> findAll(@ParameterObject AccountFilterDto accountFilterDto,
                                         @ParameterObject
                                         @PageableDefault(size = 10, page = 0, sort = "id,asc") Pageable pageable){
        return accountService.findAll(accountFilterDto, pageable);
    }

}