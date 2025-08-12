-- =================================================================================
-- == QUERY VISUALIZATION: Finding 2nd Highest Salary
-- =================================================================================
-- Query: SELECT * FROM Worker as w1 WHERE 2 = ( SELECT COUNT(w2.SALARY) FROM Worker as w2 WHERE w2.SALARY>w1.SALARY);
-- This finds the worker with the 2nd highest salary using a correlated subquery.
-- =================================================================================

USE ORG;

-- First, let's see all workers ordered by salary to understand the data
SELECT WORKER_ID, FIRST_NAME, LAST_NAME, SALARY 
FROM Worker 
ORDER BY SALARY DESC;

-- Expected output:
-- WORKER_ID | FIRST_NAME | LAST_NAME | SALARY
-- 4         | Amitabh    | Singh     | 500000  <- 1st highest (tied)
-- 5         | Vivek      | Bhati     | 500000  <- 1st highest (tied)
-- 3         | Vishal     | Singhal   | 300000  <- 2nd highest
-- 6         | Vipul      | Diwan     | 200000  <- 3rd highest
-- 1         | Monika     | Arora     | 100000  <- 4th highest
-- 8         | Geetika    | Chauhan   | 90000   <- 5th highest
-- 2         | Niharika   | Verma     | 80000   <- 6th highest
-- 7         | Satish     | Kumar     | 75000   <- 7th highest

-- =================================================================================
-- == STEP-BY-STEP VISUALIZATION
-- =================================================================================

-- Let's manually trace through each worker to see how the subquery works:

-- For WORKER_ID = 4 (Amitabh, Salary = 500000):
SELECT 'Processing Amitabh (500000)' AS processing_worker;
SELECT COUNT(w2.SALARY) AS salaries_higher_than_amitabh
FROM Worker as w2 
WHERE w2.SALARY > 500000;
-- Result: 0 (no salaries higher than 500000)
-- Since 2 ≠ 0, Amitabh is NOT selected

-- For WORKER_ID = 5 (Vivek, Salary = 500000):
SELECT 'Processing Vivek (500000)' AS processing_worker;
SELECT COUNT(w2.SALARY) AS salaries_higher_than_vivek
FROM Worker as w2 
WHERE w2.SALARY > 500000;
-- Result: 0 (no salaries higher than 500000)
-- Since 2 ≠ 0, Vivek is NOT selected

-- For WORKER_ID = 3 (Vishal, Salary = 300000):
SELECT 'Processing Vishal (300000)' AS processing_worker;
SELECT COUNT(w2.SALARY) AS salaries_higher_than_vishal
FROM Worker as w2 
WHERE w2.SALARY > 300000;
-- Result: 2 (Amitabh and Vivek both have 500000)
-- Since 2 = 2, Vishal IS selected! ✓

-- For WORKER_ID = 6 (Vipul, Salary = 200000):
SELECT 'Processing Vipul (200000)' AS processing_worker;
SELECT COUNT(w2.SALARY) AS salaries_higher_than_vipul
FROM Worker as w2 
WHERE w2.SALARY > 200000;
-- Result: 3 (Amitabh: 500000, Vivek: 500000, Vishal: 300000)
-- Since 2 ≠ 3, Vipul is NOT selected

-- For WORKER_ID = 1 (Monika, Salary = 100000):
SELECT 'Processing Monika (100000)' AS processing_worker;
SELECT COUNT(w2.SALARY) AS salaries_higher_than_monika
FROM Worker as w2 
WHERE w2.SALARY > 100000;
-- Result: 4 (Amitabh, Vivek, Vishal, Vipul)
-- Since 2 ≠ 4, Monika is NOT selected

-- And so on for the remaining workers...

-- =================================================================================
-- == DETAILED BREAKDOWN FOR EACH WORKER
-- =================================================================================

-- Let's create a comprehensive view showing the logic for each worker:
SELECT 
    w1.WORKER_ID,
    w1.FIRST_NAME,
    w1.LAST_NAME,
    w1.SALARY as current_salary,
    (SELECT COUNT(w2.SALARY) FROM Worker as w2 WHERE w2.SALARY > w1.SALARY) as higher_salaries_count,
    CASE 
        WHEN (SELECT COUNT(w2.SALARY) FROM Worker as w2 WHERE w2.SALARY > w1.SALARY) = 2 
        THEN 'SELECTED (2nd highest)' 
        ELSE 'Not selected' 
    END as selection_status
FROM Worker as w1
ORDER BY w1.SALARY DESC;

-- =================================================================================
-- == THE ACTUAL QUERY RESULT
-- =================================================================================

-- Now let's run the actual query:
SELECT * FROM Worker as w1 
WHERE 2 = (SELECT COUNT(w2.SALARY) FROM Worker as w2 WHERE w2.SALARY > w1.SALARY);

-- =================================================================================
-- == VISUAL REPRESENTATION
-- =================================================================================

/*
SALARY HIERARCHY:
┌─────────────────────────────────────────────────┐
│ 500000 (Amitabh) ──┐                          │
│ 500000 (Vivek)   ──┤ ← 0 salaries higher      │
│                     │   (1st highest - tied)   │
├─────────────────────┴─────────────────────────────┤
│ 300000 (Vishal) ←─────── 2 salaries higher    │ ← SELECTED!
│                          (2nd highest)         │
├─────────────────────────────────────────────────────┤
│ 200000 (Vipul) ←──────── 3 salaries higher    │
├─────────────────────────────────────────────────────┤
│ 100000 (Monika) ←─────── 4 salaries higher    │
├─────────────────────────────────────────────────────┤
│ 90000 (Geetika) ←─────── 5 salaries higher    │
├─────────────────────────────────────────────────────┤
│ 80000 (Niharika) ←────── 6 salaries higher    │
├─────────────────────────────────────────────────────┤
│ 75000 (Satish) ←──────── 7 salaries higher    │
└─────────────────────────────────────────────────────┘

The query finds workers where exactly 2 other workers have higher salaries.
This identifies the worker(s) with the 2nd highest salary.
*/

-- =================================================================================
-- == ALTERNATIVE APPROACHES
-- =================================================================================

-- Method 1: Using RANK() window function (more efficient)
SELECT WORKER_ID, FIRST_NAME, LAST_NAME, SALARY,
       RANK() OVER (ORDER BY SALARY DESC) as salary_rank
FROM Worker
WHERE SALARY = (
    SELECT DISTINCT SALARY 
    FROM Worker 
    ORDER BY SALARY DESC 
    LIMIT 1 OFFSET 1
);

-- Method 2: Using ROW_NUMBER() to handle ties differently
SELECT WORKER_ID, FIRST_NAME, LAST_NAME, SALARY,
       ROW_NUMBER() OVER (ORDER BY SALARY DESC) as row_num
FROM Worker
WHERE ROW_NUMBER() OVER (ORDER BY SALARY DESC) = 2;

-- Method 3: Using DENSE_RANK() for proper ranking with ties
SELECT * FROM (
    SELECT WORKER_ID, FIRST_NAME, LAST_NAME, SALARY,
           DENSE_RANK() OVER (ORDER BY SALARY DESC) as dense_rank
    FROM Worker
) ranked_workers
WHERE dense_rank = 2;
