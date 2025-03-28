package store.fooding.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.fooding.backend.dto.user.SignupRequest;
import store.fooding.backend.dto.user.LoginRequest;
import store.fooding.backend.dto.user.UserNameResponse;
import store.fooding.backend.dto.user.UserResponse;
import store.fooding.backend.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody SignupRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/{userId}/username")
    public UserNameResponse getUserNameById(@PathVariable Long userId) {
        return userService.getUserNameById(userId);
    }

}
