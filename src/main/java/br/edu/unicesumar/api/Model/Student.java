package br.edu.unicesumar.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudent")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String registration;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String course;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> inscricoes = new ArrayList<>();

    public List<Enrollment> getInscricoes() {
        return inscricoes;
    }

    public void setInscricoes(List<Enrollment> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
