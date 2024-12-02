package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.Teacher;
import info.exemple.springbootwebwithjsp.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/teacherStudent")
public class TeacherStudentController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String getAllTeachers(Model model) {
        List<Teacher> teachersList = teacherService.getAllTeachers();
        model.addAttribute("teachers",teachersList );
        return "teacherStudent/list";
    }
}
