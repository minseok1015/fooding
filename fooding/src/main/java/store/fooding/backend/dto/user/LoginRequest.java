package store.fooding.backend.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String email;
    private String userPassword;
}