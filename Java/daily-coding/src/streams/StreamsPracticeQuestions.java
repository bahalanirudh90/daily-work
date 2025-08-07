package streams;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * JAVA STREAMS PRACTICE QUESTIONS
 * 
 * Instructions:
 * 1. Solve each question step by step
 * 2. Try to use different Stream operations
 * 3. Test your solutions with the provided data
 * 4. Focus on understanding intermediate vs terminal operations
 */

public class StreamsPracticeQuestions {
    
    // Sample data classes
    static class Employee {
        private String name;
        private String department;
        private double salary;
        private int age;
        private String city;
        
        public Employee(String name, String department, double salary, int age, String city) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.age = age;
            this.city = city;
        }
        
        // Getters
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
        public int getAge() { return age; }
        public String getCity() { return city; }
        
        @Override
        public String toString() {
            return String.format("Employee{name='%s', dept='%s', salary=%.0f, age=%d, city='%s'}", 
                               name, department, salary, age, city);
        }
    }
    
    static class Product {
        private String name;
        private String category;
        private double price;
        private int quantity;
        private boolean inStock;
        
        public Product(String name, String category, double price, int quantity, boolean inStock) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.quantity = quantity;
            this.inStock = inStock;
        }
        
        // Getters
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }
        public boolean isInStock() { return inStock; }
        
        @Override
        public String toString() {
            return String.format("Product{name='%s', category='%s', price=%.2f, qty=%d, inStock=%s}", 
                               name, category, price, quantity, inStock);
        }
    }
    
    public static void main(String[] args) {
        // Sample Data
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 75000, 28, "New York"),
            new Employee("Bob", "HR", 55000, 32, "Chicago"),
            new Employee("Charlie", "IT", 85000, 26, "New York"),
            new Employee("Diana", "Finance", 70000, 30, "Boston"),
            new Employee("Eve", "HR", 60000, 25, "Chicago"),
            new Employee("Frank", "IT", 95000, 35, "San Francisco"),
            new Employee("Grace", "Finance", 80000, 29, "Boston"),
            new Employee("Henry", "Marketing", 65000, 31, "New York")
        );
        
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 999.99, 50, true),
            new Product("Mouse", "Electronics", 25.99, 100, true),
            new Product("Desk", "Furniture", 299.99, 25, false),
            new Product("Chair", "Furniture", 199.99, 30, true),
            new Product("Phone", "Electronics", 699.99, 75, true),
            new Product("Table", "Furniture", 399.99, 15, true),
            new Product("Headphones", "Electronics", 149.99, 0, false),
            new Product("Lamp", "Furniture", 79.99, 40, true)
        );
        
        List<String> words = Arrays.asList("java", "streams", "functional", "programming", 
                                         "lambda", "filter", "map", "collect", "reduce");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 25, 30);
        
        System.out.println("=== JAVA STREAMS PRACTICE QUESTIONS ===\n");
        
        // Call practice methods (uncomment as you solve them)
        // basicFilteringQuestions(employees, products, numbers);
        // mappingQuestions(employees, products, words);
        // sortingQuestions(employees, products);
        // groupingQuestions(employees, products);
        // reductionQuestions(employees, products, numbers);
        // advancedQuestions(employees, products);
        // challengeQuestions(employees, products, numbers);
    }
    
    // ================ BASIC FILTERING QUESTIONS ================
    public static void basicFilteringQuestions(List<Employee> employees, List<Product> products, List<Integer> numbers) {
        System.out.println("=== BASIC FILTERING QUESTIONS ===");
        
        // Q1: Find all employees with salary > 70000
        System.out.println("Q1: Employees with salary > 70000");
        // YOUR CODE HERE
        
        // Q2: Find all products that are in stock
        System.out.println("\nQ2: Products that are in stock");
        // YOUR CODE HERE
        
        // Q3: Find all even numbers from the numbers list
        System.out.println("\nQ3: Even numbers");
        // YOUR CODE HERE
        
        // Q4: Find employees in IT department aged < 30
        System.out.println("\nQ4: IT employees aged < 30");
        // YOUR CODE HERE
        
        // Q5: Find products with price between 100 and 500
        System.out.println("\nQ5: Products with price between 100-500");
        // YOUR CODE HERE
    }
    
    // ================ MAPPING QUESTIONS ================
    public static void mappingQuestions(List<Employee> employees, List<Product> products, List<String> words) {
        System.out.println("\n=== MAPPING QUESTIONS ===");
        
        // Q6: Get list of all employee names
        System.out.println("Q6: All employee names");
        // YOUR CODE HERE
        
        // Q7: Get list of all product categories (unique)
        System.out.println("\nQ7: Unique product categories");
        // YOUR CODE HERE
        
        // Q8: Convert all words to uppercase
        System.out.println("\nQ8: Words in uppercase");
        // YOUR CODE HERE
        
        // Q9: Get employee names and their salaries as "Name: $Salary" format
        System.out.println("\nQ9: Employee name and salary format");
        // YOUR CODE HERE
        
        // Q10: Get total value of each product (price * quantity)
        System.out.println("\nQ10: Total value per product");
        // YOUR CODE HERE
    }
    
    // ================ SORTING QUESTIONS ================
    public static void sortingQuestions(List<Employee> employees, List<Product> products) {
        System.out.println("\n=== SORTING QUESTIONS ===");
        
        // Q11: Sort employees by salary (ascending)
        System.out.println("Q11: Employees sorted by salary (asc)");
        // YOUR CODE HERE
        
        // Q12: Sort products by price (descending)
        System.out.println("\nQ12: Products sorted by price (desc)");
        // YOUR CODE HERE
        
        // Q13: Sort employees by age, then by salary
        System.out.println("\nQ13: Employees sorted by age, then salary");
        // YOUR CODE HERE
        
        // Q14: Sort employee names alphabetically
        System.out.println("\nQ14: Employee names sorted alphabetically");
        // YOUR CODE HERE
    }
    
    // ================ GROUPING QUESTIONS ================
    public static void groupingQuestions(List<Employee> employees, List<Product> products) {
        System.out.println("\n=== GROUPING QUESTIONS ===");
        
        // Q15: Group employees by department
        System.out.println("Q15: Employees grouped by department");
        // YOUR CODE HERE
        
        // Q16: Group products by category with count
        System.out.println("\nQ16: Products grouped by category with count");
        // YOUR CODE HERE
        
        // Q17: Group employees by city and get average salary per city
        System.out.println("\nQ17: Average salary by city");
        // YOUR CODE HERE
        
        // Q18: Group products by inStock status
        System.out.println("\nQ18: Products grouped by stock status");
        // YOUR CODE HERE
    }
    
    // ================ REDUCTION QUESTIONS ================
    public static void reductionQuestions(List<Employee> employees, List<Product> products, List<Integer> numbers) {
        System.out.println("\n=== REDUCTION QUESTIONS ===");
        
        // Q19: Find total salary of all employees
        System.out.println("Q19: Total salary of all employees");
        // YOUR CODE HERE
        
        // Q20: Find average age of employees
        System.out.println("\nQ20: Average age of employees");
        // YOUR CODE HERE
        
        // Q21: Find sum of all numbers
        System.out.println("\nQ21: Sum of all numbers");
        // YOUR CODE HERE
        
        // Q22: Find employee with highest salary
        System.out.println("\nQ22: Employee with highest salary");
        // YOUR CODE HERE
        
        // Q23: Find cheapest product in stock
        System.out.println("\nQ23: Cheapest product in stock");
        // YOUR CODE HERE
        
        // Q24: Count employees in each department
        System.out.println("\nQ24: Count of employees per department");
        // YOUR CODE HERE
    }
    
    // ================ ADVANCED QUESTIONS ================
    public static void advancedQuestions(List<Employee> employees, List<Product> products) {
        System.out.println("\n=== ADVANCED QUESTIONS ===");
        
        // Q25: Find top 3 highest paid employees
        System.out.println("Q25: Top 3 highest paid employees");
        // YOUR CODE HERE
        
        // Q26: Check if any employee earns more than 90000
        System.out.println("\nQ26: Any employee earns > 90000?");
        // YOUR CODE HERE
        
        // Q27: Check if all products have positive price
        System.out.println("\nQ27: All products have positive price?");
        // YOUR CODE HERE
        
        // Q28: Find departments with average salary > 70000
        System.out.println("\nQ28: Departments with avg salary > 70000");
        // YOUR CODE HERE
        
        // Q29: Get employee names from IT department, sorted by salary, as comma-separated string
        System.out.println("\nQ29: IT employee names as comma-separated string");
        // YOUR CODE HERE
        
        // Q30: Find products that are out of stock or have quantity < 20
        System.out.println("\nQ30: Products out of stock or quantity < 20");
        // YOUR CODE HERE
    }
    
    // ================ CHALLENGE QUESTIONS ================
    public static void challengeQuestions(List<Employee> employees, List<Product> products, List<Integer> numbers) {
        System.out.println("\n=== CHALLENGE QUESTIONS ===");
        
        // Q31: Create a Map of department -> list of employee names
        System.out.println("Q31: Department to employee names mapping");
        // YOUR CODE HERE
        
        // Q32: Find the second highest salary
        System.out.println("\nQ32: Second highest salary");
        // YOUR CODE HERE
        
        // Q33: Get statistics (count, sum, avg, min, max) for employee salaries
        System.out.println("\nQ33: Salary statistics");
        // YOUR CODE HERE
        
        // Q34: Partition employees into high earners (>75000) and others
        System.out.println("\nQ34: Partition employees by salary");
        // YOUR CODE HERE
        
        // Q35: Find products where name length > 5 and price < 200
        System.out.println("\nQ35: Products with name length > 5 and price < 200");
        // YOUR CODE HERE
        
        // Q36: Create a nested map: city -> department -> list of employees
        System.out.println("\nQ36: Nested grouping: city -> department -> employees");
        // YOUR CODE HERE
        
        // Q37: Find fibonacci numbers from the numbers list
        System.out.println("\nQ37: Fibonacci numbers from the list");
        // YOUR CODE HERE
        
        // Q38: Custom collector: Join employee names with their departments
        System.out.println("\nQ38: Join names with departments");
        // YOUR CODE HERE
    }
}
