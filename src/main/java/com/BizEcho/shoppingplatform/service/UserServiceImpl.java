package com.BizEcho.shoppingplatform.service;

import com.BizEcho.shoppingplatform.entity.User;
import com.BizEcho.shoppingplatform.exception.UsernameAlreadyExistsException;
import com.BizEcho.shoppingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }




    @Override
    public User registerUser(User newUser) {
        // 检查用户名是否已存在
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            // 抛出异常或返回null，因为用户名已存在
            throw new UsernameAlreadyExistsException(); // 自定义异常或者选择其他错误处理方式
        }

        // 加密用户密码
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        // 保存用户
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    // 其他服务方法...
}
