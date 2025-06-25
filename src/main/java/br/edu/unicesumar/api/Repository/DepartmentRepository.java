package br.edu.unicesumar.api.Repository;

import br.edu.unicesumar.api.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
