package store.fooding.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.fooding.backend.dto.user.user.PostUserRequest;
import store.fooding.backend.dto.user.user.PostUserResponse;
import store.fooding.backend.model.User;
import store.fooding.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public PostUserResponse register(@RequestBody @Valid PostUserRequest request) {
        User savedUser = userService.registerUser(request.getName(), request.getPassword());
        return PostUserResponse.builder()
                .userId(savedUser.getUserId())
                .name(savedUser.getName())
                .build();
    }
}
