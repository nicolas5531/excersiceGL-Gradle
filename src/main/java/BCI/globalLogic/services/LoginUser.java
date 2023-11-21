package BCI.globalLogic.services;

import BCI.globalLogic.repositories.IUserRepository;
import BCI.globalLogic.entities.User;
import BCI.globalLogic.util.JWTUtil;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Builder
public class LoginUser {
    private final IUserRepository userRepository;
    private final JWTUtil jwtUtil;

    public User login(String token) {
        User user = getUser(token);

        user.setLastLogin(LocalDateTime.now());

        String newToken = jwtUtil.createTokenWithShortExpiration(user);
        user.setToken(newToken);

        return userRepository.save(user);
    }

    private User getUser(String token) {
        String userEmail = jwtUtil.verify(token);

        return userRepository.findByEmail(userEmail);
    }
}
