package org.da.service;

import org.da.dto.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmploye(Employee employee);
    List<Employee> getAll();
    void updateEmployee(Employee employee,Long id);
    void removeEmployee(Long id);

    Employee findById(Long id);
}
