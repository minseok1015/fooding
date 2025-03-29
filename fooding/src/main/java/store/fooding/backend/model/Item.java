package store.fooding.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "items")
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;
    private String itemDescription;
    private LocalDate expirationDate;
    private String itemLocation;
    private String itemStatus;
    private String quantity;

    private String thumbnailUrl; // 🔥 S3 URL 저장

    private String actorType; // "user" or "restaurant"
    private Long actorId;     // user_id or restaurant_id

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
