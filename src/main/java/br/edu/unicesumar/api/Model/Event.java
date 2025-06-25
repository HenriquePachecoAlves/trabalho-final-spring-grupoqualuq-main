package br.edu.unicesumar.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 50)
    private String name;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime date;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer limitParticpants;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "event_speakers",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private Set<Speaker> Speaker = new HashSet<>();

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Speaker> getSpeaker() {
        return Speaker;
    }

    public void setSpeaker(Set<Speaker> speaker) {
        Speaker = speaker;
    }

    public List<Enrollment> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Enrollment> registrations) {
        this.registrations = registrations;
    }

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> registrations = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getLimitParticpants() {
        return limitParticpants;
    }

    public void setLimitParticpants(Integer limitParticpants) {
        this.limitParticpants = limitParticpants;
    }
}
