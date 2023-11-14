package com.BizEcho.shoppingplatform.service;

import com.BizEcho.shoppingplatform.entity.User;
import com.BizEcho.shoppingplatform.exception.UsernameAlreadyExistsException;
import com.BizEcho.shoppingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

//    @Autowired
//    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//    }
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User registerUser(User newUser) throws UsernameAlreadyExistsException {
        // Check if the username or email is already in use
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }
        // This check should also use isPresent() since findByEmail returns an Optional
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new UsernameAlreadyExistsException("Email already in use.");
        }

        // Validate password strength and other user properties if needed
        validatePassword(newUser.getPassword());
        // ...

        // Save the new user to the database
        return userRepository.save(newUser);
    }

    // Implement a method to validate the password strength
    private void validatePassword(String password) {
        // Add your password strength validation logic here
        // Throw a custom exception or return a boolean as needed
    }

    // Other service methods...
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 其他服务方法...
}
