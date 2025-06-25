package br.edu.unicesumar.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Speaker")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String miniResume;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String Institution;

    public Set<Event> getEventos() {
        return eventos;
    }

    public void setEventos(Set<Event> eventos) {
        this.eventos = eventos;
    }

    @ManyToMany(mappedBy = "Speaker")
    private Set<Event> eventos = new HashSet<>();

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

    public String getMiniResume() {
        return miniResume;
    }

    public void setMiniResume(String miniResume) {
        this.miniResume = miniResume;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }
}
