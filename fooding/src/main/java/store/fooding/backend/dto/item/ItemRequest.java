package store.fooding.backend.dto.item;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ItemRequest {
    private String itemName;
    private String itemDescription;
    private LocalDate expirationDate;
    private String itemLocation;
    private String quantity;
    private String itemStatus; // SHARING or EXCHANGE
}
