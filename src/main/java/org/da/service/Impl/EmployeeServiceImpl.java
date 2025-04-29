package org.da.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.da.Entity.EmployeeEntity;
import org.da.dto.Employee;
import org.da.repository.EmployeeRepository;
import org.da.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    final EmployeeRepository repository;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]{1,100}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,4}$");
    private static final List<String> VALID_DEPARTMENTS = Arrays.asList("HR", "IT", "Finance", "Operations");

    @Override
    public void addEmploye(Employee employee) {
        // === VALIDATION ===
        if (!isValidName(employee.getName())) {
            throw new IllegalArgumentException("Invalid name. Only alphabetic characters and spaces are allowed (max 100 characters).");
        }

        if (!isValidEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        if (!isValidDepartment(employee.getDepartment())) {
            throw new IllegalArgumentException("Invalid department. Allowed: HR, IT, Finance, Operations.");
        }

        // === Create timestamps ===
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        employee.setCreateAt(now);


        EmployeeEntity employeeEntity = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
        repository.save(employeeEntity);
    }

    private boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name.trim()).matches();
    }

    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    private boolean isValidDepartment(String dept) {
        return dept != null && VALID_DEPARTMENTS.contains(dept.trim());
    }

    @Override
    public List<Employee> getAll() {
        Iterable<EmployeeEntity> allEmployee = repository.findAll();
        List<Employee> employeeList = new ArrayList<>();
        allEmployee.forEach(entity ->
                employeeList.add(new ObjectMapper().convertValue(entity, Employee.class))
        );
        return employeeList;
    }
    @Override
    public void updateEmployee(Employee employee, Long id) {
        // Validate name
        if (employee.getName() == null || !employee.getName().matches("^[A-Za-z\\s]{1,100}$")) {
            throw new IllegalArgumentException("Invalid name. Only letters and spaces allowed, max 100 characters.");
        }

        // Validate email
        if (employee.getEmail() == null || !employee.getEmail()
                .matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,6}$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        // Validate department
        List<String> validDepartments = List.of("HR", "IT", "Finance", "Operations");
        if (employee.getDepartment() == null || !validDepartments.contains(employee.getDepartment())) {
            throw new IllegalArgumentException("Invalid department. Must be one of HR, IT, Finance, or Operations.");
        }

        // Proceed with update
        Optional<EmployeeEntity> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            EmployeeEntity updated = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
            updated.setId(id); // Ensure ID is preserved

            // Set updated timestamp (UplordAt)
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString();
            updated.setUplordAt(now);

            repository.save(updated);
        } else {
            throw new IllegalArgumentException("Employee with ID " + id + " not found.");
        }
    }



    @Override
    public void removeEmployee(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public Employee findById(Long id) {
        if(repository.findById(id).isPresent()){
            Optional<EmployeeEntity> byId = repository.findById(id);
            return new ObjectMapper().convertValue(byId.get(), Employee.class);
        }

        return new Employee();
    }
}
