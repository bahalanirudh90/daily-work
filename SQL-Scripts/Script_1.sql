-- =================================================================================
-- == COMBINED DATABASE SETUP SCRIPT
-- =================================================================================
-- This script contains the schema, data, and example queries for three databases:
-- 1. ORG: An employee management system with Worker, Bonus, and Title tables.
-- 2. temp: A simple customer and order management system.
-- 3. prac: A practice database with Employees, Clients, and Projects.
-- =================================================================================



-- =================================================================================
-- == DATABASE 1: ORG (Employee Management System)
-- =================================================================================

-- ---
-- --- Schema Definition and Data Insertion
-- ---
CREATE DATABASE IF NOT EXISTS ORG;
USE ORG;

-- Create Worker table
CREATE TABLE Worker (
    WORKER_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FIRST_NAME VARCHAR(25),
    LAST_NAME VARCHAR(25),
    SALARY INT,
    JOINING_DATE DATETIME,
    DEPARTMENT VARCHAR(25)
);

-- Insert data into Worker table
INSERT INTO Worker
(WORKER_ID, FIRST_NAME, LAST_NAME, SALARY, JOINING_DATE, DEPARTMENT) VALUES
    (1, 'Monika', 'Arora', 100000, '2014-02-20 09:00:00', 'HR'),
    (2, 'Niharika', 'Verma', 80000, '2014-06-11 09:00:00', 'Admin'),
    (3, 'Vishal', 'Singhal', 300000, '2014-02-20 09:00:00', 'HR'),
    (4, 'Amitabh', 'Singh', 500000, '2014-02-20 09:00:00', 'Admin'),
    (5, 'Vivek', 'Bhati', 500000, '2014-06-11 09:00:00', 'Admin'),
    (6, 'Vipul', 'Diwan', 200000, '2014-06-11 09:00:00', 'Account'),
    (7, 'Satish', 'Kumar', 75000, '2014-01-20 09:00:00', 'Account'),
    (8, 'Geetika', 'Chauhan', 90000, '2014-04-11 09:00:00', 'Admin');

-- Create Bonus table
CREATE TABLE Bonus (
    WORKER_REF_ID INT,
    BONUS_AMOUNT INT,
    BONUS_DATE DATETIME,
    FOREIGN KEY (WORKER_REF_ID) REFERENCES Worker(WORKER_ID) ON DELETE CASCADE
);

-- Insert data into Bonus table
INSERT INTO Bonus
(WORKER_REF_ID, BONUS_AMOUNT, BONUS_DATE) VALUES
    (1, 5000, '2016-02-20 00:00:00'),
    (2, 3000, '2016-06-11 00:00:00'),
    (3, 4000, '2016-02-20 00:00:00'),
    (1, 4500, '2016-02-20 00:00:00'),
    (2, 3500, '2016-06-11 00:00:00');

-- Create Title table
CREATE TABLE Title (
    WORKER_REF_ID INT,
    WORKER_TITLE VARCHAR(25),
    AFFECTED_FROM DATETIME,
    FOREIGN KEY (WORKER_REF_ID) REFERENCES Worker(WORKER_ID) ON DELETE CASCADE
);

-- Insert data into Title table
INSERT INTO Title
(WORKER_REF_ID, WORKER_TITLE, AFFECTED_FROM) VALUES
    (1, 'Manager', '2016-02-20 00:00:00'),
    (2, 'Executive', '2016-06-11 00:00:00'),
    (8, 'Executive', '2016-06-11 00:00:00'),
    (5, 'Manager', '2016-06-11 00:00:00'),
    (4, 'Asst. Manager', '2016-06-11 00:00:00'),
    (7, 'Executive', '2016-06-11 00:00:00'),
    (6, 'Lead', '2016-06-11 00:00:00'),
    (3, 'Lead', '2016-06-11 00:00:00');

-- ---
-- --- Schema Modification Examples (ALTER TABLE)
-- ---
ALTER TABLE Worker ADD GENDER CHAR(1) NOT NULL DEFAULT 'M';
ALTER TABLE Worker MODIFY SALARY INT NOT NULL DEFAULT 0;
ALTER TABLE Worker CHANGE COLUMN GENDER SEX CHAR(1) NOT NULL DEFAULT 'M';
ALTER TABLE Worker DROP COLUMN SEX;
-- ALTER TABLE Worker RENAME TO Employees; -- Renaming example
-- ALTER TABLE Employees RENAME TO Worker; -- Rename back

-- ---
-- --- Query Examples
-- ---
-- Basic selections
SELECT * FROM Worker WHERE SALARY > 300000;
SELECT * FROM Worker WHERE DEPARTMENT = 'HR';

-- Using BETWEEN, OR, and IN
SELECT * FROM Worker WHERE SALARY BETWEEN 80000 AND 300000;
SELECT * FROM Worker WHERE DEPARTMENT = 'HR' OR DEPARTMENT = 'Admin';
SELECT * FROM Worker WHERE DEPARTMENT IN ('HR','Admin','Account');
SELECT * FROM Worker WHERE DEPARTMENT NOT IN ('HR','Admin','Account');

-- Pattern matching
SELECT * FROM Worker WHERE FIRST_NAME LIKE '%ik_';

-- Sorting and distinct values
SELECT * FROM Worker ORDER BY SALARY DESC;
SELECT DISTINCT DEPARTMENT FROM Worker;

-- ---
-- --- Aggregation and Grouping Examples
-- ---
-- Find the number of employees working in different departments
SELECT DEPARTMENT, COUNT(*) AS num_employees FROM Worker GROUP BY DEPARTMENT;

-- Find average, min, and max salary per department
SELECT DEPARTMENT, AVG(SALARY) AS avg_salary FROM Worker GROUP BY DEPARTMENT;
SELECT DEPARTMENT, MIN(SALARY) AS min_salary FROM Worker GROUP BY DEPARTMENT;
SELECT DEPARTMENT, MAX(SALARY) AS max_salary FROM Worker GROUP BY DEPARTMENT;

-- Find departments having more than 2 workers
SELECT DEPARTMENT, COUNT(*) FROM Worker GROUP BY DEPARTMENT HAVING COUNT(*) > 2;




-- =================================================================================
-- == DATABASE 2: temp (Customer and Order Management System)
-- =================================================================================

-- ---
-- --- Schema Definition and Data Insertion
-- ---
CREATE DATABASE IF NOT EXISTS temp;
USE temp;

-- Create Customer table
CREATE TABLE Customer (
    id INTEGER PRIMARY KEY,
    cname VARCHAR(50),
    Address VARCHAR(100),
    Gender CHAR(1),
    City VARCHAR(50),
    Pincode INTEGER
);

-- Insert data into Customer table
INSERT INTO Customer
VALUES (1251, 'Ram Kumar', 'Dilbagh Nagar', 'M', 'Jalandhar', 144002),
       (1300, 'Shayam Singh', 'Ludhiana H.O', 'M', 'Ludhiana', 141001),
       (245, 'Neelabh Shukla', 'Ashok Nagar', 'M', 'Jalandhar', 144003),
       (210, 'Barkha Singh', 'Dilbagh Nagar', 'F', 'Jalandhar', 144002),
       (500, 'Rohan Arora', 'Ludhiana H.O', 'M', 'Ludhiana', 141001),
       (1, 'codehelp', 'delhi', 'M', 'Delhi', 110000),
       (121, 'Bob', NULL, NULL, NULL, NULL);

-- Create Order_details table
CREATE TABLE Order_details (
    Order_id INTEGER,
    delivery_date DATE,
    cust_id INTEGER,
    FOREIGN KEY(cust_id) REFERENCES Customer(id)
);

-- ---
-- --- Data Manipulation Examples (UPDATE, REPLACE)
-- ---
-- Note: Disabling safe updates is generally not recommended in production.
-- SET SQL_SAFE_UPDATES = 0;

UPDATE Customer SET Address='Mumbai', Gender='M' WHERE id = 121;
UPDATE Customer SET Pincode = 110000;
UPDATE Customer SET Pincode = Pincode + 1;

-- REPLACE works like INSERT, but if an old row has the same value in a PRIMARY KEY or UNIQUE index, the old row is deleted first.
REPLACE INTO Customer(id, city) VALUES(1251, 'Colony');
REPLACE INTO Customer SET id = 1300, city = 'New Ludhiana';




-- =================================================================================
-- == DATABASE 3: prac (Practice Database with Employees, Clients, Projects)
-- =================================================================================

-- ---
-- --- Schema Definition and Data Insertion
-- ---
CREATE DATABASE IF NOT EXISTS prac;
USE prac;

-- Create Employees table
CREATE TABLE Employees (
    id INT PRIMARY KEY,
    fname VARCHAR(25),
    lname VARCHAR(25),
    Age INT,
    emailID VARCHAR(50),
    PhoneNo VARCHAR(15),
    City VARCHAR(25)
);

-- Insert data into Employees table
INSERT INTO Employees (id, fname, lname, Age, emailID, PhoneNo, City) VALUES
    (1, 'Aman', 'Proto', 32, 'aman@gmail.com', '898', 'Delhi'),
    (2, 'Yagya', 'Narayan', 44, 'yagya@gmail.com', '222', 'Palam'),
    (3, 'Rahul', 'BD', 22, 'rahul@gmail.com', '444', 'Kolkata'),
    (4, 'Jatin', 'Hermit', 31, 'jatin@gmail.com', '666', 'Raipur'),
    (5, 'PK', 'Pandey', 21, 'pk@gmail.com', '555', 'Jaipur');

-- Create Client table
CREATE TABLE Client (
    id INT PRIMARY KEY,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    age INT,
    emailID VARCHAR(50),
    PhoneNo VARCHAR(15),
    City VARCHAR(25),
    empID INT,
    FOREIGN KEY (empID) REFERENCES Employees(id)
);

-- Insert data into Client table
INSERT INTO Client (id, first_name, last_name, age, emailID, PhoneNo, City, empID) VALUES
    (1, 'Mac', 'Rogers', 47, 'mac@hotmail.com', '333', 'Kolkata', 3),
    (2, 'Max', 'Poirier', 27, 'max@gmail.com', '222', 'Kolkata', 3),
    (3, 'Peter', 'Jain', 24, 'peter@abc.com', '111', 'Delhi', 1),
    (4, 'Sushant', 'Aggarwal', 23, 'sushant@yahoo.com', '45454', 'Hyderabad', 5),
    (5, 'Pratap', 'Singh', 36, 'p@xyz.com', '77767', 'Mumbai', 2);

-- Create Project table
CREATE TABLE Project (
    id INT PRIMARY KEY,
    empID INT,
    name VARCHAR(25),
    startdate DATE,
    clientID INT,
    FOREIGN KEY (empID) REFERENCES Employees(id),
    FOREIGN KEY (clientID) REFERENCES Client(id)
);

-- Insert data into Project table
INSERT INTO Project (id, empID, name, startdate, clientID) VALUES
    (1, 1, 'A', '2021-04-21', 3),
    (2, 2, 'B', '2021-03-12', 1),
    (3, 3, 'C', '2021-01-16', 5),
    (4, 3, 'D', '2021-04-27', 2),
    (5, 5, 'E', '2021-05-01', 4);

-- ---
-- --- JOIN Query Examples
-- ---

-- Enlist all the employee IDs and names along with the project allocated to them (INNER JOIN)
SELECT e.id, e.fname, p.name FROM Employees AS e INNER JOIN Project AS p ON e.id = p.empID;

-- Fetch out all the employee IDs and their contact details who have been working from Jaipur with clients working in Hyderabad
SELECT e.id AS Employee_id, e.PhoneNo AS Employee_Phone, e.emailID AS Employee_email, c.first_name AS Client_Name
FROM Employees AS e INNER JOIN Client as c on e.id = c.empID WHERE e.City = 'Jaipur' AND c.City = 'Hyderabad';

-- Fetch out each project allocated to each employee (LEFT JOIN to include employees without projects)
SELECT e.id, e.fname, e.lname, p.id as project_id, p.name as project_name 
FROM Employees as e LEFT JOIN Project as p ON e.id = p.empID;

-- List out all the projects along with employee's name and their respective allocated email IDs (RIGHT JOIN to include projects without employees)
SELECT e.fname, e.emailID, p.name FROM Employees as e RIGHT JOIN Project as p ON e.id = p.empID;

-- List out all possible combinations of employee names and projects (CROSS JOIN)
SELECT e.fname, e.lname, p.id, p.name FROM Employees as e CROSS JOIN Project as p;

-- ---
-- --- Subquery and Advanced Query Examples
-- ---

-- Employee details with age > 30
SELECT * FROM Employees WHERE age IN (SELECT age from Employees where age > 30);

-- Employees working in more than 1 project
SELECT * FROM Employees WHERE id IN (SELECT empID FROM Project GROUP BY empID HAVING COUNT(empID) > 1);

-- Employee details having age > average age
SELECT * FROM Employees WHERE age > (SELECT avg(age) FROM Employees);

-- Find the 3rd oldest employee (using a correlated subquery)
-- Note: Using DISTINCT handles ties correctly.
SELECT * FROM Employees e1 WHERE 3 = (
    SELECT COUNT(DISTINCT e2.age) FROM Employees e2 WHERE e2.age >= e1.age);

-- Find the 2nd youngest employee
SELECT * FROM Employees e1 WHERE 2 = (
    SELECT COUNT(DISTINCT e2.age) FROM Employees e2 WHERE e2.age <= e1.age);