package org.da.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.da.Entity.EmployeeEntity;
import org.da.dto.Employee;
import org.da.repository.EmployeeRepository;
import org.da.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    final EmployeeRepository repository;

    @Override
    public void addEmploye(Employee employee) {
        EmployeeEntity employeeEntity
                = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
        EmployeeEntity savedEmploye = repository.save(employeeEntity);

    }

    @Override
    public List<Employee> getAll() {
        Iterable<EmployeeEntity> allEmployee = repository.findAll();
        List<Employee> employeeList=new ArrayList<>();
        allEmployee.forEach(employeeEntity -> {
            employeeList.add(new ObjectMapper().convertValue(employeeEntity, Employee.class));
        });
        return employeeList;
    }

    @Override
    public void updateEmployee(Employee employee, Long id) {
        repository.findById(id).ifPresent(existingEmployee -> {
            EmployeeEntity updated = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
            updated.setId(id); // Ensure ID stays the same
            repository.save(updated);
        });
    }
    @Override
    public void removeEmployee(Long id) {

        if (repository.existsById(id))
        {
            repository.deleteById(id);}
    }





}
