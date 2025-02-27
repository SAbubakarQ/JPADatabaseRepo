package com.linkedinlearning.jpa.repository;

import com.linkedinlearning.jpa.entity.Employee;

import java.util.List;
import java.util.Optional;

//isolate the persistence logic for each entity using the Repository pattern
public interface EmployeeRepository {
    Optional<Employee> save(Employee employee);
    Optional<Employee> getEmployeeById(Long id);
    List<Employee> getEmployeesByExperienceCriteriaQuery(Integer yearsExperience);
    Optional<Employee> getEmployeeByFullName(String firstName, String LastName);

    List<Employee> getEmployeeByLastName(String lastName);
    void deleteEmployee(Employee employee);
}
