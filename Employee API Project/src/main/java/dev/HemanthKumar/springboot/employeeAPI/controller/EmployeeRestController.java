package dev.HemanthKumar.springboot.employeeAPI.controller;

import dev.HemanthKumar.springboot.employeeAPI.entity.Employee;
import dev.HemanthKumar.springboot.employeeAPI.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.createEmployee(employee),HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/lastname/{lastName}")
    public ResponseEntity<Employee> findByLastName(@PathVariable String lastName){
        return new ResponseEntity<>(employeeService.findByLastName(lastName),HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getById(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.findById(employeeId),HttpStatus.OK);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> update(@PathVariable Long employeeId, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeId, employee),HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> delete(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeId),HttpStatus.NO_CONTENT);
    }

}














