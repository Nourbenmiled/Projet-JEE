package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.Cours;
import info.exemple.springbootwebwithjsp.modal.Note;
import info.exemple.springbootwebwithjsp.modal.Student;
import info.exemple.springbootwebwithjsp.modal.Teacher;
import info.exemple.springbootwebwithjsp.repository.CoursRepository;
import info.exemple.springbootwebwithjsp.repository.StudentRepository;
import info.exemple.springbootwebwithjsp.repository.TeacherRepository;
import info.exemple.springbootwebwithjsp.services.NoteService;
import info.exemple.springbootwebwithjsp.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CoursRepository coursRepository;

    @GetMapping
    public String getAllNote(Model model) {
        List<Note> noteList = noteService.getAllNote();
        model.addAttribute("notes",noteList );
        return "Note/list";
    }
    @GetMapping("/create")
    public String createNoteForm(Model model) {
        model.addAttribute("note", new Note());
        List<Teacher> teachers=teacherRepository.findAll();
        List<Student> students=studentRepository.findAll();
        List<Cours> cours=coursRepository.findAll();
        model.addAttribute("teachers",teachers);
        model.addAttribute("students",students);
        model.addAttribute("cours",cours);
        return "Note/form";
    }

    @PostMapping("/save")
    public String saveNote(@RequestParam("note") Double noteValue,
                           @RequestParam("teacher_id") Long teacherId,
                           @RequestParam("student_id") Long studentId,
                           @RequestParam("cours_id") Long coursId,
                           @RequestParam(value = "id", required = false) Long noteId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new RuntimeException("Cours not found"));
        Note note;
        if (noteId != null) {
            note = noteService.getNoteById(noteId);
        } else {
            note = new Note();
        }
        note.setNote(noteValue);
        note.setTeacher(teacher);
        note.setStudent(student);
        note.setCours(cours);
        noteService.saveNote(note);
        return "redirect:/note";
    }

    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable("id") Long id, Model model) {
        model.addAttribute("note", noteService.getNoteById(id));
        List<Teacher> teachers=teacherRepository.findAll();
        List<Student> students=studentRepository.findAll();
        List<Cours> cours=coursRepository.findAll();
        model.addAttribute("teachers",teachers);
        model.addAttribute("students",students);
        model.addAttribute("cours",cours);
        return "Note/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return "redirect:/note";
    }
}