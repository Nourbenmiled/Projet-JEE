package info.exemple.springbootwebwithjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/index")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/page1")
    public String showPage1() {
        return "home";
    }

    @GetMapping("/page2")
    public String showPage2() {
        return "courses";
    }

}
