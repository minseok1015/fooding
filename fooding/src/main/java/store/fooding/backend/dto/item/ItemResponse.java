package store.fooding.backend.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import store.fooding.backend.model.Item;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ItemResponse {
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private LocalDate expirationDate;
    private String itemLocation;
    private Integer quantity;
    private String itemStatus;
    private String registeredBy; // optional

    public static ItemResponse from(Item item, String registeredBy) {
        return new ItemResponse(
                item.getItemId(),
                item.getItemName(),
                item.getItemDescription(),
                item.getExpirationDate(),
                item.getItemLocation(),
                item.getQuantity(),
                item.getItemStatus(),
                registeredBy
        );
    }
}
