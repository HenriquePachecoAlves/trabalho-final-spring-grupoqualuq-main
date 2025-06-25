package br.edu.unicesumar.api.Repository;

import br.edu.unicesumar.api.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
