package store.fooding.backend.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import store.fooding.backend.model.Item;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
public class ItemResponse {
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private LocalDate expirationDate;
    private String itemLocation;
    private String quantity;
    private String itemStatus;
    private String categoryName;
    private String registeredBy;
    private String phoneNumber;

    public static ItemResponse from(Item item, String registeredBy, String phoneNumber) {
        return new ItemResponse(
                item.getItemId(),
                item.getItemName(),
                item.getItemDescription(),
                item.getExpirationDate(),
                item.getItemLocation(),
                item.getQuantity(),
                item.getItemStatus(),
                item.getCategory() != null ? item.getCategory().getCategoryName() : null,
                registeredBy,
                phoneNumber
        );
    }
}

