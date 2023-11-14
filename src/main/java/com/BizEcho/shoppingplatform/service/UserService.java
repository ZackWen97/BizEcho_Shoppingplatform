package com.BizEcho.shoppingplatform.service;

import com.BizEcho.shoppingplatform.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User newUser);
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    // 其他业务方法...
}