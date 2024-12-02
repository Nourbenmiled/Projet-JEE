package info.exemple.springbootwebwithjsp.repository;

import info.exemple.springbootwebwithjsp.modal.Teacher;
import info.exemple.springbootwebwithjsp.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
