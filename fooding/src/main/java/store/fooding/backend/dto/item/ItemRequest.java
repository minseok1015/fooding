package store.fooding.backend.dto.item;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ItemRequest {
    private String itemName;
    private String itemDescription;
    private String expirationDate;
    private String itemLocation;
    private String quantity;
    private String itemStatus;
    private Long categoryId;
}

