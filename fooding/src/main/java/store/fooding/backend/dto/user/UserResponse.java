package store.fooding.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String userName;
    private String email;
    private String location;
    private String phoneNumber;

}
