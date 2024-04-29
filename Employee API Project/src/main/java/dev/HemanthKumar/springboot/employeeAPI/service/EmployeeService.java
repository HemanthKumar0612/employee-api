package dev.HemanthKumar.springboot.employeeAPI.service;

import dev.HemanthKumar.springboot.employeeAPI.exception.ResourceNotFoundException;
import dev.HemanthKumar.springboot.employeeAPI.repository.EmployeeRepository;
import dev.HemanthKumar.springboot.employeeAPI.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",String.valueOf(employeeId)));
    }

    public Employee findByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName).orElseThrow(() -> new ResourceNotFoundException("Employee","Last Name",lastName));
    }

    public Employee updateEmployee(Long employeeId,Employee employee) {
        Employee oldEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",String.valueOf(employeeId)));
        oldEmployee.setEmail(employee.getEmail());
        oldEmployee.setFirstName(employee.getFirstName());
        oldEmployee.setLastName(employee.getLastName());
        return employeeRepository.save(oldEmployee);
    }

    public String deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",String.valueOf(employeeId)));
        try {
            employeeRepository.delete(employee);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return String.format("Employee with %d deleted successfully",employeeId);
    }
}






