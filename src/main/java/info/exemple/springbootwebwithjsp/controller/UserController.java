package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.User;
import info.exemple.springbootwebwithjsp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", userService.findAllStudents());

        return "users/student";
    }

    @GetMapping("/teachers")
    public String listTeachers(Model model) {
        model.addAttribute("teachers", userService.findAllTeacher());

        return "users/teacher";
    }

    @PostMapping("/teachers/delete")
    public String deleteTeachers(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/teachers";
    }

    @GetMapping("/validation")
    public String listValidation(Model model) {
        model.addAttribute("users", userService.getAllUsersExceptAdmin());

        return "users/validation";
    }



    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/validation/done/{id}")
    public String validationStudent(@PathVariable("id") Long id) {
        Optional<User> user = userService.userById(id);
        if (user.isPresent()) {
            user.get().setValidation(true);
            userService.editUser(user.get());
        }else {
            System.out.println("user not fount");
        }
        return "redirect:/validation";
    }
}

