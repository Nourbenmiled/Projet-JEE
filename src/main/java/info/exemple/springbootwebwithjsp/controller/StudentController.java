package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.Student;
import info.exemple.springbootwebwithjsp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> studentsList = studentService.getAllStudents();
        model.addAttribute("students",studentsList );
        return "students/list";
    }
    @GetMapping("/create")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "students/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
