package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.Note;
import info.exemple.springbootwebwithjsp.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/noteStudent")
public class NoteStudentController {
    @Autowired
    private NoteService noteService;

    @GetMapping
    public String getAllNote(Model model) {
        List<Note> noteList = noteService.getAllNote();
        model.addAttribute("notes",noteList );
        return "NoteStudent/list";
    }
}
