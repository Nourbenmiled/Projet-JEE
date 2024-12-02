package info.exemple.springbootwebwithjsp.services;

import info.exemple.springbootwebwithjsp.modal.Cours;
import info.exemple.springbootwebwithjsp.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursService {
    @Autowired
    private CoursRepository coursRepository;

    public List<Cours> getAllCourses() {
        return coursRepository.findAll();
    }

    public Cours getCourseById(Long id) {
        return coursRepository.findById(id).orElse(null);
    }

    public Cours saveCourse(Cours cours) {
        return coursRepository.save(cours);
    }

    public void deleteCourse(Long id) {
        coursRepository.deleteById(id);
    }
}

