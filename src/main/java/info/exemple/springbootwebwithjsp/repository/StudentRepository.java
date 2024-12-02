package info.exemple.springbootwebwithjsp.repository;

import info.exemple.springbootwebwithjsp.modal.Student;
import info.exemple.springbootwebwithjsp.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
