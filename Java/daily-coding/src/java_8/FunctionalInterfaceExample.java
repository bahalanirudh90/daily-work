package java_8;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

// Custom functional interfaces
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
    
    default void printResult(int result) {
        System.out.println("Result: " + result);
    }
    
    static void info() {
        System.out.println("Calculator: Performs mathematical operations");
    }
}

@FunctionalInterface
interface StringProcessor {
    String process(String input);
    
    default String processWithPrefix(String input, String prefix) {
        return prefix + process(input);
    }
}

@FunctionalInterface
interface NumberChecker {
    boolean check(int number);
}

public class FunctionalInterfaceExample {
    
    public static void main(String[] args) {
        System.out.println("=== Complete Functional Interface Demo ===\n");
        
        demonstrateCustomFunctionalInterfaces();
        demonstrateMethodReferences();
        demonstrateBuiltInFunctionalInterfaces();
        demonstrateStreamsWithFunctionalInterfaces();
        demonstrateFunctionComposition();
        demonstrateRealWorldExamples();
        printSummary();
    }
    
    public static void demonstrateCustomFunctionalInterfaces() {
        System.out.println("1. CUSTOM FUNCTIONAL INTERFACES");
        System.out.println("=" .repeat(40));
        
        // Calculator interface
        Calculator.info();
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> b != 0 ? a / b : 0;
        
        int x = 15, y = 3;
        System.out.println("Operating on: " + x + " and " + y);
        System.out.println("Addition: " + add.calculate(x, y));
        System.out.println("Multiplication: " + multiply.calculate(x, y));
        System.out.println("Division: " + divide.calculate(x, y));
        multiply.printResult(multiply.calculate(x, y));
        
        // StringProcessor interface
        System.out.println("\nString Processing:");
        StringProcessor uppercase = String::toUpperCase;
        StringProcessor reverse = input -> new StringBuilder(input).reverse().toString();
        
        String text = "Hello World";
        System.out.println("Original: " + text);
        System.out.println("Uppercase: " + uppercase.process(text));
        System.out.println("Reversed: " + reverse.process(text));
        System.out.println("With prefix: " + uppercase.processWithPrefix(text, ">>> "));
        
        // NumberChecker interface
        System.out.println("\nNumber Checking:");
        NumberChecker isEven = n -> n % 2 == 0;
        NumberChecker isPrime = FunctionalInterfaceExample::isPrime;
        
        int[] numbers = {2, 3, 4, 5, 8, 11, 16, 17};
        for (int num : numbers) {
            System.out.println(num + " -> Even: " + isEven.check(num) + 
                             ", Prime: " + isPrime.check(num));
        }
        System.out.println();
    }
    
    public static void demonstrateMethodReferences() {
        System.out.println("2. METHOD REFERENCES");
        System.out.println("=" .repeat(40));
        
        // Static method references
        Calculator max = Integer::max;
        Calculator min = Integer::min;
        
        System.out.println("Method References with Calculator:");
        System.out.println("Max(20, 15): " + max.calculate(20, 15));
        System.out.println("Min(20, 15): " + min.calculate(20, 15));
        
        // Instance method references
        String sample = "functional programming";
        StringProcessor toUpper = String::toUpperCase;
        StringProcessor substring = input -> input.substring(0, Math.min(5, input.length()));
        
        System.out.println("\nInstance Method References:");
        System.out.println("Original: " + sample);
        System.out.println("ToUpper: " + toUpper.process("test"));
        System.out.println("Substring: " + substring.process(sample));
        System.out.println();
    }
    
    public static void demonstrateBuiltInFunctionalInterfaces() {
        System.out.println("3. BUILT-IN FUNCTIONAL INTERFACES");
        System.out.println("=" .repeat(40));
        
        // Predicate<T> - Test conditions
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isLong = s -> s.length() > 5;
        Predicate<Integer> isPositive = n -> n > 0;
        
        System.out.println("Predicate Examples:");
        System.out.println("'' isEmpty: " + isEmpty.test(""));
        System.out.println("'hello' isLong: " + isLong.test("hello"));
        System.out.println("'programming' isLong: " + isLong.test("programming"));
        System.out.println("-5 isPositive: " + isPositive.test(-5));
        
        // Function<T,R> - Transform data
        Function<String, Integer> getLength = String::length;
        Function<String, String> addBrackets = s -> "[" + s + "]";
        Function<Integer, String> numberToHex = Integer::toHexString;
        
        System.out.println("\nFunction Examples:");
        System.out.println("Length of 'Java': " + getLength.apply("Java"));
        System.out.println("Add brackets: " + addBrackets.apply("text"));
        System.out.println("255 to hex: " + numberToHex.apply(255));
        
        // Consumer<T> - Process data
        Consumer<String> printer = System.out::println;
        Consumer<String> upperPrinter = s -> System.out.println(">>> " + s.toUpperCase());
        
        System.out.println("\nConsumer Examples:");
        printer.accept("Normal print");
        upperPrinter.accept("upper print");
        
        // Supplier<T> - Generate data
        Supplier<String> randomGreeting = () -> "Hello #" + (int)(Math.random() * 1000);
        Supplier<List<String>> listSupplier = ArrayList::new;
        
        System.out.println("\nSupplier Examples:");
        System.out.println(randomGreeting.get());
        System.out.println(randomGreeting.get());
        System.out.println("New list: " + listSupplier.get());
        
        // BinaryOperator<T> - Combine two values
        BinaryOperator<String> concat = (s1, s2) -> s1 + " " + s2;
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        
        System.out.println("\nBinaryOperator Examples:");
        System.out.println("Concat: " + concat.apply("Hello", "World"));
        System.out.println("Multiply: " + multiply.apply(6, 7));
        System.out.println();
    }
    
    public static void demonstrateStreamsWithFunctionalInterfaces() {
        System.out.println("4. STREAMS WITH FUNCTIONAL INTERFACES");
        System.out.println("=" .repeat(40));
        
        List<String> words = Arrays.asList("java", "python", "javascript", "go", "rust", "kotlin");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Stream operations with functional interfaces
        System.out.println("Original words: " + words);
        
        // Filter + Map + Collect
        List<String> processedWords = words.stream()
            .filter(word -> word.length() > 4)     // Predicate
            .map(String::toUpperCase)              // Function
            .map(word -> "[" + word + "]")         // Function
            .collect(Collectors.toList());
        
        System.out.println("Processed (length > 4): " + processedWords);
        
        // Number processing
        System.out.println("\nOriginal numbers: " + numbers);
        
        // Complex stream pipeline
        List<String> numberResults = numbers.stream()
            .filter(n -> n % 2 == 0)              // Predicate - even numbers
            .map(n -> n * n)                      // Function - square
            .filter(n -> n > 10)                  // Predicate - greater than 10
            .map(n -> "Square: " + n)             // Function - format
            .collect(Collectors.toList());
        
        System.out.println("Even squares > 10: " + numberResults);
        
        // Reduce operations
        Optional<Integer> sum = numbers.stream()
            .filter(n -> n > 5)                   // Predicate
            .reduce(Integer::sum);                // BinaryOperator
        
        System.out.println("Sum of numbers > 5: " + sum.orElse(0));
        
        // forEach with Consumer
        System.out.println("\nPrinting with forEach:");
        words.stream()
            .filter(word -> word.startsWith("j"))  // Predicate
            .map(String::toUpperCase)              // Function
            .forEach(word -> System.out.println("-> " + word)); // Consumer
        System.out.println();
    }
    
    public static void demonstrateFunctionComposition() {
        System.out.println("5. FUNCTION COMPOSITION");
        System.out.println("=" .repeat(40));
        
        // Function composition
        Function<String, String> removeSpaces = s -> s.replace(" ", "");
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, String> addBrackets = s -> "[" + s + "]";
        
        // Compose functions using andThen
        Function<String, String> processText = removeSpaces
            .andThen(toUpperCase)
            .andThen(addBrackets);
        
        String input = "hello world programming";
        System.out.println("Input: " + input);
        System.out.println("Step 1 (remove spaces): " + removeSpaces.apply(input));
        System.out.println("Step 2 (uppercase): " + toUpperCase.apply(removeSpaces.apply(input)));
        System.out.println("Step 3 (add brackets): " + addBrackets.apply(toUpperCase.apply(removeSpaces.apply(input))));
        System.out.println("Composed result: " + processText.apply(input));
        
        // Predicate composition
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isGreaterThan5 = n -> n > 5;
        Predicate<Integer> evenAndGreaterThan5 = isEven.and(isGreaterThan5);
        
        System.out.println("\nPredicate Composition:");
        List<Integer> testNumbers = Arrays.asList(2, 4, 6, 8, 10, 3, 5, 7, 9);
        List<Integer> filtered = testNumbers.stream()
            .filter(evenAndGreaterThan5)
            .collect(Collectors.toList());
        
        System.out.println("Numbers (even AND > 5): " + filtered);
        System.out.println();
    }
    
    public static void demonstrateRealWorldExamples() {
        System.out.println("6. REAL-WORLD EXAMPLES");
        System.out.println("=" .repeat(40));
        
        // Example 1: Employee data processing
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 25, 75000, "Engineering"),
            new Employee("Bob", 30, 65000, "Marketing"),
            new Employee("Charlie", 35, 85000, "Engineering"),
            new Employee("Diana", 28, 70000, "Sales"),
            new Employee("Eve", 32, 90000, "Engineering")
        );
        
        System.out.println("Employee Processing:");
        
        // Find high-earning engineers
        List<String> highEarningEngineers = employees.stream()
            .filter(emp -> emp.department.equals("Engineering"))    // Predicate
            .filter(emp -> emp.salary > 70000)                     // Predicate
            .map(emp -> emp.name + " ($" + emp.salary + ")")       // Function
            .collect(Collectors.toList());
        
        System.out.println("High-earning Engineers: " + highEarningEngineers);
        
        // Calculate average salary by department
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                emp -> emp.department,                              // Function
                Collectors.averagingDouble(emp -> emp.salary)       // Function
            ));
        
        System.out.println("Average salary by department:");
        avgSalaryByDept.forEach((dept, avg) -> 
            System.out.println("  " + dept + ": $" + String.format("%.0f", avg)));
        
        // Example 2: Data validation pipeline
        System.out.println("\nData Validation Pipeline:");
        List<String> emails = Arrays.asList(
            "alice@company.com", "invalid-email", "bob@company.com", 
            "", "charlie@company.com", "diana@company.com"
        );
        
        Predicate<String> isNotEmpty = s -> !s.isEmpty();
        Predicate<String> containsAt = s -> s.contains("@");
        Predicate<String> containsDot = s -> s.contains(".");
        Predicate<String> isValidEmail = isNotEmpty.and(containsAt).and(containsDot);
        
        List<String> validEmails = emails.stream()
            .filter(isValidEmail)
            .map(String::toLowerCase)
            .sorted()
            .collect(Collectors.toList());
        
        System.out.println("Valid emails: " + validEmails);
        System.out.println();
    }
    
    public static void printSummary() {
        System.out.println("7. SUMMARY");
        System.out.println("=" .repeat(40));
        System.out.println("✓ Functional Interface: Exactly 1 abstract method");
        System.out.println("✓ Enables Lambda expressions: (params) -> body");
        System.out.println("✓ Enables Method references: Class::method");
        System.out.println("✓ Can have default and static methods");
        System.out.println("✓ Built-in interfaces: Predicate, Function, Consumer, Supplier");
        System.out.println("✓ Perfect for Stream API operations");
        System.out.println("✓ Supports function composition");
        System.out.println("✓ Makes code more concise and readable");
        System.out.println("✓ Foundation of functional programming in Java");
    }
    
    // Helper methods
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    
    // Employee class for examples
    static class Employee {
        String name;
        int age;
        double salary;
        String department;
        
        Employee(String name, int age, double salary, String department) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.department = department;
        }
    }
}
