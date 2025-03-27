package store.fooding.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.fooding.backend.model.User;
import store.fooding.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String name, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = User.builder()
                .name(name)
                .password(encodedPassword)
                .build();
        return userRepository.save(user);
    }
}
