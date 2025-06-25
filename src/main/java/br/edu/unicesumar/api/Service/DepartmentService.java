package br.edu.unicesumar.api.Service;

import br.edu.unicesumar.api.DTOs.DepartmentReportDTO;
import br.edu.unicesumar.api.Model.Department;
import br.edu.unicesumar.api.Model.Event;
import br.edu.unicesumar.api.Repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department create(Department department){
        return departmentRepository.save(department);
    }

    public Department searchId(Long id){
       return departmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department update(Long id, Department department){
        Department existing = searchId(id);

        existing.setName(department.getName());
        existing.setSigla(department.getSigla());
        existing.setResponsible(department.getResponsible());

        return departmentRepository.save(existing);
    }

    public void delete(Long id){
        Department department = searchId(id);
        departmentRepository.delete(department);
    }

    public DepartmentReportDTO generateReport(Long id) {
        Department departamento = searchId(id);

        List<Event> events = departamento.getEvents();

        int totalEvents = events.size();
        int totalRegistrations = events.stream()
                .mapToInt(event -> event.getRegistrations().size())
                .sum();

        return new DepartmentReportDTO(totalEvents, totalRegistrations);
    }
}
