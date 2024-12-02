package info.exemple.springbootwebwithjsp.repository;

import info.exemple.springbootwebwithjsp.modal.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
}