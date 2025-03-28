package store.fooding.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNameResponse {
    private Long userId;
    private String userName;
}
