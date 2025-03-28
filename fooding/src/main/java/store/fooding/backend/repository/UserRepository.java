package store.fooding.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.fooding.backend.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
