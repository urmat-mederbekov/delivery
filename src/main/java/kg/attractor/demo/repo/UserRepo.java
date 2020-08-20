package kg.attractor.demo.repo;

import kg.attractor.demo.model.Role;
import kg.attractor.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByEmailAndRole(String email, Role role);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(Role role);
    Page<User> findAllByRole(Role role, Pageable pageable);
}
