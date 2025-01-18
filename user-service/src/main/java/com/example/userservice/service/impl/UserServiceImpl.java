package com.example.userservice.service.impl;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.exception.AlreadyExistsException;
import com.example.userservice.exception.NotFoundException;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {

        String userLastName = userDto.getLastName();
        String userFirstName = userDto.getFirstName();
        String userMiddleName = userDto.getMiddleName();

        if (userLastName == null || userLastName.isBlank()) { throw new IllegalArgumentException("User Last Name is null or empty");}
        if (userFirstName == null || userFirstName.isBlank()) { throw new IllegalArgumentException("User First Name is null or empty");}
        if (userMiddleName == null || userMiddleName.isBlank()) { throw new IllegalArgumentException("User Middle Name is null or empty");}
        if (userRepository.existsByFio(userLastName, userFirstName, userMiddleName)) {throw new AlreadyExistsException("This user already exists");}

        User user = userMapper.toUser(userDto);
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id %s not found".formatted(id)));
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UserDto> findUserByFio(String fio,Pageable pageable) {
        return userRepository
                .findByFio(fio, pageable)
                .map(userMapper::toUserDto);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findUserById(Long id) {
        return userRepository
                .findById(id)
                .map(userMapper::toUserDto)
                .orElseThrow(()-> new NotFoundException("User with id %d not found".formatted(id)));
    }

}
