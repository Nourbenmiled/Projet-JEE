package info.exemple.springbootwebwithjsp.repository;

import info.exemple.springbootwebwithjsp.modal.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> findByCoursId(Long coursId);
}
