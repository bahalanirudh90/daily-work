# Java Object-Oriented Programming (OOP) Concepts

## Core OOP Principles

### 1. Encapsulation
- **Definition**: Binding data (variables) and methods together while hiding internal implementation details
- **Implementation**: Use private variables with public getter/setter methods
- **Benefits**: 
  - Data security and controlled access
  - Code maintainability and reduced complexity
  - Flexibility to change internal implementation without affecting external code

### 2. Inheritance
- **Definition**: Mechanism where one class (child/subclass) acquires properties and behaviors from another class (parent/superclass)
- **Syntax**: 
  - Class inheritance: `class Child extends Parent`
  - Interface implementation: `class MyClass implements MyInterface`
- **Types in Java**:
  - Single inheritance (classes can extend only one class)
  - Multiple inheritance through interfaces
  - Multilevel inheritance (A → B → C)
  - Hierarchical inheritance (A → B, A → C)

### 3. Polymorphism
- **Definition**: Ability of objects to take multiple forms and behave differently based on context
- **Types**:
  - **Compile-time Polymorphism**: Method Overloading
  - **Runtime Polymorphism**: Method Overriding
- **Implementation**: Achieved through inheritance and interface implementation

### 4. Abstraction
- **Definition**: Hiding complex implementation details while showing only essential features
- **Implementation**: Using abstract classes and interfaces

## OOP Implementation Details

### Abstract Classes
- Contains both abstract methods (without implementation) and concrete methods (with implementation)
- Cannot be instantiated directly
- Subclasses must implement all abstract methods unless they are also abstract
- Support single inheritance only
- Variables can have any access modifier
- Use `abstract` keyword for class and methods

```java
abstract class Animal {
    abstract void makeSound();  // Abstract method
    void sleep() { /* concrete method */ }
}
```

### Interfaces
- Contract that defines what methods implementing classes must provide
- All methods implicitly public and abstract (pre-Java 8)
- All variables implicitly public, static, and final
- Support multiple inheritance
- From Java 8: Can have default and static methods
- From Java 9: Can have private methods

```java
interface Drawable {
    void draw();  // Abstract method
    default void display() { /* default method */ }
}
```

### Method Overriding vs Overloading

#### Method Overriding (Runtime Polymorphism)
- Same method signature in parent and child class
- Child class provides specific implementation of parent method
- Uses `@Override` annotation (recommended)
- Resolved at runtime

#### Method Overloading (Compile-time Polymorphism)
- Same method name with different parameters (number, type, or order)
- Can exist in same class or through inheritance
- Resolved at compile time

### Access Modifiers
| Modifier | Same Class | Same Package | Subclass(Different PKG) | Outside PKG |
|----------|------------|--------------|---------------------------------------|
| private  | ✓          | ✗           | ✗                       | ✗          |
| default  | ✓          | ✓           | ✗                       | ✗          |
| protected| ✓          | ✓           | ✓                       | ✗          |
| public   | ✓          | ✓           | ✓                       | ✓          |

- We cannot reduce the visibility but can increase it when overriding methods.
- access modifiers don't change during inheritance.

### Static vs Instance Members

#### Static Members
- Belong to the class, not to any specific instance
- Accessed using class name: `ClassName.staticMethod()`
- Loaded when class is first loaded into memory
- Cannot access instance variables directly
- Shared among all instances of the class

#### Instance Members
- Belong to specific object/instance
- Accessed using object reference: `object.instanceMethod()`
- Created when object is instantiated
- Can access both static and instance members
- Each object has its own copy

### Final Keyword
- **final variable**: Cannot be reassigned after initialization (constant)
- **final method**: Cannot be overridden by subclasses
- **final class**: Cannot be extended (e.g., String, Integer, wrapper classes)

```java
final int MAX_SIZE = 100;        // Constant variable
final void display() { }         // Cannot be overridden
final class UtilityClass { }     // Cannot be extended
``` 

## Functional Interface
- **Definition**: Interface with exactly one abstract method (SAM - Single Abstract Method)
- Can have unlimited default methods
- Can have unlimited static methods
- Can have private methods as helpers for default and static methods
- Must be annotated with `@FunctionalInterface` (optional but recommended)
- Used primarily with Lambda expressions and Method references

### Ways to Implement Functional Interface:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);  // Single abstract method

    default void print(int val) {
        System.out.println("Result is: " + val);
    }

    static void info() {
        System.out.println("This is a calculator");
    }
}

class Main {
    public static void main(String[] args) {
        /*----------------1. Anonymous Class (Generic Way)------------------------*/
        Calculator genericWay = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };
        int result = genericWay.calculate(5, 3);
        genericWay.print(result);

        /*---------------2. Lambda Expression-----------------------*/
        Calculator lambdaWay = (a, b) -> {
            Calculator.info();
            return a * b;
        };
        int lambdaResult = lambdaWay.calculate(5, 3);
        lambdaWay.print(lambdaResult);

        /*----------------3. Method Reference-----------------------*/
        Calculator methodRefWay = Main::add;
        int methodRefResult = methodRefWay.calculate(5, 3);
        methodRefWay.print(methodRefResult);

        // Static method references
        Calculator max = Integer::max;
        Calculator min = Integer::min;
    }

    private static int add(int a, int b) {
        return a + b;
    }
}
```
- Another Example :
``` java
@FunctionalInterface
interface StringProcessor{
    String process(String str);

    default String addPrefix(String prefix,String str){
        return prefix + str;
    }
}
class Main {
    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
        StringProcessor obj1 = new StringProcessor() {
            @Override
            public String process(String str) {
                return str.toUpperCase();
            }
        };

        String str = "hello";
        System.out.println(obj1.addPrefix(">>>", str));

        StringProcessor obj2 = (str) -> str.toLowerCase();

        StringProcessor obj3 = Main::reverse;

    }
}
```
- Another Example :
``` java
@FunctionalInterface
interface NumberChecker {
    boolean check(int number);
}
class Main {
    public static void main(String[] args) {
        /* ------------------------------------------------ */
        NumberChecker isPrime = new NumberChecker() {
            @Override
            public boolean check(int number) {
                if (number <= 1)
                    return false;
                for (int i = 2; i <= Math.sqrt(number); i++) {
                    if (number % i == 0)
                        return false;
                }
                return true;
            }
        };
        /* ------------------------------------------------ */
        NumberChecker isEven = (number) -> number % 2 == 0;
        /* ------------------------------------------------ */
        NumberChecker isOdd = (number) -> number % 2 != 0;
        /* ------------------------------------------------ */
        int[] numbers = {2, 3, 4, 5, 8, 11, 16, 17};
        for (int num : numbers) {
            System.out.println(num + " -> Even: " + isEven.check(num) + 
                             ", Prime: " + isPrime.check(num));
        }
        System.out.println();
    }
}
```

## Built-in Functional Interfaces

Java provides several predefined functional interfaces in the `java.util.function` package:

### 1. Predicate<T>
- **Purpose**: Represents a boolean-valued function that tests a condition
- **Method**: `boolean test(T t)`
- **Example**:

```java
public static void callPredicate() {
    Predicate<String> isEmpty = new Predicate<String>() {
        @Override
        public boolean test(String s) {
            return s == null || s.isEmpty();
        }
    };

    Predicate<String> isNotEmpty = isEmpty.negate();
    Predicate<String> lengthGreaterThan5 = s -> s.length() > 5;

    System.out.println(isEmpty.test("Hello")); // false
    System.out.println(isNotEmpty.test("Hello")); // true
    System.out.println(lengthGreaterThan5.test("Programming")); // true
}
```

### 2. Consumer<T>
- **Purpose**: Takes one argument and returns no result; performs an action on the given argument
- **Method**: `void accept(T t)`
- **Example**:

```java
public static void callConsumer() {
    Consumer<String> changeToUpperCase = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println(s.toUpperCase());
        }
    };
    
    Consumer<String> printLength = s -> System.out.println("Length: " + s.length());
    
    changeToUpperCase.accept("hello"); // prints "HELLO"
    printLength.accept("hello"); // prints "Length: 5"
}
```

### 3. Function<T, R>
- **Purpose**: Takes one argument and returns a result
- **Method**: `R apply(T t)`
- **Example**:

```java
public static void callFunction() {
    Function<String, Integer> stringLength = new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return s.length();
        }
    };
    
    Function<Integer, String> intToString = Object::toString;
    Function<String, String> toUpperCase = String::toUpperCase;
    
    System.out.println(stringLength.apply("Hello")); // prints 5
    System.out.println(intToString.apply(42)); // prints "42"
    System.out.println(toUpperCase.apply("hello")); // prints "HELLO"
}
```

### 4. Supplier<T>
- **Purpose**: Takes no arguments and returns a result
- **Method**: `T get()`
- **Example**:

```java
public static void callSupplier() {
    Supplier<Double> randomValue = new Supplier<Double>() {
        @Override
        public Double get() {
            return Math.random();
        }
    };
    
    Supplier<String> getCurrentTime = () -> new Date().toString();
    Supplier<Integer> constant = () -> 42;
    
    System.out.println(randomValue.get()); // prints random number
    System.out.println(getCurrentTime.get()); // prints current date/time
    System.out.println(constant.get()); // prints 42
}
```

### 5. BinaryOperator<T>
- **Purpose**: Takes two arguments of the same type and returns result of the same type
- **Method**: `T apply(T t1, T t2)`
- **Example**:

```java
public static void callBinaryOperator() {
    BinaryOperator<Integer> add = new BinaryOperator<Integer>() {
        @Override
        public Integer apply(Integer a, Integer b) {
            return a + b;
        }
    };
    
    BinaryOperator<Integer> multiply = (a, b) -> a * b;
    BinaryOperator<Integer> max = Integer::max;
    BinaryOperator<String> concat = (s1, s2) -> s1 + s2;
    
    System.out.println(add.apply(5, 3)); // prints 8
    System.out.println(multiply.apply(5, 3)); // prints 15
    System.out.println(max.apply(5, 3)); // prints 5
    System.out.println(concat.apply("Hello", "World")); // prints "HelloWorld"
}
```

### 6. UnaryOperator<T>
- **Purpose**: Takes one argument and returns result of the same type
- **Method**: `T apply(T t)`
- **Example**:

```java
public static void callUnaryOperator() {
    UnaryOperator<Integer> square = new UnaryOperator<Integer>() {
        @Override
        public Integer apply(Integer x) {
            return x * x;
        }
    };
    
    UnaryOperator<String> toUpperCase = String::toUpperCase;
    UnaryOperator<Integer> increment = x -> x + 1;
    
    System.out.println(square.apply(5)); // prints 25
    System.out.println(toUpperCase.apply("hello")); // prints "HELLO"
    System.out.println(increment.apply(10)); // prints 11
}
```

### 7. BiFunction<T, U, R>
- **Purpose**: Takes two inputs of different types and returns a result
- **Method**: `R apply(T t, U u)`
- **Example**:

```java
public static void callBiFunction() {
    BiFunction<Integer, Integer, Integer> add = new BiFunction<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer a, Integer b) {
            return a + b;
        }
    };
    
    BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
    BiFunction<Integer, Integer, Double> power = (a, b) -> Math.pow(a, b);
    BiFunction<String, Integer, String> repeat = (str, times) -> str.repeat(times);
    
    System.out.println("Addition: " + add.apply(5, 3)); // 8
    System.out.println("Multiplication: " + multiply.apply(5, 3)); // 15
    System.out.println("Power: " + power.apply(5, 3)); // 125.0
    System.out.println("Repeat: " + repeat.apply("Hi", 3)); // "HiHiHi"
}
```

### 8. BiConsumer<T, U>
- **Purpose**: Performs an action with two inputs and returns no result
- **Method**: `void accept(T t, U u)`
- **Example**:

```java
public static void callBiConsumer() {
    BiConsumer<String, Integer> biConsumer = new BiConsumer<String, Integer>() {
        @Override
        public void accept(String s, Integer integer) {
            System.out.println("String: " + s.toUpperCase() + ", Integer: " + integer);
        }
    };
    
    BiConsumer<String, String> printBoth = (s1, s2) -> 
        System.out.println("First: " + s1 + ", Second: " + s2);
    
    biConsumer.accept("hello", 42); // prints "String: HELLO, Integer: 42"
    printBoth.accept("Java", "Programming"); // prints "First: Java, Second: Programming"
}
```

### 9. BiPredicate<T, U>
- **Purpose**: Tests a condition with two arguments
- **Method**: `boolean test(T t, U u)`
- **Example**:

```java
public static void callBiPredicate() {
    BiPredicate<String, Integer> lengthEquals = new BiPredicate<String, Integer>() {
        @Override
        public boolean test(String str, Integer length) {
            return str.length() == length;
        }
    };
    
    BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
    BiPredicate<String, String> areEqual = String::equals;
    
    System.out.println(lengthEquals.test("Hello", 5)); // true
    System.out.println(isGreater.test(10, 5)); // true
    System.out.println(areEqual.test("Java", "Java")); // true
}
```

## Java Streams API

### What are Streams?
- **Definition**: A sequence of elements that can be processed in a functional programming style
- **Introduced**: Java 8 (2014)
- **Purpose**: Process collections of data declaratively and efficiently
- **Key Features**:
  - Functional programming approach
  - Lazy evaluation (operations are not executed until terminal operation)
  - Can be parallel for better performance
  - Immutable (original data source is not modified)

### Stream Creation

```java
import java.util.*;
import java.util.stream.*;

public class StreamCreation {
    public static void main(String[] args) {
        // 1. From Collections
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");
        Stream<String> nameStream = names.stream();
        
        // 2. From Arrays
        String[] nameArray = {"Alice", "Bob", "Charlie"};
        Stream<String> arrayStream = Arrays.stream(nameArray);
        
        // 3. Using Stream.of()
        Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5);
        
        // 4. Using Stream.generate() - Infinite Stream
        Stream<Double> randomStream = Stream.generate(Math::random).limit(5);
        
        // 5. Using Stream.iterate() - Infinite Stream
        Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2).limit(10);
        
        // 6. Using IntStream, DoubleStream, LongStream
        IntStream intStream = IntStream.range(1, 11); // 1 to 10
        DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3);
    }
}
```

### Stream Operations

#### Intermediate Operations (Lazy - return another Stream)

##### 1. filter() - Filters elements based on condition
```java
public static void filterExample() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    // Filter even numbers
    List<Integer> evenNumbers = numbers.stream()
        .filter(n -> n % 2 == 0)
        .collect(Collectors.toList());
    System.out.println("Even numbers: " + evenNumbers); // [2, 4, 6, 8, 10]
    
    // Filter strings by length
    List<String> words = Arrays.asList("Java", "Python", "C++", "JavaScript", "Go");
    List<String> longWords = words.stream()
        .filter(word -> word.length() > 4)
        .collect(Collectors.toList());
    System.out.println("Long words: " + longWords); // [Python, JavaScript]
}
```

##### 2. map() - Transform each element
```java
public static void mapExample() {
    List<String> names = Arrays.asList("alice", "bob", "charlie");
    
    // Convert to uppercase
    List<String> upperNames = names.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());
    System.out.println("Upper names: " + upperNames); // [ALICE, BOB, CHARLIE]
    
    // Get string lengths
    List<Integer> lengths = names.stream()
        .map(String::length)
        .collect(Collectors.toList());
    System.out.println("Name lengths: " + lengths); // [5, 3, 7]
    
    // Square numbers
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> squares = numbers.stream()
        .map(n -> n * n)
        .collect(Collectors.toList());
    System.out.println("Squares: " + squares); // [1, 4, 9, 16, 25]
}
```

##### 3. flatMap() - Flatten nested structures
```java
public static void flatMapExample() {
    List<List<String>> nestedList = Arrays.asList(
        Arrays.asList("Java", "Python"),
        Arrays.asList("C++", "JavaScript"),
        Arrays.asList("Go", "Rust")
    );
    
    // Flatten the nested list
    List<String> flatList = nestedList.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
    System.out.println("Flat list: " + flatList); // [Java, Python, C++, JavaScript, Go, Rust]
    
    // Split words and flatten
    List<String> sentences = Arrays.asList("Hello World", "Java Streams", "Functional Programming");
    List<String> words = sentences.stream()
        .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
        .collect(Collectors.toList());
    System.out.println("Words: " + words); // [Hello, World, Java, Streams, Functional, Programming]
}
```

##### 4. distinct() - Remove duplicates
```java
public static void distinctExample() {
    List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 5);
    
    List<Integer> uniqueNumbers = numbers.stream()
        .distinct()
        .collect(Collectors.toList());
    System.out.println("Unique numbers: " + uniqueNumbers); // [1, 2, 3, 4, 5]
}
```

##### 5. sorted() - Sort elements
```java
public static void sortedExample() {
    List<String> names = Arrays.asList("Charlie", "Alice", "Bob", "Diana");
    
    // Natural sorting
    List<String> sortedNames = names.stream()
        .sorted()
        .collect(Collectors.toList());
    System.out.println("Sorted names: " + sortedNames); // [Alice, Bob, Charlie, Diana]
    
    // Custom sorting
    List<String> reverseSorted = names.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
    System.out.println("Reverse sorted: " + reverseSorted); // [Diana, Charlie, Bob, Alice]
    
    // Sort by length
    List<String> sortedByLength = names.stream()
        .sorted(Comparator.comparing(String::length))
        .collect(Collectors.toList());
    System.out.println("Sorted by length: " + sortedByLength); // [Bob, Alice, Diana, Charlie]
}
```

##### 6. limit() and skip() - Limit or skip elements
```java
public static void limitSkipExample() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    // Take first 5 elements
    List<Integer> first5 = numbers.stream()
        .limit(5)
        .collect(Collectors.toList());
    System.out.println("First 5: " + first5); // [1, 2, 3, 4, 5]
    
    // Skip first 3 elements
    List<Integer> skipFirst3 = numbers.stream()
        .skip(3)
        .collect(Collectors.toList());
    System.out.println("Skip first 3: " + skipFirst3); // [4, 5, 6, 7, 8, 9, 10]
    
    // Pagination: skip 3, take 4
    List<Integer> page = numbers.stream()
        .skip(3)
        .limit(4)
        .collect(Collectors.toList());
    System.out.println("Page (skip 3, take 4): " + page); // [4, 5, 6, 7]
}
```

#### Terminal Operations (Eager - produce a result)

##### 1. collect() - Collect elements into collections
```java
public static void collectExample() {
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana", "Eve");
    
    // Collect to List
    List<String> list = names.stream()
        .filter(name -> name.length() > 3)
        .collect(Collectors.toList());
    
    // Collect to Set
    Set<String> set = names.stream()
        .collect(Collectors.toSet());
    
    // Collect to Map
    Map<String, Integer> nameToLength = names.stream()
        .collect(Collectors.toMap(
            name -> name,           // key
            String::length         // value
        ));
    System.out.println("Name to length map: " + nameToLength);
    
    // Group by length
    Map<Integer, List<String>> groupedByLength = names.stream()
        .collect(Collectors.groupingBy(String::length));
    System.out.println("Grouped by length: " + groupedByLength);
    
    // Joining strings
    String joinedNames = names.stream()
        .collect(Collectors.joining(", "));
    System.out.println("Joined names: " + joinedNames);
}
```

##### 2. forEach() - Perform action on each element
```java
public static void forEachExample() {
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
    
    // Print each name
    names.stream().forEach(System.out::println);
    
    // Print with index (using IntStream)
    IntStream.range(0, names.size())
        .forEach(i -> System.out.println(i + ": " + names.get(i)));
}
```

##### 3. reduce() - Reduce elements to single value
```java
public static void reduceExample() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    
    // Sum using reduce
    Optional<Integer> sum = numbers.stream()
        .reduce((a, b) -> a + b);
    System.out.println("Sum: " + sum.orElse(0)); // 15
    
    // Sum with initial value
    Integer sumWithInitial = numbers.stream()
        .reduce(0, (a, b) -> a + b);
    System.out.println("Sum with initial: " + sumWithInitial); // 15
    
    // Find maximum
    Optional<Integer> max = numbers.stream()
        .reduce(Integer::max);
    System.out.println("Max: " + max.orElse(0)); // 5
    
    // Concatenate strings
    List<String> words = Arrays.asList("Java", "is", "awesome");
    String sentence = words.stream()
        .reduce("", (a, b) -> a + " " + b).trim();
    System.out.println("Sentence: " + sentence); // "Java is awesome"
}
```

##### 4. count(), min(), max(), anyMatch(), allMatch(), noneMatch()
```java
public static void aggregateOperations() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    // Count elements
    long count = numbers.stream()
        .filter(n -> n % 2 == 0)
        .count();
    System.out.println("Even count: " + count); // 5
    
    // Find min and max
    Optional<Integer> min = numbers.stream().min(Integer::compareTo);
    Optional<Integer> max = numbers.stream().max(Integer::compareTo);
    System.out.println("Min: " + min.orElse(0) + ", Max: " + max.orElse(0));
    
    // Matching operations
    boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);
    boolean allPositive = numbers.stream().allMatch(n -> n > 0);
    boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);
    
    System.out.println("Any even: " + anyEven); // true
    System.out.println("All positive: " + allPositive); // true
    System.out.println("None negative: " + noneNegative); // true
}
```

##### 5. findFirst() and findAny()
```java
public static void findOperations() {
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");
    
    // Find first element
    Optional<String> first = names.stream()
        .filter(name -> name.startsWith("C"))
        .findFirst();
    System.out.println("First name starting with C: " + first.orElse("None")); // Charlie
    
    // Find any element (useful in parallel streams)
    Optional<String> any = names.stream()
        .filter(name -> name.length() > 4)
        .findAny();
    System.out.println("Any name with length > 4: " + any.orElse("None")); // Alice or Charlie
}
```

### Complex Stream Examples

```java
public class ComplexStreamExamples {
    static class Person {
        private String name;
        private int age;
        private String city;
        private double salary;
        
        public Person(String name, int age, String city, double salary) {
            this.name = name;
            this.age = age;
            this.city = city;
            this.salary = salary;
        }
        
        // Getters
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getCity() { return city; }
        public double getSalary() { return salary; }
        
        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d, city='%s', salary=%.2f}", 
                               name, age, city, salary);
        }
    }
    
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, "New York", 75000),
            new Person("Bob", 25, "Los Angeles", 65000),
            new Person("Charlie", 35, "New York", 85000),
            new Person("Diana", 28, "Chicago", 70000),
            new Person("Eve", 32, "Los Angeles", 80000)
        );
        
        // 1. Find highest paid person in New York
        Optional<Person> highestPaidInNY = people.stream()
            .filter(person -> "New York".equals(person.getCity()))
            .max(Comparator.comparing(Person::getSalary));
        
        System.out.println("Highest paid in NY: " + highestPaidInNY.orElse(null));
        
        // 2. Group people by city and calculate average salary
        Map<String, Double> avgSalaryByCity = people.stream()
            .collect(Collectors.groupingBy(
                Person::getCity,
                Collectors.averagingDouble(Person::getSalary)
            ));
        
        System.out.println("Average salary by city: " + avgSalaryByCity);
        
        // 3. Get names of people above 30, sorted by salary
        List<String> namesAbove30 = people.stream()
            .filter(person -> person.getAge() > 30)
            .sorted(Comparator.comparing(Person::getSalary).reversed())
            .map(Person::getName)
            .collect(Collectors.toList());
        
        System.out.println("Names above 30 (sorted by salary desc): " + namesAbove30);
        
        // 4. Check if any person earns more than 90k
        boolean anyHighEarner = people.stream()
            .anyMatch(person -> person.getSalary() > 90000);
        
        System.out.println("Any high earner (>90k): " + anyHighEarner);
        
        // 5. Total salary of all people in Los Angeles
        double totalSalaryLA = people.stream()
            .filter(person -> "Los Angeles".equals(person.getCity()))
            .mapToDouble(Person::getSalary)
            .sum();
        
        System.out.println("Total salary in LA: $" + totalSalaryLA);
    }
}
```

### Parallel Streams

```java
public static void parallelStreamExample() {
    List<Integer> largeList = IntStream.range(1, 1000000)
        .boxed()
        .collect(Collectors.toList());
    
    // Sequential processing
    long startTime = System.currentTimeMillis();
    long sequentialSum = largeList.stream()
        .mapToLong(Integer::longValue)
        .sum();
    long sequentialTime = System.currentTimeMillis() - startTime;
    
    // Parallel processing
    startTime = System.currentTimeMillis();
    long parallelSum = largeList.parallelStream()
        .mapToLong(Integer::longValue)
        .sum();
    long parallelTime = System.currentTimeMillis() - startTime;
    
    System.out.println("Sequential sum: " + sequentialSum + " (Time: " + sequentialTime + "ms)");
    System.out.println("Parallel sum: " + parallelSum + " (Time: " + parallelTime + "ms)");
}
```

### Best Practices

1. **Use appropriate collectors**: `Collectors.toList()`, `Collectors.toSet()`, `Collectors.groupingBy()`
2. **Avoid side effects**: Don't modify external variables in lambda expressions
3. **Use method references**: When possible, use `String::length` instead of `s -> s.length()`
4. **Parallel streams**: Use only for CPU-intensive operations on large datasets
5. **Optional handling**: Always handle `Optional` results properly
6. **Lazy evaluation**: Remember intermediate operations are lazy, only executed when terminal operation is called

