package com.example.userservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private Long id;
    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
