package store.fooding.backend.dto.item;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ItemRequest {
    private String itemName;
    private String itemDescription;
    private LocalDate expirationDate;
    private String itemLocation;
    private String itemStatus;
    private Integer quantity;
    private Long categoryId;
    private String thumbnailUrl;
}
