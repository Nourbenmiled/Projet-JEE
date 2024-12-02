package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.Cours;
import info.exemple.springbootwebwithjsp.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cours")
public class CoursController {
    @Autowired
    private CoursService coursService;

    @GetMapping
    public String getAllCourses(Model model) {
        List<Cours> coursList = coursService.getAllCourses();
        model.addAttribute("courses",coursList );
        return "cours/list";
    }

    @GetMapping("/create")
    public String createCourseForm(Model model) {
        model.addAttribute("cours", new Cours());
        return "cours/form";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Cours cours) {
        coursService.saveCourse(cours);
        return "redirect:/cours";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cours", coursService.getCourseById(id));
        return "cours/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        coursService.deleteCourse(id);
        return "redirect:/cours";
    }
}

