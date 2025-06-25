package br.edu.unicesumar.api.Controller;

import br.edu.unicesumar.api.Model.Department;
import br.edu.unicesumar.api.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> findAll(){
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.searchId(id));
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody @Valid Department department){
        Department createDepartment = departmentService.create(department);
        return ResponseEntity.created(URI.create("/departments/" + createDepartment.getId())).body(createDepartment);
    }

     @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody @Valid Department department){
        Department updateDepartment = departmentService.update(id, department);
        return ResponseEntity.ok(updateDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
