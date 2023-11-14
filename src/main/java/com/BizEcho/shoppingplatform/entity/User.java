package com.BizEcho.shoppingplatform.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String idNumber;
    private String phoneNumber;
    private String region;
    private String password;
    private String email;



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

    //get
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date registrationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastLoginDate;

    // Getters and Setters
}