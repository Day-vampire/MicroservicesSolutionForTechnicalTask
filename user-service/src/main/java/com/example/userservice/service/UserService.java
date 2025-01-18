package com.example.userservice.service;
//
//Функционал
//1. Добавление пользователя
//2. Удаление пользователя
//3. Изменение данных о пользователе
//4. Поиск по Id
//5. Полнотекстовый поиск по ФИО
//6. Получение списка пользователей

import com.example.userservice.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto createUser(UserDto userDto);
    void deleteUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto findUserById(Long id);
    Page<UserDto> findUserByFio(String fio, Pageable pageable);

}
