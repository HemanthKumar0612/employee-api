package dev.HemanthKumar.springboot.employeeAPI.service;

import dev.HemanthKumar.springboot.employeeAPI.entity.Employee;
import dev.HemanthKumar.springboot.employeeAPI.exception.ResourceNotFoundException;
import dev.HemanthKumar.springboot.employeeAPI.repository.EmployeeRepository;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository mockRepository;
    @InjectMocks
    private EmployeeService mockService;

    @Test
    void testCreateEmployee() {
        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("John");
        actualEmployee.setLastName("Doe");
        actualEmployee.setEmail("john.doe@example.com");

        when(mockRepository.save(actualEmployee)).thenReturn(actualEmployee);

        Employee expectedEmployee = mockService.createEmployee(actualEmployee);

        verify(mockRepository,times(1)).save(actualEmployee);

        assertEquals(actualEmployee,expectedEmployee);

    }

    @Test
    void testFindAll() {

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");
        List<Employee> actualEmployees = new ArrayList<>();
        actualEmployees.add(employee);

        when(mockRepository.findAll()).thenReturn(actualEmployees);

        List<Employee> expectedEmployees = mockService.findAll();

        verify(mockRepository,times(1)).findAll();

        assertEquals(actualEmployees,expectedEmployees);
    }


    @Test
    void testFindById_IfExists() {
        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("John");
        actualEmployee.setLastName("Doe");
        actualEmployee.setEmail("john.doe@example.com");

        when(mockRepository.findById(1L)).thenReturn(Optional.of(actualEmployee));

        Employee expectedEmployee = mockService.findById(1L);
        verify(mockRepository,times(1)).findById(1L);

        assertEquals(actualEmployee,expectedEmployee);
    }

    @Test
    void testFindById_IfNotExists() {

        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> mockService.findById(1L));

        assertEquals("Employee not found with Id : 1", exception.getMessage());
    }

    @Test
    void testFindByLastName_IfExists() {
        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("John");
        actualEmployee.setLastName("Doe");
        actualEmployee.setEmail("john.doe@example.com");

        when(mockRepository.findByLastName("Doe")).thenReturn(Optional.of(actualEmployee));

        Employee expectedEmployee = mockService.findByLastName("Doe");

        verify(mockRepository,times(1)).findByLastName("Doe");

        assertEquals(actualEmployee,expectedEmployee);
    }

    @Test
    void testFindByLastName_IfNotExists() {

        when(mockRepository.findByLastName(anyString())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> mockService.findByLastName("Doe"));

        assertEquals("Employee not found with Last Name : Doe", exception.getMessage());
    }

    @Test
    void testUpdateEmployee_IfExists() {
        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("Hemanth");
        actualEmployee.setLastName("Muluka");
        actualEmployee.setEmail("mhk123@example.com");

        Employee oldEmployee = new Employee();
        oldEmployee.setId(1L);
        oldEmployee.setFirstName("Hemanth Kumar");
        oldEmployee.setLastName("Muluka");
        oldEmployee.setEmail("mhk@example.com");

        when(mockRepository.findById(anyLong())).thenReturn(Optional.of(oldEmployee));
        when(mockRepository.save(any())).thenReturn(actualEmployee);

        Employee expectedEmployee = mockService.updateEmployee(1L,oldEmployee);

        verify(mockRepository,times(1)).findById(1L);
        verify(mockRepository,times(1)).save(oldEmployee);

        assertEquals(actualEmployee,expectedEmployee);
    }

    @Test
    void testUpdateEmployee_IfNotExists() {
        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("Hemanth");
        actualEmployee.setLastName("Muluka");
        actualEmployee.setEmail("mhk123@example.com");

        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> mockService.updateEmployee(1L,actualEmployee));

        verify(mockRepository,times(1)).findById(1L);
        assertEquals("Employee not found with Id : 1", exception.getMessage());
    }

    @Test
    void testDeleteEmployee_IfExists() {

        Employee actualEmployee = new Employee();
        actualEmployee.setId(1L);
        actualEmployee.setFirstName("John");
        actualEmployee.setLastName("Doe");
        actualEmployee.setEmail("john.doe@example.com");

        when(mockRepository.findById(1L)).thenReturn(Optional.of(actualEmployee));

        String message = mockService.deleteEmployee(1L);

        verify(mockRepository,times(1)).delete(actualEmployee);

        assertEquals("Employee with 1 deleted successfully",message);
    }

    @Test
    void testDeleteEmployee_IfNotExists() {

        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> mockService.deleteEmployee(1L));

        verify(mockRepository,times(1)).findById(1L);
        assertEquals("Employee not found with Id : 1", exception.getMessage());

    }

}