package com.linkedinlearning.jpa.repository;

import com.linkedinlearning.jpa.entity.Salary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class
SalaryRepositoryImpl implements SalaryRepository {
    EntityManager entityManager;

    public SalaryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Salary> save(Salary salary) {
        try {
            entityManager.getTransaction().begin();
            if (salary.getId() == null) {
                entityManager.persist(salary);
            } else {
                salary = entityManager.merge(salary);
            }
            entityManager.getTransaction().commit();

            return Optional.of(salary);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Salary> getSalaryById(Long id) {
        Salary salary = entityManager.find(Salary.class, id);
        return salary != null ? Optional.of(salary) : Optional.empty();
    }

    @Override
    public List<Salary> getSalariesByAmountCriteriaQuery(Double minSalary){
        Query jplQuery = entityManager
                .createQuery("SELECT s FROM Salary as s WHERE s.currentSalary > :minSalary ORDER BY s.title", Salary.class);
        jplQuery.setParameter("minSalary", minSalary);
        List<Salary> salariesList = jplQuery.getResultList();
        return salariesList;

    }

    @Override
    public void deleteSalary(Salary salary) {
        entityManager.getTransaction().begin(); //uncomment if not using @Transactional

        if (entityManager.contains(salary)) {
            entityManager.remove(salary);
        } else {
            entityManager.merge(salary);
        }

        entityManager.getTransaction().commit(); //uncomment if not using @Transactional
    }
}
