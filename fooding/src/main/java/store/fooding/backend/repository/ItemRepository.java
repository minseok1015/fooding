package store.fooding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.fooding.backend.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUser_UserId(Long userId);

}
