import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.BizEcho.shoppingplatform.entity.User;
import com.BizEcho.shoppingplatform.repository.UserRepository;
import com.BizEcho.shoppingplatform.service.UserService;
import com.BizEcho.shoppingplatform.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        // ... 其他必要的设置
    }

    @Test
    void registerUser_ShouldSaveNewUser() {
        // 设置模拟行为
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        // 调用方法
        User registeredUser = userService.registerUser(user);

        // 验证交互
        verify(userRepository).save(any(User.class));

        // 断言结果
        assertEquals("testUser", registeredUser.getUsername());
        assertEquals("password", registeredUser.getPassword()); // 先暂时不考虑加密密码
    }


    @Test
    void findByUsername_ShouldReturnUser_WhenUserExists() {
        // Arrange
        User expectedUser = new User();
        expectedUser.setUsername("existingUser");
        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(expectedUser));

        // Act
        Optional<User> actualUser = userService.findByUsername("existingUser");

        // Assert
        assertTrue(actualUser.isPresent());
        assertEquals(expectedUser.getUsername(), actualUser.get().getUsername());
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenUserExists() {
        // Arrange
        User expectedUser = new User();
        expectedUser.setEmail("existing@example.com");
        when(userRepository.findByEmail("existing@example.com")).thenReturn(Optional.of(expectedUser));

        // Act
        Optional<User> actualUser = userService.findByEmail("existing@example.com");

        // Assert
        assertTrue(actualUser.isPresent()); // 确保返回的 Optional 包含一个用户
        assertEquals(expectedUser.getEmail(), actualUser.get().getEmail()); // 确保返回的用户有正确的电子邮件地址
    }



}
