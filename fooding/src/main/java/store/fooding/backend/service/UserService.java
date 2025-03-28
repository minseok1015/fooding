package store.fooding.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.fooding.backend.common.exception.BadRequestException;
import store.fooding.backend.common.response.status.BaseExceptionResponseStatus;
import store.fooding.backend.dto.user.SignupRequest;
import store.fooding.backend.dto.user.LoginRequest;
import store.fooding.backend.dto.user.UserNameResponse;
import store.fooding.backend.dto.user.UserResponse;
import store.fooding.backend.model.User;
import store.fooding.backend.repository.UserRepository;

import java.time.LocalDateTime;

import static store.fooding.backend.common.response.status.BaseExceptionResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse registerUser(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException(DUPLICATE_EMAIL);
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setUserPassword(request.getUserPassword());
        user.setLocation(request.getLocation());
        user.setCreateAt(LocalDateTime.now());

        User saved = userRepository.save(user);

        return new UserResponse(saved.getUserId(), saved.getUserName(), saved.getEmail(), saved.getLocation());
    }

    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException(EMAIL_NOT_FOUND));

        if (!user.getUserPassword().equals(request.getUserPassword())) {
            throw new BadRequestException(PASSWORD_NO_MATCH);
        }

        return new UserResponse(user.getUserId(), user.getUserName(), user.getEmail(), user.getLocation());
    }

    public UserNameResponse getUserNameById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(BaseExceptionResponseStatus.USER_NOT_FOUND));
        return new UserNameResponse(user.getUserId(), user.getUserName());
    }






}
