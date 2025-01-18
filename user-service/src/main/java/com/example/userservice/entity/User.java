package com.example.userservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName; // Имя
    @Column(name = "lastName")
    private String lastName; // Фимилия
    @Column(name = "middleName")
    private String middleName; // Отчество

    @OneToMany(mappedBy = "user")
    private List<Account> accounts; // счета
}
