package org.da.service;

import org.da.dto.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmploye(Employee employee);
    void getAll();
    void removeEmployee(String productId);
    void updateEmployee(Employee employee);
}
