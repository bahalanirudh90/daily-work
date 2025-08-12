-- =================================================================================
-- == SQL PRACTICE SOLUTIONS
-- =================================================================================
-- This script contains solutions to all practice questions from Practice_Questions.sql
-- Make sure to run Script_1.sql first to create the databases and tables.
-- =================================================================================

-- =================================================================================
-- == BASIC QUERIES SOLUTIONS (DATABASE: ORG)
-- =================================================================================
USE ORG;

-- Solution 1: Fetch all workers whose first name contains 'a'
SELECT * FROM Worker WHERE FIRST_NAME LIKE '%a%';

-- Solution 2: Fetch all workers whose salary is between 100000 and 500000
SELECT * FROM Worker WHERE SALARY BETWEEN 100000 AND 500000;

-- Solution 3: Fetch all workers from HR department, ordered by salary descending
SELECT * FROM Worker WHERE DEPARTMENT = 'HR' ORDER BY SALARY DESC;

-- Solution 4: Fetch the top 3 highest paid workers
SELECT * FROM Worker ORDER BY SALARY DESC LIMIT 3;

-- Solution 5: Fetch workers whose joining date is in 2014
SELECT * FROM Worker WHERE YEAR(JOINING_DATE) = 2014;

-- =================================================================================
-- == AGGREGATE FUNCTIONS SOLUTIONS (DATABASE: ORG)
-- =================================================================================

-- Solution 6: Find the total salary paid by the organization
SELECT SUM(SALARY) AS total_salary FROM Worker;

-- Solution 7: Find the average salary of workers in each department
SELECT DEPARTMENT, AVG(SALARY) AS avg_salary FROM Worker GROUP BY DEPARTMENT;

-- Solution 8: Find the department with the highest number of workers
SELECT DEPARTMENT, COUNT(*) AS worker_count 
FROM Worker 
GROUP BY DEPARTMENT 
ORDER BY worker_count DESC 
LIMIT 1;

-- Solution 9: Find the second highest salary in the organization
SELECT DISTINCT SALARY FROM Worker ORDER BY SALARY DESC LIMIT 1 OFFSET 1;
-- Alternative approach:
SELECT MAX(SALARY) AS second_highest 
FROM Worker 
WHERE SALARY < (SELECT MAX(SALARY) FROM Worker);

-- Solution 10: Find workers who earn more than the average salary
SELECT * FROM Worker WHERE SALARY > (SELECT AVG(SALARY) FROM Worker);

-- =================================================================================
-- == JOIN QUERIES SOLUTIONS (DATABASE: ORG)
-- =================================================================================

-- Solution 11: Fetch worker names along with their bonus amounts
SELECT w.FIRST_NAME, w.LAST_NAME, b.BONUS_AMOUNT 
FROM Worker w 
INNER JOIN Bonus b ON w.WORKER_ID = b.WORKER_REF_ID;

-- Solution 12: Fetch worker names along with their titles
SELECT w.FIRST_NAME, w.LAST_NAME, t.WORKER_TITLE 
FROM Worker w 
INNER JOIN Title t ON w.WORKER_ID = t.WORKER_REF_ID;

-- Solution 13: Fetch workers who have received bonuses along with their total bonus amount
SELECT w.FIRST_NAME, w.LAST_NAME, SUM(b.BONUS_AMOUNT) AS total_bonus 
FROM Worker w 
INNER JOIN Bonus b ON w.WORKER_ID = b.WORKER_REF_ID 
GROUP BY w.WORKER_ID, w.FIRST_NAME, w.LAST_NAME;

-- Solution 14: Fetch workers who have never received any bonus
SELECT w.* FROM Worker w 
LEFT JOIN Bonus b ON w.WORKER_ID = b.WORKER_REF_ID 
WHERE b.WORKER_REF_ID IS NULL;

-- Solution 15: Fetch the highest bonus received by workers in each department
SELECT w.DEPARTMENT, MAX(b.BONUS_AMOUNT) AS highest_bonus 
FROM Worker w 
INNER JOIN Bonus b ON w.WORKER_ID = b.WORKER_REF_ID 
GROUP BY w.DEPARTMENT;

-- =================================================================================
-- == CUSTOMER QUERIES SOLUTIONS (DATABASE: temp)
-- =================================================================================
USE temp;

-- Solution 16: Fetch all customers from Jalandhar
SELECT * FROM Customer WHERE City = 'Jalandhar';

-- Solution 17: Fetch all female customers
SELECT * FROM Customer WHERE Gender = 'F';

-- Solution 18: Fetch customers whose names start with 'R'
SELECT * FROM Customer WHERE cname LIKE 'R%';

-- Solution 19: Update all customers' pincode to 999999 where city is NULL
UPDATE Customer SET Pincode = 999999 WHERE City IS NULL;

-- Solution 20: Count the number of customers in each city
SELECT City, COUNT(*) AS customer_count FROM Customer GROUP BY City;

-- =================================================================================
-- == ADVANCED QUERIES SOLUTIONS (DATABASE: prac)
-- =================================================================================
USE prac;

-- Solution 21: Fetch employee names who are working on project 'A'
SELECT e.fname, e.lname 
FROM Employees e 
INNER JOIN Project p ON e.id = p.empID 
WHERE p.name = 'A';

-- Solution 22: Fetch all employees along with their client details (LEFT JOIN)
SELECT e.fname, e.lname, c.first_name AS client_fname, c.last_name AS client_lname 
FROM Employees e 
LEFT JOIN Client c ON e.id = c.empID;

-- Solution 23: Fetch employees who are not assigned to any project
SELECT e.* FROM Employees e 
LEFT JOIN Project p ON e.id = p.empID 
WHERE p.empID IS NULL;

-- Solution 24: Fetch the oldest employee from each city
SELECT City, fname, lname, Age 
FROM Employees e1 
WHERE Age = (SELECT MAX(Age) FROM Employees e2 WHERE e1.City = e2.City);

-- Solution 25: Fetch employees whose age is greater than their assigned client's age
SELECT e.fname, e.lname, e.Age AS emp_age, c.age AS client_age 
FROM Employees e 
INNER JOIN Client c ON e.id = c.empID 
WHERE e.Age > c.age;

-- =================================================================================
-- == COMPLEX QUERIES SOLUTIONS
-- =================================================================================

-- Solution 26: (ORG) Fetch workers who have both bonus and title assigned
USE ORG;
SELECT DISTINCT w.* FROM Worker w 
INNER JOIN Bonus b ON w.WORKER_ID = b.WORKER_REF_ID 
INNER JOIN Title t ON w.WORKER_ID = t.WORKER_REF_ID;

-- Solution 27: (prac) Fetch employees who are handling more than one project
USE prac;
SELECT e.fname, e.lname, COUNT(p.id) AS project_count 
FROM Employees e 
INNER JOIN Project p ON e.id = p.empID 
GROUP BY e.id, e.fname, e.lname 
HAVING COUNT(p.id) > 1;

-- Solution 28: (prac) Fetch the project that started earliest
SELECT * FROM Project ORDER BY startdate ASC LIMIT 1;

-- Solution 29: (ORG) Fetch the worker who has received the maximum bonus
USE ORG;
SELECT w.FIRST_NAME, w.LAST_NAME, b.BONUS_AMOUNT 
FROM Worker w 
INNER JOIN Bonus b ON w.WORKER_ID = b.WORKER_REF_ID 
WHERE b.BONUS_AMOUNT = (SELECT MAX(BONUS_AMOUNT) FROM Bonus);

-- Solution 30: (prac) Create a view that shows employee name, project name, and client name
USE prac;
CREATE VIEW EmployeeProjectClient AS
SELECT e.fname AS Employee_Name, p.name AS Project_Name, c.first_name AS Client_Name 
FROM Employees e 
INNER JOIN Project p ON e.id = p.empID 
INNER JOIN Client c ON p.clientID = c.id;

-- To view the created view:
-- SELECT * FROM EmployeeProjectClient;

-- =================================================================================
-- == WINDOW FUNCTIONS SOLUTIONS (Advanced)
-- =================================================================================

-- Solution 31: (ORG) Rank workers by salary within each department
USE ORG;
SELECT FIRST_NAME, LAST_NAME, DEPARTMENT, SALARY,
       RANK() OVER (PARTITION BY DEPARTMENT ORDER BY SALARY DESC) AS salary_rank
FROM Worker;

-- Solution 32: (ORG) Find the cumulative salary by joining date
SELECT FIRST_NAME, LAST_NAME, JOINING_DATE, SALARY,
       SUM(SALARY) OVER (ORDER BY JOINING_DATE) AS cumulative_salary
FROM Worker ORDER BY JOINING_DATE;

-- Solution 33: (prac) Assign row numbers to employees ordered by age
USE prac;
SELECT fname, lname, Age,
       ROW_NUMBER() OVER (ORDER BY Age DESC) AS row_num
FROM Employees;

-- =================================================================================
-- == DATA MODIFICATION SOLUTIONS
-- =================================================================================

-- Solution 34: (temp) Data modification operations
USE temp;
-- a) Add a new customer
INSERT INTO Customer VALUES (999, 'Practice User', 'Sample Address', 'M', 'Sample City', 123456);

-- b) Update this customer's address
UPDATE Customer SET Address = 'Updated Address' WHERE id = 999;

-- c) Delete this customer
DELETE FROM Customer WHERE id = 999;

-- Solution 35: (ORG) Give 10% bonus to all workers in Admin department
USE ORG;
-- First, let's see current Admin workers
SELECT * FROM Worker WHERE DEPARTMENT = 'Admin';

-- Insert bonus records for Admin department workers (10% of their salary)
INSERT INTO Bonus (WORKER_REF_ID, BONUS_AMOUNT, BONUS_DATE)
SELECT WORKER_ID, SALARY * 0.10, NOW() 
FROM Worker 
WHERE DEPARTMENT = 'Admin';

-- =================================================================================
-- == CHALLENGE QUESTIONS SOLUTIONS
-- =================================================================================

-- Solution 36: Find employees who share the same city as their clients
USE prac;
SELECT e.fname, e.lname, e.City AS emp_city, c.first_name, c.last_name, c.City AS client_city 
FROM Employees e 
INNER JOIN Client c ON e.id = c.empID 
WHERE e.City = c.City;

-- Solution 37: Find the worker who has the longest tenure
USE ORG;
SELECT FIRST_NAME, LAST_NAME, JOINING_DATE, 
       DATEDIFF(NOW(), JOINING_DATE) AS tenure_days
FROM Worker 
ORDER BY JOINING_DATE ASC 
LIMIT 1;

-- Solution 38: Find departments where average salary is above overall average
USE ORG;
SELECT DEPARTMENT, AVG(SALARY) AS dept_avg_salary 
FROM Worker 
GROUP BY DEPARTMENT 
HAVING AVG(SALARY) > (SELECT AVG(SALARY) FROM Worker);

-- Solution 39: Create a stored procedure for department-wise worker retrieval
USE ORG;
DELIMITER //
CREATE PROCEDURE GetWorkersByDepartment(IN dept_name VARCHAR(25))
BEGIN
    SELECT * FROM Worker WHERE DEPARTMENT = dept_name;
END //
DELIMITER ;

-- To use the procedure:
-- CALL GetWorkersByDepartment('HR');

-- Solution 40: Find workers who joined in the same month as their bonus date
USE ORG;
SELECT w.FIRST_NAME, w.LAST_NAME, w.JOINING_DATE, b.BONUS_DATE 
FROM Worker w 
INNER JOIN Bonus b ON w.WORKER_ID = b.WORKER_REF_ID 
WHERE MONTH(w.JOINING_DATE) = MONTH(b.BONUS_DATE) 
AND YEAR(w.JOINING_DATE) = YEAR(b.BONUS_DATE);

-- =================================================================================
-- == ADDITIONAL PRACTICE QUERIES
-- =================================================================================

-- Bonus Query 1: Find the department-wise second highest salary
USE ORG;
SELECT DEPARTMENT, SALARY AS second_highest_salary
FROM (
    SELECT DEPARTMENT, SALARY,
           ROW_NUMBER() OVER (PARTITION BY DEPARTMENT ORDER BY SALARY DESC) AS rn
    FROM Worker
) ranked
WHERE rn = 2;

-- Bonus Query 2: Find employees who have clients from the same city but different from employee's city
USE prac;
SELECT e.fname, e.lname, e.City AS emp_city, c.first_name, c.City AS client_city
FROM Employees e 
INNER JOIN Client c ON e.id = c.empID 
WHERE e.City != c.City
AND c.City IN (SELECT DISTINCT City FROM Employees WHERE id != e.id);

-- Bonus Query 3: Create a summary report of ORG database
USE ORG;
SELECT 
    'Total Workers' AS metric, COUNT(*) AS value FROM Worker
UNION ALL
SELECT 
    'Total Departments', COUNT(DISTINCT DEPARTMENT) FROM Worker
UNION ALL
SELECT 
    'Average Salary', ROUND(AVG(SALARY), 2) FROM Worker
UNION ALL
SELECT 
    'Total Bonus Paid', SUM(BONUS_AMOUNT) FROM Bonus;
