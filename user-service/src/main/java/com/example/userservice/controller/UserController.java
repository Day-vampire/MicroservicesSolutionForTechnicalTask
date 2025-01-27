package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
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
//1. Добавление пользователя
//2. Удаление пользователя
//3. Изменение данных о пользователе
//4. Поиск по Id
//5. Полнотекстовый поиск по ФИО
//6. Получение списка пользователей

@RestController
@AllArgsConstructor
@RequestMapping("users")
@PreAuthorize("hasAuthority('USER_ROLE')")
public class UserController {

    private final UserService userService;

    //1. Добавление пользователя
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create user")
    public UserDto createUser(@RequestBody UserDto userDto) {
       return userService.createUser(userDto);
    }

    //2. Удаление пользователя
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user by ID")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //3. Изменение данных о пользователе
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update user by ID")
    public UserDto updateUser(@PathVariable Long id,
                              @ParameterObject UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    //4. Поиск по Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find user by ID")
    public UserDto findUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    //5-6 Полнотекстовый поиск по ФИО / Получение списка пользователей (при отсутствии ФИО)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all/any users by FIO", description = "Use fio for find 0-any users. Not use fio for find all users")
    public Page<UserDto> findUserByFio(@RequestParam(required = false, defaultValue = "") String search,
                                       @ParameterObject
                                       @PageableDefault(size = 10, page = 0, sort = "lastName,asc") Pageable pageable){
        return userService.findUserByFio(search, pageable);
    }
}
