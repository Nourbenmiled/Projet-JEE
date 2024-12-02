package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.User;
import info.exemple.springbootwebwithjsp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, @RequestParam String role) {
        user.setRole(role);
        userService.saveUser(user);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            return "redirect:/cours"; // Rediriger vers la page d'accueil après connexion réussie
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
