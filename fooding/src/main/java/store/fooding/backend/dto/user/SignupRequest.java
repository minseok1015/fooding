package store.fooding.backend.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {
    private String userName;
    private String email;
    private String userPassword;
    private String location;
    private String phoneNumber;
}
