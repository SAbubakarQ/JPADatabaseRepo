package com.linkedinlearning.jpa;

import com.linkedinlearning.jpa.entity.Employee;
import com.linkedinlearning.jpa.entity.Salary;
import com.linkedinlearning.jpa.entity.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void testCreateEmployeeDatabaseEntry() {
        Employee employee = main.createEmployeeDatabaseEntry("Doe", "John", 10, 100000.00);
        assertNotNull(employee);
        assertEquals("Doe", employee.getlName());
        assertEquals("John", employee.getfName());
        assertEquals(10, employee.getYearsExperience());
        assertEquals(100000.00, employee.getTotalCompensation());
    }

    @Test
    void testCreateSalaryDatabaseEntry() {
        Salary salary = main.createSalaryDatabaseEntry(1, 20, 50000.00, 60000.00, "Developer");
        assertNotNull(salary);
        assertEquals(1, salary.getLevel());
        assertEquals(20, salary.getBonusPercentage());
        assertEquals(50000.00, salary.getStartingSalary());
        assertEquals(60000.00, salary.getCurrentSalary());
        assertEquals("Developer", salary.getTitle());
    }

    @Test
    void testCreateCompanyDatabaseEntry() {
        Company company = main.createCompanyDatabaseEntry("Tech Corp", "New York", "NY", "10001", "USA");
        assertNotNull(company);
        assertEquals("Tech Corp", company.getName());
        assertEquals("New York", company.getCity());
        assertEquals("NY", company.getState());
        assertEquals("10001", company.getZipcode());
        assertEquals("USA", company.getCountry());
    }
}