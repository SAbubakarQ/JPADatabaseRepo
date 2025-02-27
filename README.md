# JPA Database Project

## Main Interface
When starting you will be faced with the following interface: 
```
==== MAIN MENU ====
	0. End Program
	1. Create Database Entry
	2. Delete Database Entry
	3. Update Database Entry
	4. Print Table Entries
	5. Search Table Entries
Enter choice: 
```
From the options above you will be able to perform CRUD operations and also search via database. A brief summary of each option is below. 

### 0. End Program
This will do what it says, it will exit out the main while loop and end the program

### 1. Create Database Entry
Here you will be given options to create an entry in the database for Employee, Company, or Salary table.
The Employee table will take in first name, last name, years of experience, and total compensation. 
The Company table will take in company name, city, state, zip, and country. 
Finally, the Salary table will take in an int salary level, int bonus percentage, double starting salary, and double ending salary, and finally a string title. 

These values will be saved to the corresponding schema wihtin the database.

### 2. Delete Database Entry
This will give you the same options to choose from either Employee, Company, or Salary to delete from. Afterwards, the deletion is only from the entry Id. 

### 3. Update Database
This will prompt you to update from the table. You will find the entry you want to update for the Employee table by searching via Employee ID. Once the Employee id is found, the same entry from creation will be able to be updated. For Company, it will be searched via Company Name. Once the program finds the company based on the company name, you will be prompted to update either the company name, city, state, zip, or country. Finally, the Salary update operation will also only use the Salary Id from the table. 

### 4. Print Table Entries
This will print all values within the table after choosing the correct schema. 

### 5. Search Table Entries
This is where most of the JPA methods are being called. When searching through the Employee table, you will be able to search either by last name or employee id. Once found, it should print out the same data values. The Company table will search via company name only. For the Salary table, since we are dealing with integers, we will be searching for minimum current salary value. Anything greater than the value entered will be printed to the console.


## Conclusion
This is an introduction to JPA with Java. It was my first project using databases. The persistence.xml will also have info on how to setup your database locally. Be sure to run pgAdmin before running the program. 

### Notes 

#### Database Configurations 
- If you want the data to reset after each compile change the line: 
```
<property name="jakarta.persistence.schema-generation.database.action" value="create"/>
```
to 
```
<property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>
```

This will reset the data within the table to a fresh slate. 
