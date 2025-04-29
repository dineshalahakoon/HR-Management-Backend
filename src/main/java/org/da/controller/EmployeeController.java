package org.da.controller;

import lombok.RequiredArgsConstructor;
import org.da.dto.Employee;
import org.da.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

    final EmployeeService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Employee employee){
        service.addEmploye(employee);
    }
    @GetMapping()
    public List<Employee> getall(){
        return service.getAll();

    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        service.updateEmployee(employee, id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteEmployee(@PathVariable Long id) {
        service.removeEmployee(id);
        return "deleted";


    }

}
