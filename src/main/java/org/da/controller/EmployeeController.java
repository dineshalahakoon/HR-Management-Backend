package org.da.controller;

import org.da.dto.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("employees")

public class EmployeeController {

    @PostMapping("/add-employee")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Employee employe1){

    }

    @GetMapping("/get-all")
    public void getall(){

    }

    @DeleteMapping("/delete-emp/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEmployee(@PathVariable String id) {

    }
    @PutMapping("/update-employee")
    public void updateEmployee(@RequestBody Employee employe1){

    }
}
