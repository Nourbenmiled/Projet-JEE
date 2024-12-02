package info.exemple.springbootwebwithjsp.services;

import info.exemple.springbootwebwithjsp.modal.Student;
import info.exemple.springbootwebwithjsp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentsRepository;

    public List<Student> getAllStudents() {return studentsRepository.findAll();}

    public Student getStudentById(Long id) {return studentsRepository.findById(id).orElse(null);}

    public Student saveStudent(Student student) {return studentsRepository.save(student);}

    public void deleteStudent(Long id) {studentsRepository.deleteById(id);}
}
