package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.Teacher;
import info.exemple.springbootwebwithjsp.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String getAllTeachers(Model model) {
        List<Teacher> teachersList = teacherService.getAllTeachers();
        model.addAttribute("teachers",teachersList );
        return "teachers/list";
    }
    @GetMapping("/create")
    public String createTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers/form";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editTeacher(@PathVariable("id") Long id, Model model) {
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "teachers/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }
}

