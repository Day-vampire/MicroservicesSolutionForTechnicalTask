package com.example.userservice.repository;

import com.example.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long> {

    Optional<User> findById(Long id);

    @Query("SELECT u FROM User u WHERE LOWER(CONCAT(u.lastName, ' ', u.firstName,' ', u.middleName)) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<User> findByFio(String search, Pageable pageable);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE LOWER(u.lastName) = LOWER(:lastName) AND LOWER(u.firstName) = LOWER(:firstName) AND LOWER(u.middleName) = LOWER(:middleName)")
    boolean existsByFio(String lastName, String firstName, String middleName);
}
