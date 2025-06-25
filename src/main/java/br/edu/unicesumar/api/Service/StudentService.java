package br.edu.unicesumar.api.Service;

import br.edu.unicesumar.api.Model.Student;
import br.edu.unicesumar.api.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student){
        return studentRepository.save(student);
    }

    public Student searchId(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno nao encontrado"));
    }

    public List<Student> listStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public Student update(Long id, Student student){
        Student existing = searchId(id);

        existing.setName(student.getName());
        existing.setCourse(student.getCourse());
        existing.setRegistration(student.getRegistration());

        return studentRepository.save(existing);
    }

    @Transactional
    public void deleteStudent(Long id){
        Student student = searchId(id);
        studentRepository.delete(student);
    }

}
