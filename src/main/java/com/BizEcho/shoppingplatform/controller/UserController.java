package com.BizEcho.shoppingplatform.controller;

import com.BizEcho.shoppingplatform.entity.User;
import com.BizEcho.shoppingplatform.exception.UsernameAlreadyExistsException;
import com.BizEcho.shoppingplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users") // 这将是类级别的基本路由
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        // 检查用户名或邮箱是否已经存在
        if (userService.findByUsername(newUser.getUsername()).isPresent() ||
                userService.findByEmail(newUser.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Username or email already in use.");
        }

        // 其他验证逻辑，例如密码复杂度检查等...

        // 尝试注册用户，捕获任何可能的异常（比如数据库错误）
        try {
            User user = userService.registerUser(newUser);
            if (user != null) {
                // 创建成功，返回用户信息，但应该省略密码和其他敏感信息
                user.setPassword(null); // 清除密码信息
                return ResponseEntity.ok(user);
            } else {
                // 这通常不会发生，因为如果有错误应该抛出异常
                return ResponseEntity.badRequest().body("User cannot be registered due to an unknown error.");
            }
        } catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // 通用错误处理
            return ResponseEntity.internalServerError().body("An error occurred during registration.");
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if(user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ... 其他的端点和方法
}
