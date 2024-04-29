package dev.HemanthKumar.springboot.employeeAPI.controller;


import dev.HemanthKumar.springboot.employeeAPI.entity.Employee;
import dev.HemanthKumar.springboot.employeeAPI.exception.ResourceNotFoundException;
import dev.HemanthKumar.springboot.employeeAPI.repository.EmployeeRepository;
import static org.mockito.Mockito.*;

import dev.HemanthKumar.springboot.employeeAPI.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class EmployeeRestControllerTest {
    @Mock
    EmployeeService mockService;
    @InjectMocks
    EmployeeRestController mockRestController;

    @Test
    void testCreate() {
        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("John");
        actualEmployee.setLastName("Doe");
        actualEmployee.setEmail("john.doe@example.com");

        ResponseEntity<Employee> actualResponse = new ResponseEntity<>(actualEmployee, HttpStatus.CREATED);

        when(mockService.createEmployee(actualEmployee)).thenReturn(actualEmployee);

        ResponseEntity<Employee> expectedResponse = mockRestController.create(actualEmployee);

        verify(mockService,times(1)).createEmployee(actualEmployee);

        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void testGetAll() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmail("john.doe@example.com");

        List<Employee> actualEmployeeList = new ArrayList<Employee>();
        actualEmployeeList.add(employee1);

        ResponseEntity<List<Employee>> actualResponse = new ResponseEntity<>(actualEmployeeList, HttpStatus.OK);

        when(mockService.findAll()).thenReturn(actualEmployeeList);

        ResponseEntity<List<Employee>> expectedResponse = mockRestController.getAll();

        verify(mockService,times(1)).findAll();

        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void testFindByLastName_IfExists() {
        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("John");
        actualEmployee.setLastName("Doe");
        actualEmployee.setEmail("john.doe@example.com");

        ResponseEntity<Employee> actualResponse = new ResponseEntity<>(actualEmployee, HttpStatus.OK);

        when(mockService.findByLastName("Doe")).thenReturn(actualEmployee);

        ResponseEntity<Employee> expectedResponse = mockRestController.findByLastName("Doe");

        verify(mockService,times(1)).findByLastName("Doe");

        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void testFindByLastName_IfNotExists() {

        when(mockService.findByLastName("Doe")).thenThrow(new ResourceNotFoundException("Employee","Last Name","Doe"));

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, () -> mockRestController.findByLastName("Doe"));

        verify(mockService,times(1)).findByLastName("Doe");
    }


    @Test
    void testGetById_IfExists() {
        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("John");
        actualEmployee.setLastName("Doe");
        actualEmployee.setEmail("john.doe@example.com");

        ResponseEntity<Employee> actualResponse = new ResponseEntity<>(actualEmployee, HttpStatus.OK);

        when(mockService.findById(1L)).thenReturn(actualEmployee);

        ResponseEntity<Employee> expectedResponse = mockRestController.getById(1L);

        verify(mockService,times(1)).findById(1L);

        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void testGetById_IfNotExists() {

        when(mockService.findById(1L)).thenThrow(new ResourceNotFoundException("Employee","Id",String.valueOf(1L)));

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, () -> mockRestController.getById(1L));

        verify(mockService,times(1)).findById(1L);
    }

    @Test
    void testUpdate_IfExists() {
        Employee oldEmployee = new Employee();
        oldEmployee.setId(1L);
        oldEmployee.setFirstName("John");
        oldEmployee.setLastName("Doe");
        oldEmployee.setEmail("john.doe@example.com");

        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("John Harold");
        actualEmployee.setLastName("Doe");
        actualEmployee.setEmail("john.doeH@example.com");

        ResponseEntity<Employee> actualResponse = new ResponseEntity<>(actualEmployee, HttpStatus.OK);

        when(mockService.updateEmployee(1L,oldEmployee)).thenReturn(actualEmployee);

        ResponseEntity<Employee> expectedResponse = mockRestController.update(1L,oldEmployee);

        verify(mockService,times(1)).updateEmployee(1L,oldEmployee);

        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    void testUpdate_IfNotExists() {
        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("John");
        actualEmployee.setLastName("Doe");
        actualEmployee.setEmail("john.doe@example.com");

        when(mockService.updateEmployee(1L,actualEmployee)).thenThrow(new ResourceNotFoundException("Employee","Id",String.valueOf(1L)));

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, () -> mockRestController.update(1L,actualEmployee));

        verify(mockService,times(1)).updateEmployee(1L,actualEmployee);
    }

    @Test
    void testDelete_IfExists() {

        ResponseEntity<String> actualResponse = new ResponseEntity<>(String.format("Employee with %d deleted successfully",1L), HttpStatus.NO_CONTENT);

        when(mockService.deleteEmployee(1L)).thenReturn(String.format("Employee with %d deleted successfully",1L));

        ResponseEntity<String> expectedResponse = mockRestController.delete(1L);

        verify(mockService,times(1)).deleteEmployee(1L);

        assertEquals(expectedResponse,actualResponse);
    }
    @Test
    void testDelete_IfNotExists() {
        when(mockService.deleteEmployee(1L)).thenThrow(new ResourceNotFoundException("Employee","Id",String.valueOf(1L)));

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, () -> mockRestController.delete(1L));

        verify(mockService,times(1)).deleteEmployee(1L);
    }
}