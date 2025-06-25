package br.edu.unicesumar.api.Controller;

import br.edu.unicesumar.api.Model.Student;
import br.edu.unicesumar.api.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = studentService.listStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Student student = studentService.searchId(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid Student student) {
        Student createdStudent = studentService.create(student);
        return ResponseEntity.created(URI.create("/students/" + createdStudent.getId())).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody @Valid Student student) {
        Student updatedStudent = studentService.update(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
