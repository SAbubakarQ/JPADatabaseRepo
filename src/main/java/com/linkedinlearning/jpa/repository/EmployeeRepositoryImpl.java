package com.linkedinlearning.jpa.repository;

import com.linkedinlearning.jpa.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//isolate the persistence logic for each entity using the Repository pattern
public class EmployeeRepositoryImpl implements EmployeeRepository {
    EntityManager entityManager;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Employee> save(Employee employee) {
        try {
            entityManager.getTransaction().begin(); //uncomment if not using @Transactional
            if (employee.getId() == null) {
                entityManager.persist(employee);
            } else {
                employee = entityManager.merge(employee);
            }
            entityManager.getTransaction().commit(); //uncomment if not using @Transactional

            return Optional.of(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee != null ? Optional.of(employee) : Optional.empty();
    }

    @Override
    public List<Employee> getEmployeesByExperienceCriteriaQuery(Integer yearsExperience){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        return entityManager.
                createQuery(criteriaQuery.select(employeeRoot)
                .where(criteriaBuilder.greaterThan(employeeRoot.
                        get("yearsExperience"), yearsExperience))).getResultList();
    }

    @Override
    public Optional<Employee> getEmployeeByFullName(String firstName, String lastName){
        TypedQuery<Employee> employeeTypedQuery = entityManager.
                createQuery("SELECT e FROM Employee as e WHERE " +
                        "e.fName = :firstName AND e.lName = :lastName", Employee.class);
        employeeTypedQuery.setParameter("firstName", firstName);
        employeeTypedQuery.setParameter("lastName", lastName);

        Employee employee = employeeTypedQuery.getSingleResult();
        return employee != null ? Optional.of(employee) : Optional.empty();
    }

    @Override
    public List<Employee> getEmployeeByLastName(String lastName) {
        TypedQuery<Employee> employeeTypedQuery = entityManager.createQuery(
                "SELECT e FROM Employee as e WHERE e.lName = :lastName", Employee.class);
        employeeTypedQuery.setParameter("lastName", lastName);

        return employeeTypedQuery.getResultList();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        entityManager.getTransaction().begin(); //uncomment if not using @Transactional

        if (entityManager.contains(employee)) {
            entityManager.remove(employee);
        } else {
            entityManager.merge(employee);
        }

        entityManager.getTransaction().commit(); //uncomment if not using @Transactional
    }

}
