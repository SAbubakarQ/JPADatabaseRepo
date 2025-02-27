package com.linkedinlearning.jpa;

import com.linkedinlearning.jpa.entity.Company;
import com.linkedinlearning.jpa.entity.Employee;
import com.linkedinlearning.jpa.entity.Salary;
import com.linkedinlearning.jpa.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Entity Factory
        EntityManagerFactory entityManagerFactory = Persistence.
                createEntityManagerFactory("default");

        // EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Initilize three classes
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);
        CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl(entityManager);
        SalaryRepositoryImpl salaryRepository = new SalaryRepositoryImpl(entityManager);

        // SABQ Database Main Program
        boolean active = true;
        Scanner myObj = new Scanner(System.in);

        while(active) {

            int userChoice = mainMenu(myObj);

            if (userChoice == 0) {
                System.out.println("Ending Program, Goodbye.");
                active = false;
            } else if (userChoice == 1) {
                // Create Database Entry
                int tableChoice = dataBaseChoice(myObj);

                if (tableChoice == 1) {
                    System.out.println("Creating Employee Database Entry...");
                    // Enter Employee data
                    System.out.println("Enter first name: ");
                    String firstName = myObj.nextLine();
                    System.out.println("Enter last name: ");
                    String lastName = myObj.nextLine();
                    System.out.println("Enter years of experience: ");
                    int yearsOfXp = Integer.parseInt(myObj.nextLine());
                    System.out.println("Enter total compensation: ");
                    double totalComp = Double.parseDouble(myObj.nextLine());
                    Employee userEnteredEmployee = createEmployeeDatabaseEntry(lastName, firstName, yearsOfXp, totalComp);

                    employeeRepository.save(userEnteredEmployee);
                    System.out.println("Created, click Enter to continue...");

                } else if (tableChoice == 2) {
                    System.out.println("Creating Company Database Entry...");
                    // Enter Company data
                    System.out.println("Enter Company Name: ");
                    String compName = myObj.nextLine();
                    System.out.println("Enter Company City: ");
                    String compCity = myObj.nextLine();
                    System.out.println("Enter Company State: ");
                    String compState = myObj.nextLine();
                    System.out.println("Enter Company Zip: ");
                    String compZip = myObj.nextLine();
                    System.out.println("Enter Company Country: ");
                    String compCountry = myObj.nextLine();
                    Company userEnteredCompany = createCompanyDatabaseEntry(compName, compCity, compState, compZip, compCountry);

                    companyRepository.save(userEnteredCompany);
                    System.out.println("Created, click Enter to continue...");

                } else if (tableChoice == 3) {
                    System.out.println("Creating Salary Database Entry...");
                    // Enter Salary data
                    System.out.println("Enter Salary Level: ");
                    int level = Integer.parseInt(myObj.nextLine());
                    System.out.println("Enter Bonus Percentage: ");
                    int bonusPerc = Integer.parseInt(myObj.nextLine());
                    System.out.println("Enter Starting Salary: ");
                    double startSalary = Double.parseDouble(myObj.nextLine());
                    System.out.println("Enter Current Salary: ");
                    double currentSalary = Double.parseDouble(myObj.nextLine());
                    System.out.println("Enter Title: ");
                    String title = myObj.nextLine();
                    Salary userEnteredSalary = createSalaryDatabaseEntry(level, bonusPerc, startSalary, currentSalary, title);

                    salaryRepository.save(userEnteredSalary);
                    System.out.println("Created, click Enter to continue...");

                } else {
                    System.out.println("Not an option.");
                }

                myObj.nextLine();

            } else if (userChoice == 2) {
                // Delete Database Entry
                int tableChoice = dataBaseChoice(myObj);

                if (tableChoice == 1) {
                    System.out.println("Enter Employee ID to delete: ");
                    Long id = Long.parseLong(myObj.nextLine());
                    Optional<Employee> employeeToDelete = employeeRepository.getEmployeeById(id);
                    employeeRepository.deleteEmployee(employeeToDelete.get());
                    System.out.println("Deleted, click Enter to continue...");
                } else if (tableChoice == 2) {
                    System.out.println("Enter Company ID to delete: ");
                    Long id = Long.parseLong(myObj.nextLine());
                    Optional<Company> companyToDelete = companyRepository.getCompanyById(id);
                    companyRepository.deleteCompany(companyToDelete.get());
                    System.out.println("Deleted, click Enter to continue...");
                } else if (tableChoice == 3) {
                    System.out.println("Enter Salary ID to delete: ");
                    Long id = Long.parseLong(myObj.nextLine());
                    Optional<Salary> salaryToDelete = salaryRepository.getSalaryById(id);
                    salaryRepository.deleteSalary(salaryToDelete.get());
                    System.out.println("Deleted, click Enter to continue...");
                } else {
                    System.out.println("Not an option.");
                }

                myObj.nextLine();

            } else if (userChoice == 3){
                // Update Database Entry
                int tableChoice = dataBaseChoice(myObj);

                if (tableChoice == 1){
                    // Update Employee
                    updateEmployeeEntry(employeeRepository, myObj);
                    System.out.println("Updated, click Enter to continue...");
                } else if (tableChoice == 2) {
                    // update Company
                    updateCompanyEntry(companyRepository, myObj);
                    System.out.println("Updated, click Enter to continue...");
                } else if (tableChoice == 3) {
                    // update Salary
                    updateSalaryEntry(salaryRepository, myObj);
                    System.out.println("Updated, click Enter to continue...");
                } else {
                    System.out.println("Not an option.");
                }
                myObj.nextLine();

            } else if (userChoice == 4){
                // Print Table Entries
                int tableChoice = dataBaseChoice(myObj);

                if (tableChoice == 1){
                    System.out.println("Printing All Employees...");
                    printAllUsers(entityManager);
                } else if (tableChoice == 2){
                    System.out.println("Printing All Companies...");
                    printAllCompanies(entityManager);
                } else if (tableChoice == 3){
                    System.out.println("Printing All Salaries...");
                    printAllSalaries(entityManager);
                } else {
                    System.out.println("Not an option.");
                }

                System.out.println("- End of list -");
                System.out.println("Click Enter to continue...");
                myObj.nextLine();
            } else if (userChoice == 5){

                int tableChoice = dataBaseChoice(myObj);

                if (tableChoice == 1){
                    // Employee Search
                    searchForEmployee(employeeRepository, myObj);
                    System.out.println("Click Enter to continue...");
                    myObj.nextLine();
                } else if (tableChoice == 2) {
                    // Company Search
                    searchForCompany(companyRepository, myObj);
                    System.out.println("Click Enter to continue...");
                    myObj.nextLine();
                } else if (tableChoice == 3) {
                    // Salary Search
                    searchForSalaryByMinSalary(salaryRepository, myObj);
                    System.out.println("Click Enter to continue...");
                    myObj.nextLine();
                } else {
                    System.out.println("Not an option.");
                }


            } else {
                System.out.println("TRY AGAIN!");
                myObj.nextLine();
            }
        }

        entityManager.close();
        entityManagerFactory.close();
    }

    public static Employee createEmployeeDatabaseEntry(String lastName, String firstName, int yearsXp, double totalComp ){
        Employee employee = new Employee();
        employee.setlName(lastName);
        employee.setfName(firstName);
        employee.setYearsExperience(yearsXp);
        employee.setTotalCompensation(totalComp);
        return employee;
    }

    public static Salary createSalaryDatabaseEntry(int level, int bonusPerc, double startSalary, double currentSalary, String title){
        Salary salary = new Salary();
        salary.setActiveFlag(true);
        salary.setLevel(level);
        salary.setBonusPercentage(bonusPerc);
        salary.setTitle(title);
        salary.setCurrentSalary(currentSalary);
        salary.setStartingSalary(startSalary);

        return salary;
    }

    public static Company createCompanyDatabaseEntry(String compName, String compCity, String compState, String compZip, String compCountry){
        Company company = new Company();
        company.setName(compName);
        company.setCity(compCity);
        company.setState(compState);
        company.setZipcode(compZip);
        company.setCountry(compCountry);
        return company;
    }

    public static int mainMenu(Scanner myObj) {

        System.out.println("\n==== MAIN MENU ====");
        System.out.println("\t0. End Program");
        System.out.println("\t1. Create Database Entry");
        System.out.println("\t2. Delete Database Entry");
        System.out.println("\t3. Update Database Entry");
        System.out.println("\t4. Print Table Entries");
        System.out.println("\t5. Search Table Entries");
        System.out.println("Enter choice: ");
        return Integer.parseInt(myObj.nextLine());
    }

    public static int dataBaseChoice(Scanner myObj){

        System.out.println("++ TABLES ++ ");
        System.out.println("\t1. Employee");
        System.out.println("\t2. Company");
        System.out.println("\t3. Salary");
        System.out.println("Enter choice: ");
        return Integer.parseInt(myObj.nextLine());
    }

    public static void printAllUsers(EntityManager em){
        List<Employee> allEntries = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        for (Employee e : allEntries) {
            System.out.println(e.getId() + ", " + e.getfName() + ", " + e.getlName() + ", " + e.getYearsExperience() + ", " + e.getTotalCompensation());
        }
    }

    public static void printAllCompanies(EntityManager em){
        List<Company> allEntries = em.createQuery("SELECT c FROM Company c", Company.class).getResultList();

        for (Company c : allEntries) {
            System.out.println(c.getId() + ", " + c.getName() + ", " + c.getCity() + ", " + c.getState() + ", " + c.getZipcode() + ", " + c.getCountry());
        }
    }

    public static void printAllSalaries(EntityManager em){
        List<Salary> allEntries = em.createQuery("SELECT s FROM Salary s", Salary.class).getResultList();

        for (Salary s : allEntries) {
            System.out.println(s.getId() + ", " + s.getCurrentSalary() + ", " + s.getLevel() + ", " + s.getTitle());
        }
    }

    public static Optional<Company> getCompanyByName(CompanyRepositoryImpl companyRepository, String name){
        return companyRepository.getCompanyByName(name);
    }

    public static void updateEmployeeEntry(EmployeeRepository employeeRepository, Scanner myObj){

        System.out.println("Enter Employee ID to update: ");
        Long id = Long.parseLong(myObj.nextLine());

        Optional<Employee> retrievedEmployee = employeeRepository.
                getEmployeeById(Long.valueOf(id));

        System.out.println("Which field would you like to update?");
        System.out.println("\tY - Years of Experience");
        System.out.println("\tT - Total Compensation");
        System.out.println("\tF - First Name");
        System.out.println("\tL - Last Name");
        System.out.println("Enter choice: ");
        String userChoice = myObj.nextLine();

        if (userChoice.equalsIgnoreCase("Y")) {
            System.out.println("Enter new years of experience: ");
            int yearsOfXp = Integer.parseInt(myObj.nextLine());
            retrievedEmployee.get().setYearsExperience(yearsOfXp);
        } else if (userChoice.equalsIgnoreCase("T")) {
            System.out.println("Enter new total compensation: ");
            double totalComp = Double.parseDouble(myObj.nextLine());
            retrievedEmployee.get().setTotalCompensation(totalComp);
        } else if (userChoice.equalsIgnoreCase("F")) {
            System.out.println("Enter new first name: ");
            String firstName = myObj.nextLine();
            retrievedEmployee.get().setfName(firstName);
        } else if (userChoice.equalsIgnoreCase("L")) {
            System.out.println("Enter new last name: ");
            String lastName = myObj.nextLine();
            retrievedEmployee.get().setlName(lastName);
        } else {
            System.out.println("Not an option.");
        }

        employeeRepository.save(retrievedEmployee.get());
    }

    public static void updateCompanyEntry(CompanyRepository companyRepository, Scanner myObj){

            System.out.println("Enter Company Name to Update: ");
            String compName = myObj.nextLine();

            Optional<Company> retrievedCompany =
                    companyRepository.getCompanyByName(compName);

            System.out.println("Which field would you like to update?");
            System.out.println("\tN - Company Name");
            System.out.println("\tC - Company City");
            System.out.println("\tS - Company State");
            System.out.println("\tZ - Company Zip");
            System.out.println("\tCo - Company Country");
            System.out.println("Enter choice: ");
            String userChoice = myObj.nextLine();

            if (userChoice.equalsIgnoreCase("N")) {
                System.out.println("Enter new Company Name: ");
                String companyName = myObj.nextLine();
                retrievedCompany.get().setName(companyName);
            } else if (userChoice.equalsIgnoreCase("C")) {
                System.out.println("Enter new Company City: ");
                String companyCity = myObj.nextLine();
                retrievedCompany.get().setCity(companyCity);
            } else if (userChoice.equalsIgnoreCase("S")) {
                System.out.println("Enter new Company State: ");
                String companyState = myObj.nextLine();
                retrievedCompany.get().setState(companyState);
            } else if (userChoice.equalsIgnoreCase("Z")) {
                System.out.println("Enter new Company Zip: ");
                String companyZip = myObj.nextLine();
                retrievedCompany.get().setZipcode(companyZip);
            } else if (userChoice.equalsIgnoreCase("Co")) {
                System.out.println("Enter new Company Country: ");
                String companyCountry = myObj.nextLine();
                retrievedCompany.get().setCountry(companyCountry);
            } else {
                System.out.println("Not an option.");
            }

            companyRepository.save(retrievedCompany.get());
    }

    public static void updateSalaryEntry(SalaryRepository salaryRepository, Scanner myObj){

        System.out.println("Enter Salary ID to Update: ");
        Long id = Long.parseLong(myObj.nextLine());

        Optional<Salary> retrievedSalary = salaryRepository.getSalaryById(id);

        System.out.println("Which field would you like to update?");
        System.out.println("\tL - Level");
        System.out.println("\tB - Bonus Percentage");
        System.out.println("\tS - Starting Salary");
        System.out.println("\tC - Current Salary");
        System.out.println("\tT - Title");
        System.out.println("Enter choice: ");
        String userChoice = myObj.nextLine();

        if (userChoice.equalsIgnoreCase("L")) {
            System.out.println("Enter new Level: ");
            int level = Integer.parseInt(myObj.nextLine());
            retrievedSalary.get().setLevel(level);
        } else if (userChoice.equalsIgnoreCase("B")) {
            System.out.println("Enter new Bonus Percentage: ");
            int bonusPerc = Integer.parseInt(myObj.nextLine());
            retrievedSalary.get().setBonusPercentage(bonusPerc);
        } else if (userChoice.equalsIgnoreCase("S")) {
            System.out.println("Enter new Starting Salary: ");
            double startSalary = Double.parseDouble(myObj.nextLine());
            retrievedSalary.get().setStartingSalary(startSalary);
        } else if (userChoice.equalsIgnoreCase("C")) {
            System.out.println("Enter new Current Salary: ");
            double currentSalary = Double.parseDouble(myObj.nextLine());
            retrievedSalary.get().setCurrentSalary(currentSalary);
        } else if (userChoice.equalsIgnoreCase("T")) {
            System.out.println("Enter new Title: ");
            String title = myObj.nextLine();
            retrievedSalary.get().setTitle(title);
        } else {
            System.out.println("Not an option.");
        }

        salaryRepository.save(retrievedSalary.get());
    }

    public static void searchForEmployee(EmployeeRepository employeeRepository, Scanner myObj){
        System.out.println("Search By: ");
        System.out.println("\tL - Last Name");
        System.out.println("\tE - Employee ID");
        System.out.println("Enter choice: ");
        String searchChoice = myObj.nextLine();

        if (searchChoice.equalsIgnoreCase("L")) {
            System.out.println("Enter Last Name: ");
            String lastNameSearch = myObj.nextLine();
            employeeRepository.getEmployeeByLastName(lastNameSearch).stream().
                    forEach((e) -> System.out.println(
                            e.getId() + ", " +
                                    e.getfName() + ", " +
                                    e.getlName() + ", " +
                                    e.getYearsExperience() + ", " +
                                    e.getTotalCompensation()));
        } else if (searchChoice.equalsIgnoreCase("E")) {
            System.out.println("Enter Employee ID: ");
            Long id = Long.parseLong(myObj.nextLine());
            Optional<Employee> employee = employeeRepository.getEmployeeById(id);
            System.out.println(employee.get().getId() + ", " + employee.get().getfName() + ", " +
                    employee.get().getlName() + ", " +
                    employee.get().getYearsExperience() + ", " +
                    employee.get().getTotalCompensation());
        } else {
            System.out.println("Not an option.");
        }
    }

    public static void searchForCompany(CompanyRepository companyRepository, Scanner myObj){
            System.out.println("Enter Company Name: ");
            String name = myObj.nextLine();
            companyRepository.getCompanyByName(name).stream().
                    forEach((c) -> System.out.println(
                                            c.getId() + ", " +
                                                    c.getName() + ", " +
                                                    c.getCity() + ", " +
                                                    c.getState() + ", " +
                                                    c.getZipcode() + ", " +
                                                    c.getCountry()));
    }

    public static void searchForSalaryByMinSalary(SalaryRepository salaryRepository, Scanner myObj){
        System.out.println("Enter Minimum Salary: ");
        double minSalary = Double.parseDouble(myObj.nextLine());
        salaryRepository.getSalariesByAmountCriteriaQuery(minSalary).stream().
                forEach((s) -> System.out.println(
                        s.getId() + ", " +
                                s.getTitle() + ", " +
                                s.getCurrentSalary()));

    }

}

