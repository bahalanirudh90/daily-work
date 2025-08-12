## 🔍 CORRELATED SUBQUERY VISUALIZATION

### Query Analysis:
```sql
SELECT * FROM Worker as w1 
WHERE 2 = (SELECT COUNT(w2.SALARY) FROM Worker as w2 WHERE w2.SALARY > w1.SALARY);
```

---

## 📊 Step-by-Step Execution Flow

### **Phase 1: Outer Query Processing**
The outer query processes each worker (w1) one by one:

```
FOR EACH worker w1 IN Worker table:
    ├── Take w1's salary
    ├── Execute subquery with w1's salary
    ├── Check if subquery result = 2
    └── If TRUE, include w1 in final result
```

---

## 🔄 **Phase 2: Subquery Execution for Each Worker**

### **Worker 1: Amitabh Singh (500,000)**
```
w1.SALARY = 500,000
Subquery: SELECT COUNT(*) FROM Worker w2 WHERE w2.SALARY > 500,000
┌─────────────┬──────────┬─────────────┐
│ Worker      │ Salary   │ > 500,000?  │
├─────────────┼──────────┼─────────────┤
│ Amitabh     │ 500,000  │ ❌ No       │
│ Vivek       │ 500,000  │ ❌ No       │
│ Vishal      │ 300,000  │ ❌ No       │
│ Vipul       │ 200,000  │ ❌ No       │
│ Others...   │ < 200k   │ ❌ No       │
└─────────────┴──────────┴─────────────┘
COUNT = 0
2 = 0? ❌ FALSE → Amitabh NOT selected
```

### **Worker 2: Vivek Bhati (500,000)**
```
w1.SALARY = 500,000
Subquery: SELECT COUNT(*) FROM Worker w2 WHERE w2.SALARY > 500,000
Result: COUNT = 0 (same as Amitabh)
2 = 0? ❌ FALSE → Vivek NOT selected
```

### **Worker 3: Vishal Singhal (300,000)** ⭐
```
w1.SALARY = 300,000
Subquery: SELECT COUNT(*) FROM Worker w2 WHERE w2.SALARY > 300,000
┌─────────────┬──────────┬─────────────┐
│ Worker      │ Salary   │ > 300,000?  │
├─────────────┼──────────┼─────────────┤
│ Amitabh     │ 500,000  │ ✅ Yes      │
│ Vivek       │ 500,000  │ ✅ Yes      │
│ Vishal      │ 300,000  │ ❌ No       │
│ Vipul       │ 200,000  │ ❌ No       │
│ Others...   │ < 200k   │ ❌ No       │
└─────────────┴──────────┴─────────────┘
COUNT = 2
2 = 2? ✅ TRUE → Vishal IS SELECTED!
```

### **Worker 4: Vipul Diwan (200,000)**
```
w1.SALARY = 200,000
Subquery: SELECT COUNT(*) FROM Worker w2 WHERE w2.SALARY > 200,000
┌─────────────┬──────────┬─────────────┐
│ Worker      │ Salary   │ > 200,000?  │
├─────────────┼──────────┼─────────────┤
│ Amitabh     │ 500,000  │ ✅ Yes      │
│ Vivek       │ 500,000  │ ✅ Yes      │
│ Vishal      │ 300,000  │ ✅ Yes      │
│ Vipul       │ 200,000  │ ❌ No       │
│ Others...   │ < 200k   │ ❌ No       │
└─────────────┴──────────┴─────────────┘
COUNT = 3
2 = 3? ❌ FALSE → Vipul NOT selected
```

---

## 🎯 **Final Result Logic**

### **Salary Ranking Visualization:**
```
Rank │ Worker   │ Salary  │ Higher Salaries │ Count=2? │ Selected?
─────┼──────────┼─────────┼─────────────────┼──────────┼──────────
 1   │ Amitabh  │ 500,000 │ [ ]             │    0     │    ❌
 1   │ Vivek    │ 500,000 │ [ ]             │    0     │    ❌
 2   │ Vishal   │ 300,000 │ [Amitabh,Vivek] │    2     │    ✅
 3   │ Vipul    │ 200,000 │ [A,V,Vishal]    │    3     │    ❌
 4   │ Monika   │ 100,000 │ [A,V,Vi,Vip]    │    4     │    ❌
 5   │ Geetika  │  90,000 │ [A,V,Vi,Vip,M]  │    5     │    ❌
 6   │ Niharika │  80,000 │ [...6 workers]  │    6     │    ❌
 7   │ Satish   │  75,000 │ [...7 workers]  │    7     │    ❌
```

---

## 🧠 **Key Insights:**

1. **Correlated Subquery**: For each worker w1, the subquery counts how many workers w2 have higher salaries than w1.

2. **The Magic Number "2"**: We're looking for workers who have exactly 2 workers earning more than them.

3. **Handling Ties**: Since Amitabh and Vivek both earn 500,000 (tied for 1st place), Vishal with 300,000 becomes the 2nd highest unique salary.

4. **Why Vishal is Selected**: Exactly 2 workers (Amitabh and Vivek) earn more than Vishal's 300,000.

---

## ⚡ **Performance Note:**
This correlated subquery approach works but is inefficient for large datasets because:
- The subquery executes once for each row in the outer query
- Time complexity: O(n²) where n = number of workers

**Better alternatives:**
- `RANK()` or `DENSE_RANK()` window functions
- `LIMIT` with `OFFSET`
- `ROW_NUMBER()` for handling ties differently
