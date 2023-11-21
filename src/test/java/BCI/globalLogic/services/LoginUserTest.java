package BCI.globalLogic.services;

import BCI.globalLogic.repositories.IUserRepository;
import BCI.globalLogic.entities.User;
import BCI.globalLogic.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUserTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private JWTUtil jwtUtil;

    @InjectMocks
    private LoginUser loginUser;

    @Test
    void testLogin() {
        // Arrange
        String mockToken = "mockToken";
        String userEmail = "test@example.com";
        User mockUser = User.builder()
                .email(userEmail)
                .lastLogin(null) // Assuming lastLogin is initially null
                .token(null) // Assuming token is initially null
                .build();

        when(jwtUtil.verify(mockToken)).thenReturn(userEmail);
        when(userRepository.findByEmail(userEmail)).thenReturn(mockUser);
        when(jwtUtil.createTokenWithShortExpiration(any(User.class))).thenReturn("newMockToken");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Act
        User result = loginUser.login(mockToken);

        // Assert
        verify(userRepository, times(1)).findByEmail(userEmail);
        verify(userRepository, times(1)).save(mockUser);
        assertNotNull(result);
        assertNotNull(result.getToken());
        assertNotNull(result.getLastLogin());
    }
}
