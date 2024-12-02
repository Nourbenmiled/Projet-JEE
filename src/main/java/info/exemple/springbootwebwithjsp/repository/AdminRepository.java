package info.exemple.springbootwebwithjsp.repository;

import info.exemple.springbootwebwithjsp.modal.Admin;
import info.exemple.springbootwebwithjsp.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
