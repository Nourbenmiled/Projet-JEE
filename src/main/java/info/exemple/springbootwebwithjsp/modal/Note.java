package info.exemple.springbootwebwithjsp.modal;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private double note;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public Cours getCours() {
        return cours;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student getStudent() {
        return student;
    }

    public double getNote() {
        return note;
    }
}
