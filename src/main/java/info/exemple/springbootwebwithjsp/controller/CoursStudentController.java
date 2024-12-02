package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.Cours;
import info.exemple.springbootwebwithjsp.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/coursStudent")
public class CoursStudentController {
    @Autowired
    private CoursService coursService;

    @GetMapping
    public String getAllCourses(Model model) {
        List<Cours> coursList = coursService.getAllCourses();
        model.addAttribute("courses",coursList );
        return "coursStudent/list";
    }
}
