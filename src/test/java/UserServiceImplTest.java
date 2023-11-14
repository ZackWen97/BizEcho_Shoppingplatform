import static org.junit.jupiter.api.Assertions.assertEquals;
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
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");

        // 调用方法
        User registeredUser = userService.registerUser(user);

        // 验证交互
        verify(userRepository).save(any(User.class));
        verify(passwordEncoder).encode("password");

        // 断言结果
        assertEquals("testUser", registeredUser.getUsername());
        assertEquals("encryptedPassword", registeredUser.getPassword());
        // ... 其他断言
    }
}
