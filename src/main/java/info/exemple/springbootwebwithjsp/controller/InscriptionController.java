package info.exemple.springbootwebwithjsp.controller;

import info.exemple.springbootwebwithjsp.modal.Cours;
import info.exemple.springbootwebwithjsp.modal.Inscription;
import info.exemple.springbootwebwithjsp.modal.Student;
import info.exemple.springbootwebwithjsp.modal.Teacher;
import info.exemple.springbootwebwithjsp.repository.CoursRepository;
import info.exemple.springbootwebwithjsp.repository.InscriptionRepository;
import info.exemple.springbootwebwithjsp.repository.StudentRepository;
import info.exemple.springbootwebwithjsp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/inscription")
public class InscriptionController {
    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;


    @GetMapping("/assign")
    public String assignCours(@RequestParam("coursId") Long coursId, Model model) {

        Cours cours = coursRepository.findById(coursId).orElseThrow(() -> new RuntimeException("Cours introuvable"));


        List<Teacher> teachers = teacherRepository.findAll();
        List<Student> students = studentRepository.findAll();

        // Ajouter les données au modèle
        model.addAttribute("cours", cours);
        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);

        return "cours/assignCours";
    }

    @PostMapping("/save")
    public String saveInscription(
            @RequestParam("coursId") Long coursId,
            @RequestParam("teacherIds") List<Long> teacherIds,
            @RequestParam("studentIds") List<Long> studentIds
    ) {
        Cours cours = coursRepository.findById(coursId).orElseThrow(() -> new RuntimeException("Cours introuvable"));

        List<Teacher> teachers = teacherRepository.findAllById(teacherIds);
        List<Student> students = studentRepository.findAllById(studentIds);

        Inscription inscription = new Inscription();
        inscription.setCours(cours);
        inscription.setTeachers(teachers);
        inscription.setStudents(students);

        inscriptionRepository.save(inscription);

        return "redirect:/cours";
    }

    @GetMapping("/list")
    public String listAffectations(@RequestParam("coursId") Long coursId, Model model) {
        // Récupérer le cours par son ID
        Cours cours = coursRepository.findById(coursId).orElseThrow(() -> new RuntimeException("Cours introuvable"));

        // Récupérer la liste des affectations du cours
        List<Inscription> inscriptions = inscriptionRepository.findByCoursId(coursId);

        // Ajouter les données au modèle
        model.addAttribute("cours", cours);
        model.addAttribute("inscriptions", inscriptions);

        return "cours/listAffectations"; // Vue JSP pour afficher la liste des affectations
    }

}
