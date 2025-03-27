package store.fooding.backend.dto.user.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostUserResponse {

    private Long userId;
    private String name;
}
