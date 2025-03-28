package store.fooding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.fooding.backend.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByActorTypeAndActorId(String actorType, Long actorId);

    @Query("SELECT i FROM Item i WHERE " +
            "LOWER(i.itemName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(i.itemDescription) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(i.itemLocation) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Item> searchItemsByKeyword(@Param("keyword") String keyword);
}

