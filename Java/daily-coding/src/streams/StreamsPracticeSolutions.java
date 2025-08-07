package streams;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * SOLUTIONS TO JAVA STREAMS PRACTICE QUESTIONS
 * 
 * Note: Try to solve the questions yourself first before looking at these solutions!
 * Each solution demonstrates different Stream operations and techniques.
 */

public class StreamsPracticeSolutions {
    
    // Copy the same data classes from practice questions
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
        // Same sample data as practice questions
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
        
        // Run all solutions
        basicFilteringSolutions(employees, products, numbers);
        mappingSolutions(employees, products, words);
        sortingSolutions(employees, products);
        groupingSolutions(employees, products);
        reductionSolutions(employees, products, numbers);
        advancedSolutions(employees, products);
        challengeSolutions(employees, products, numbers);
    }
    
    // BASIC FILTERING SOLUTIONS
    public static void basicFilteringSolutions(List<Employee> employees, List<Product> products, List<Integer> numbers) {
        System.out.println("=== BASIC FILTERING SOLUTIONS ===");
        
        // Q1: Find all employees with salary > 70000
        System.out.println("Q1: Employees with salary > 70000");
        employees.stream()
            .filter(emp -> emp.getSalary() > 70000)
            .forEach(System.out::println);
        
        // Q2: Find all products that are in stock
        System.out.println("\nQ2: Products that are in stock");
        products.stream()
            .filter(Product::isInStock)
            .forEach(System.out::println);
        
        // Q3: Find all even numbers
        System.out.println("\nQ3: Even numbers");
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .forEach(System.out::println);
        
        // Q4: Find employees in IT department aged < 30
        System.out.println("\nQ4: IT employees aged < 30");
        employees.stream()
            .filter(emp -> "IT".equals(emp.getDepartment()))
            .filter(emp -> emp.getAge() < 30)
            .forEach(System.out::println);
        
        // Q5: Find products with price between 100 and 500
        System.out.println("\nQ5: Products with price between 100-500");
        products.stream()
            .filter(p -> p.getPrice() >= 100 && p.getPrice() <= 500)
            .forEach(System.out::println);
    }
    
    // MAPPING SOLUTIONS
    public static void mappingSolutions(List<Employee> employees, List<Product> products, List<String> words) {
        System.out.println("\n=== MAPPING SOLUTIONS ===");
        
        // Q6: Get list of all employee names
        System.out.println("Q6: All employee names");
        List<String> names = employees.stream()
            .map(Employee::getName)
            .collect(Collectors.toList());
        System.out.println(names);
        
        // Q7: Get list of all product categories (unique)
        System.out.println("\nQ7: Unique product categories");
        Set<String> categories = products.stream()
            .map(Product::getCategory)
            .collect(Collectors.toSet());
        System.out.println(categories);
        
        // Q8: Convert all words to uppercase
        System.out.println("\nQ8: Words in uppercase");
        List<String> upperWords = words.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println(upperWords);
        
        // Q9: Get employee names and their salaries as "Name: $Salary" format
        System.out.println("\nQ9: Employee name and salary format");
        employees.stream()
            .map(emp -> emp.getName() + ": $" + emp.getSalary())
            .forEach(System.out::println);
        
        // Q10: Get total value of each product (price * quantity)
        System.out.println("\nQ10: Total value per product");
        products.stream()
            .map(p -> p.getName() + ": $" + (p.getPrice() * p.getQuantity()))
            .forEach(System.out::println);
    }
    
    // SORTING SOLUTIONS
    public static void sortingSolutions(List<Employee> employees, List<Product> products) {
        System.out.println("\n=== SORTING SOLUTIONS ===");
        
        // Q11: Sort employees by salary (ascending)
        System.out.println("Q11: Employees sorted by salary (asc)");
        employees.stream()
            .sorted(Comparator.comparing(Employee::getSalary))
            .forEach(System.out::println);
        
        // Q12: Sort products by price (descending)
        System.out.println("\nQ12: Products sorted by price (desc)");
        products.stream()
            .sorted(Comparator.comparing(Product::getPrice).reversed())
            .forEach(System.out::println);
        
        // Q13: Sort employees by age, then by salary
        System.out.println("\nQ13: Employees sorted by age, then salary");
        employees.stream()
            .sorted(Comparator.comparing(Employee::getAge)
                   .thenComparing(Employee::getSalary))
            .forEach(System.out::println);
        
        // Q14: Sort employee names alphabetically
        System.out.println("\nQ14: Employee names sorted alphabetically");
        employees.stream()
            .map(Employee::getName)
            .sorted()
            .forEach(System.out::println);
    }
    
    // GROUPING SOLUTIONS
    public static void groupingSolutions(List<Employee> employees, List<Product> products) {
        System.out.println("\n=== GROUPING SOLUTIONS ===");
        
        // Q15: Group employees by department
        System.out.println("Q15: Employees grouped by department");
        Map<String, List<Employee>> byDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        byDept.forEach((dept, empList) -> {
            System.out.println(dept + ": " + empList.size() + " employees");
        });
        
        // Q16: Group products by category with count
        System.out.println("\nQ16: Products grouped by category with count");
        Map<String, Long> productCount = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        productCount.forEach((category, count) -> 
            System.out.println(category + ": " + count + " products"));
        
        // Q17: Group employees by city and get average salary per city
        System.out.println("\nQ17: Average salary by city");
        Map<String, Double> avgSalaryByCity = employees.stream()
            .collect(Collectors.groupingBy(Employee::getCity,
                    Collectors.averagingDouble(Employee::getSalary)));
        avgSalaryByCity.forEach((city, avg) -> 
            System.out.printf("%s: $%.2f\n", city, avg));
        
        // Q18: Group products by inStock status
        System.out.println("\nQ18: Products grouped by stock status");
        Map<Boolean, List<Product>> byStock = products.stream()
            .collect(Collectors.groupingBy(Product::isInStock));
        System.out.println("In Stock: " + byStock.get(true).size());
        System.out.println("Out of Stock: " + byStock.get(false).size());
    }
    
    // REDUCTION SOLUTIONS
    public static void reductionSolutions(List<Employee> employees, List<Product> products, List<Integer> numbers) {
        System.out.println("\n=== REDUCTION SOLUTIONS ===");
        
        // Q19: Find total salary of all employees
        System.out.println("Q19: Total salary of all employees");
        double totalSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .sum();
        System.out.println("Total: $" + totalSalary);
        
        // Q20: Find average age of employees
        System.out.println("\nQ20: Average age of employees");
        OptionalDouble avgAge = employees.stream()
            .mapToInt(Employee::getAge)
            .average();
        avgAge.ifPresent(age -> System.out.printf("Average age: %.2f\n", age));
        
        // Q21: Find sum of all numbers
        System.out.println("\nQ21: Sum of all numbers");
        int sum = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Sum: " + sum);
        
        // Q22: Find employee with highest salary
        System.out.println("\nQ22: Employee with highest salary");
        Optional<Employee> topEarner = employees.stream()
            .max(Comparator.comparing(Employee::getSalary));
        topEarner.ifPresent(System.out::println);
        
        // Q23: Find cheapest product in stock
        System.out.println("\nQ23: Cheapest product in stock");
        Optional<Product> cheapest = products.stream()
            .filter(Product::isInStock)
            .min(Comparator.comparing(Product::getPrice));
        cheapest.ifPresent(System.out::println);
        
        // Q24: Count employees in each department
        System.out.println("\nQ24: Count of employees per department");
        Map<String, Long> deptCount = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        deptCount.forEach((dept, count) -> 
            System.out.println(dept + ": " + count));
    }
    
    // ADVANCED SOLUTIONS
    public static void advancedSolutions(List<Employee> employees, List<Product> products) {
        System.out.println("\n=== ADVANCED SOLUTIONS ===");
        
        // Q25: Find top 3 highest paid employees
        System.out.println("Q25: Top 3 highest paid employees");
        employees.stream()
            .sorted(Comparator.comparing(Employee::getSalary).reversed())
            .limit(3)
            .forEach(System.out::println);
        
        // Q26: Check if any employee earns more than 90000
        System.out.println("\nQ26: Any employee earns > 90000?");
        boolean anyHighEarner = employees.stream()
            .anyMatch(emp -> emp.getSalary() > 90000);
        System.out.println("Any high earner: " + anyHighEarner);
        
        // Q27: Check if all products have positive price
        System.out.println("\nQ27: All products have positive price?");
        boolean allPositivePrice = products.stream()
            .allMatch(p -> p.getPrice() > 0);
        System.out.println("All positive prices: " + allPositivePrice);
        
        // Q28: Find departments with average salary > 70000
        System.out.println("\nQ28: Departments with avg salary > 70000");
        employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                    Collectors.averagingDouble(Employee::getSalary)))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 70000)
            .forEach(entry -> System.out.println(entry.getKey() + ": $" + entry.getValue()));
        
        // Q29: Get employee names from IT department, sorted by salary, as comma-separated string
        System.out.println("\nQ29: IT employee names as comma-separated string");
        String itNames = employees.stream()
            .filter(emp -> "IT".equals(emp.getDepartment()))
            .sorted(Comparator.comparing(Employee::getSalary))
            .map(Employee::getName)
            .collect(Collectors.joining(", "));
        System.out.println("IT employees: " + itNames);
        
        // Q30: Find products that are out of stock or have quantity < 20
        System.out.println("\nQ30: Products out of stock or quantity < 20");
        products.stream()
            .filter(p -> !p.isInStock() || p.getQuantity() < 20)
            .forEach(System.out::println);
    }
    
    // CHALLENGE SOLUTIONS
    public static void challengeSolutions(List<Employee> employees, List<Product> products, List<Integer> numbers) {
        System.out.println("\n=== CHALLENGE SOLUTIONS ===");
        
        // Q31: Create a Map of department -> list of employee names
        System.out.println("Q31: Department to employee names mapping");
        Map<String, List<String>> deptToNames = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                    Collectors.mapping(Employee::getName, Collectors.toList())));
        deptToNames.forEach((dept, names) -> 
            System.out.println(dept + ": " + names));
        
        // Q32: Find the second highest salary
        System.out.println("\nQ32: Second highest salary");
        OptionalDouble secondHighest = employees.stream()
            .mapToDouble(Employee::getSalary)
            .distinct()
            .sorted()
            .skip(employees.stream().mapToDouble(Employee::getSalary).distinct().count() - 2)
            .limit(1)
            .findFirst();
        secondHighest.ifPresent(salary -> System.out.println("Second highest: $" + salary));
        
        // Q33: Get statistics for employee salaries
        System.out.println("\nQ33: Salary statistics");
        DoubleSummaryStatistics stats = employees.stream()
            .mapToDouble(Employee::getSalary)
            .summaryStatistics();
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: $" + stats.getSum());
        System.out.println("Average: $" + stats.getAverage());
        System.out.println("Min: $" + stats.getMin());
        System.out.println("Max: $" + stats.getMax());
        
        // Q34: Partition employees into high earners (>75000) and others
        System.out.println("\nQ34: Partition employees by salary");
        Map<Boolean, List<Employee>> partitioned = employees.stream()
            .collect(Collectors.partitioningBy(emp -> emp.getSalary() > 75000));
        System.out.println("High earners (>75000): " + partitioned.get(true).size());
        System.out.println("Others: " + partitioned.get(false).size());
        
        // Q35: Find products where name length > 5 and price < 200
        System.out.println("\nQ35: Products with name length > 5 and price < 200");
        products.stream()
            .filter(p -> p.getName().length() > 5)
            .filter(p -> p.getPrice() < 200)
            .forEach(System.out::println);
        
        // Q36: Create a nested map: city -> department -> list of employees
        System.out.println("\nQ36: Nested grouping: city -> department -> employees");
        Map<String, Map<String, List<Employee>>> nested = employees.stream()
            .collect(Collectors.groupingBy(Employee::getCity,
                    Collectors.groupingBy(Employee::getDepartment)));
        nested.forEach((city, deptMap) -> {
            System.out.println(city + ":");
            deptMap.forEach((dept, empList) -> 
                System.out.println("  " + dept + ": " + empList.size() + " employees"));
        });
        
        // Q37: Find fibonacci numbers from the numbers list
        System.out.println("\nQ37: Fibonacci numbers from the list");
        Set<Integer> fibSet = Set.of(1, 1, 2, 3, 5, 8, 13, 21, 34);
        numbers.stream()
            .filter(fibSet::contains)
            .forEach(System.out::println);
        
        // Q38: Custom collector: Join employee names with their departments
        System.out.println("\nQ38: Join names with departments");
        String result = employees.stream()
            .map(emp -> emp.getName() + "(" + emp.getDepartment() + ")")
            .collect(Collectors.joining(", "));
        System.out.println(result);
    }
}
