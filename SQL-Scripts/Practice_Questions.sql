-- =================================================================================
-- == SQL PRACTICE QUESTIONS
-- =================================================================================
-- This script contains practice questions based on the existing database schemas.
-- Use the tables from Script_1.sql to practice these queries.
-- Solutions are provided in Practice_Solutions.sql
-- =================================================================================

-- Before starting, make sure you have run Script_1.sql to create the databases and tables.

-- =================================================================================
-- == BASIC QUERIES (DATABASE: ORG)
-- =================================================================================
USE ORG;

-- Question 1: Write a query to fetch all workers whose first name contains 'a'.

-- Question 2: Write a query to fetch all workers whose salary is between 100000 and 500000.

-- Question 3: Write a query to fetch all workers from the HR department, ordered by salary in descending order.

-- Question 4: Write a query to fetch the top 3 highest paid workers.

-- Question 5: Write a query to fetch workers whose joining date is in 2014.

-- =================================================================================
-- == AGGREGATE FUNCTIONS (DATABASE: ORG)
-- =================================================================================

-- Question 6: Write a query to find the total salary paid by the organization.

-- Question 7: Write a query to find the average salary of workers in each department.

-- Question 8: Write a query to find the department with the highest number of workers.

-- Question 9: Write a query to find the second highest salary in the organization.

-- Question 10: Write a query to find workers who earn more than the average salary.

-- =================================================================================
-- == JOIN QUERIES (DATABASE: ORG)
-- =================================================================================

-- Question 11: Write a query to fetch worker names along with their bonus amounts.

-- Question 12: Write a query to fetch worker names along with their titles.

-- Question 13: Write a query to fetch workers who have received bonuses along with their total bonus amount.

-- Question 14: Write a query to fetch workers who have never received any bonus.

-- Question 15: Write a query to fetch the highest bonus received by workers in each department.

-- =================================================================================
-- == CUSTOMER QUERIES (DATABASE: temp)
-- =================================================================================
USE temp;

-- Question 16: Write a query to fetch all customers from Jalandhar.

-- Question 17: Write a query to fetch all female customers.

-- Question 18: Write a query to fetch customers whose names start with 'R'.

-- Question 19: Write a query to update all customers' pincode to 999999 where city is NULL.

-- Question 20: Write a query to count the number of customers in each city.

-- =================================================================================
-- == ADVANCED QUERIES (DATABASE: prac)
-- =================================================================================
USE prac;

-- Question 21: Write a query to fetch employee names who are working on project 'A'.

-- Question 22: Write a query to fetch all employees along with their client details (use LEFT JOIN).

-- Question 23: Write a query to fetch employees who are not assigned to any project.

-- Question 24: Write a query to fetch the oldest employee from each city.

-- Question 25: Write a query to fetch employees whose age is greater than their assigned client's age.

-- =================================================================================
-- == COMPLEX QUERIES
-- =================================================================================

-- Question 26: (ORG) Write a query to fetch workers who have both bonus and title assigned.

-- Question 27: (prac) Write a query to fetch employees who are handling more than one project.

-- Question 28: (prac) Write a query to fetch the project that started earliest.

-- Question 29: (ORG) Write a query to fetch the worker who has received the maximum bonus.

-- Question 30: (prac) Write a query to create a view that shows employee name, project name, and client name.

-- =================================================================================
-- == WINDOW FUNCTIONS (Advanced)
-- =================================================================================

-- Question 31: (ORG) Write a query to rank workers by salary within each department.

-- Question 32: (ORG) Write a query to find the cumulative salary by joining date.

-- Question 33: (prac) Write a query to assign row numbers to employees ordered by age.

-- =================================================================================
-- == DATA MODIFICATION
-- =================================================================================

-- Question 34: (temp) Write queries to:
--             a) Add a new customer with id=999, name='Your Name', city='Your City'
--             b) Update this customer's address
--             c) Delete this customer

-- Question 35: (ORG) Write a query to give a 10% bonus to all workers in the 'Admin' department.

-- =================================================================================
-- == CHALLENGE QUESTIONS
-- =================================================================================

-- Question 36: Write a query to find employees (from prac database) who share the same city as their clients.

-- Question 37: Write a query to find the worker (from ORG database) who has the longest tenure in the organization.

-- Question 38: Write a query to find departments (from ORG database) where the average salary is above the overall average salary.

-- Question 39: Create a stored procedure that takes department name as input and returns all workers from that department.

-- Question 40: Write a query to find workers who joined in the same month as their bonus date.
