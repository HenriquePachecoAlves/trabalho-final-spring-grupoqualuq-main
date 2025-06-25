package br.edu.unicesumar.api.Repository;

import br.edu.unicesumar.api.Model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
