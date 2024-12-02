package info.exemple.springbootwebwithjsp.services;

import info.exemple.springbootwebwithjsp.modal.Admin;
import info.exemple.springbootwebwithjsp.modal.Student;
import info.exemple.springbootwebwithjsp.modal.Teacher;
import info.exemple.springbootwebwithjsp.modal.User;
import info.exemple.springbootwebwithjsp.repository.AdminRepository;
import info.exemple.springbootwebwithjsp.repository.StudentRepository;
import info.exemple.springbootwebwithjsp.repository.TeacherRepository;
import info.exemple.springbootwebwithjsp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    @Autowired
    private StudentRepository studentRepository;

    public User saveUser(User user) {
        User user1 = userRepository.findByUsername(user.getUsername());
        if(user1 == null){
            if(user.getRole().equals("admin")){
                user.setValidation(true);
                Admin admin = new Admin();
                admin.setNom(user.getNom());
                admin.setPrenom(user.getPrenom());
                admin.setValidation(true);
                adminRepository.save(admin);
            }else if (user.getRole().equals("teacher")){
                user.setValidation(false);
                Teacher teacher = new Teacher();
                teacher.setNom(user.getNom());
                teacher.setPrenom(user.getPrenom());
                teacher.setValidation(true);
                teacherRepository.save(teacher);
            }else {
                user.setValidation(false);
                Student student = new Student();
                student.setNom(user.getNom());
                student.setPrenom(user.getPrenom());
                student.setValidation(true);
                studentRepository.save(student);
            }
            return userRepository.save(user);
        }else{
            return null ;
        }

    }

    public User editUser(User user){
        return userRepository.save(user);
    }

    public User authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllStudents() {
        return userRepository.findByRoleAndValidationTrue("student");
    }

    public List<User> findAllTeacher() {
        return userRepository.findByRoleAndValidationTrue("teacher");
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> userById(Long id) {
       return userRepository.findById(id);
    }

    public List<User> getAllUsersExceptAdmin() {
        return userRepository.findByRoleNot("admin");
    }
}
