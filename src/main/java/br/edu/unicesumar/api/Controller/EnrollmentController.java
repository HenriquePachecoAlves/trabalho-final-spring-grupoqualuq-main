package br.edu.unicesumar.api.Controller;

import br.edu.unicesumar.api.DTOs.IdStudentRequest;
import br.edu.unicesumar.api.Model.Enrollment;
import br.edu.unicesumar.api.Service.EnrollmentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/events/{idEvento}/registrations")
    public ResponseEntity<Enrollment> registerStudent(@PathVariable Long idEvent, @RequestBody @NotNull IdStudentRequest request) {
        Enrollment enrollment = enrollmentService.RegisterStudent(idEvent, request.getIdStudent());
        return ResponseEntity.created(URI.create("/registrations/" + enrollment.getId())).body(enrollment);
    }

    @GetMapping("/students/{idAluno}/registrations")
    public ResponseEntity<List<Enrollment>> listEnrollments(@PathVariable Long idStudent) {
        List<Enrollment> enrollments = enrollmentService.listByStudent(idStudent);
        return ResponseEntity.ok(enrollments);
    }

    @DeleteMapping
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        enrollmentService.unsubscribe(id);
        return ResponseEntity.noContent().build();
    }

}
