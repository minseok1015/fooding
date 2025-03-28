package store.fooding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.fooding.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
