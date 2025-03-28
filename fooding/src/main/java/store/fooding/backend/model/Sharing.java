package store.fooding.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sharing")
@Getter @Setter
public class Sharing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sharingId;

    private String fromType;
    private Long fromId;
    private String toType;
    private Long toId;

    @Column(name = "shared_at")
    private LocalDateTime sharedAt;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
