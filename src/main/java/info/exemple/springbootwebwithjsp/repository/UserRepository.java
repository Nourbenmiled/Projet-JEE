package info.exemple.springbootwebwithjsp.repository;

import info.exemple.springbootwebwithjsp.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    List<User> findByRoleAndValidationTrue(String role);
    List<User> findByRoleNot(String role);
}