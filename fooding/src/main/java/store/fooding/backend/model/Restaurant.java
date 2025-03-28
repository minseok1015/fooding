package store.fooding.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurants")
@Getter @Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    private String restaurantName;
    private String restaurantLocation;
    private String restaurantDescription;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
