package dev.HemanthKumar.springboot.employeeAPI.repository;

import dev.HemanthKumar.springboot.employeeAPI.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByLastName(String lastName);
}
