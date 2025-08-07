package java_8;
// Interface vs Abstract Class comparison

// Interface example
interface Shape {
    double PI = 3.14159; // public static final
    
    double calculateArea(); // public abstract
    double calculatePerimeter(); // public abstract
    
    default void display() { // default method
        System.out.println("This is a shape");
    }
    
    static void info() { // static method
        System.out.println("Shape interface provides geometric calculations");
    }
}

// Abstract class example
abstract class Animal {
    protected String name; // instance variable
    protected int age; // instance variable
    
    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Concrete method
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
    
    // Abstract method
    public abstract void makeSound();
    
    // Another abstract method
    public abstract void move();
    
    // Protected method
    protected void sleep() {
        System.out.println(name + " is sleeping");
    }
}

// Interface implementation
class Circle implements Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * PI * radius;
    }
}

class Rectangle implements Shape {
    private double length, width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double calculateArea() {
        return length * width;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
    
    @Override
    public void display() {
        System.out.println("This is a rectangle with length=" + length + " and width=" + width);
    }
}

// Abstract class implementation
class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age); // calling parent constructor
        this.breed = breed;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
    
    @Override
    public void move() {
        System.out.println(name + " is running on four legs");
    }
    
    public void displayBreed() {
        System.out.println("Breed: " + breed);
    }
}

class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow! Meow!");
    }
    
    @Override
    public void move() {
        System.out.println(name + " is walking gracefully");
    }
}

public class InterfaceVsAbstractExample {
    public static void main(String[] args) {
        System.out.println("=== Interface vs Abstract Class Comparison ===\n");
        
        // Interface examples
        System.out.println("1. Interface Implementation:");
        Shape.info(); // static method call
        
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);
        
        System.out.println("Circle - Area: " + circle.calculateArea() + 
                         ", Perimeter: " + circle.calculatePerimeter());
        circle.display(); // default method
        
        System.out.println("Rectangle - Area: " + rectangle.calculateArea() + 
                         ", Perimeter: " + rectangle.calculatePerimeter());
        rectangle.display(); // overridden method
        
        // Abstract class examples
        System.out.println("\n2. Abstract Class Implementation:");
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        Cat cat = new Cat("Whiskers", 2);
        
        dog.displayInfo(); // inherited concrete method
        dog.makeSound(); // implemented abstract method
        dog.move(); // implemented abstract method
        dog.displayBreed(); // own method
        dog.sleep(); // inherited protected method
        
        cat.displayInfo();
        cat.makeSound();
        cat.move();
        cat.sleep();
        
        // Polymorphism examples
        System.out.println("\n3. Polymorphism:");
        
        // Interface polymorphism
        Shape[] shapes = {circle, rectangle};
        for (Shape shape : shapes) {
            System.out.println("Area: " + shape.calculateArea());
            shape.display();
        }
        
        // Abstract class polymorphism
        Animal[] animals = {dog, cat};
        for (Animal animal : animals) {
            animal.displayInfo();
            animal.makeSound();
            animal.move();
        }
        
        // Comparison table
        System.out.println("\n4. Comparison Table:");
        printComparisonTable();
        
        System.out.println("\n5. When to Use:");
        System.out.println("Use Interface when:");
        System.out.println("- You want to specify contract for classes");
        System.out.println("- You need multiple inheritance");
        System.out.println("- You want to achieve loose coupling");
        System.out.println("- You're designing APIs or frameworks");
        
        System.out.println("\nUse Abstract Class when:");
        System.out.println("- You want to share code among related classes");
        System.out.println("- You need to declare non-public members");
        System.out.println("- You want to have instance variables");
        System.out.println("- You need constructors");
    }
    
    public static void printComparisonTable() {
        System.out.println("┌─────────────────────┬──────────────────┬─────────────────────┐");
        System.out.println("│      Feature        │    Interface     │   Abstract Class    │");
        System.out.println("├─────────────────────┼──────────────────┼─────────────────────┤");
        System.out.println("│ Variables           │ public static final │ Any access modifier │");
        System.out.println("│ Methods             │ public abstract   │ Any access modifier │");
        System.out.println("│ Inheritance         │ Multiple          │ Single              │");
        System.out.println("│ Constructor         │ Not allowed       │ Allowed             │");
        System.out.println("│ Instantiation       │ Cannot            │ Cannot              │");
        System.out.println("│ Access Modifiers    │ Public only       │ All types           │");
        System.out.println("│ Default Methods     │ Yes (Java 8+)     │ N/A                 │");
        System.out.println("│ Static Methods      │ Yes (Java 8+)     │ Yes                 │");
        System.out.println("│ Instance Variables  │ No                │ Yes                 │");
        System.out.println("│ Implementation      │ Contract only     │ Partial/Complete    │");
        System.out.println("└─────────────────────┴──────────────────┴─────────────────────┘");
    }
}
