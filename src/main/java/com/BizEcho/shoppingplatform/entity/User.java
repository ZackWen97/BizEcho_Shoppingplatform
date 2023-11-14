package com.BizEcho.shoppingplatform.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users") // 与表名相同
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(name = "identification_number", unique = true, nullable = false)
    private String idNumber;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime registrationDate;

    @Column
    @LastModifiedDate
    private LocalDateTime lastLoginDate;
    // ...加上getter和setter



    //init
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
//    public void setRole(String role) {
//        this.role = role;
//    }

    //get
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

//    public String getRole() {
//        return role;
//    }



    // Getters and Setters
}