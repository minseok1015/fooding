package store.fooding.backend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MyPageResponse {
    private UserInfo user;
    private List<ItemInfo> items;

    @Getter @Setter
    @AllArgsConstructor
    public static class UserInfo {
        private Long userId;
        private String userName;
        private String phoneNumber;
        private String location;
    }

    @Getter @Setter
    @AllArgsConstructor
    public static class ItemInfo {
        private Long itemId;
        private String itemName;
        private String quantity;
        private LocalDate expirationDate;
        private String itemDescription;
    }
}
