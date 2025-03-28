package store.fooding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.fooding.backend.model.Sharing;

public interface SharingRepository extends JpaRepository<Sharing, Long> {
}
