package java_8;
interface MotoVehicle{
    int wheels = 4; //public static final (implicit)
    String fueltype = "Petrol"; //public static final (implicit)
    
    // These are equivalent to:
    // public static final int wheels = 4;
    // public static final String fueltype = "Petrol";

    void start(); //public abstract (implicit)
    void stop(); //public abstract (implicit)

    default void honk() { //default method
        System.out.println("Honk! Honk!");
    }
    
    // Static method in interface (Java 8+)
    static int getMaxSpeed() {
        return 120; // Default max speed
    }
    
    // Private method in interface (Java 9+)
    private void logOperation(String operation) {
        System.out.println("Vehicle operation: " + operation);
    }
}

// Additional interfaces for multiple inheritance demo
interface Flyable {
    void fly();
    default void takeOff() {
        System.out.println("Taking off...");
    }
}

interface Swimmable {
    void swim();
    default void dive() {
        System.out.println("Diving...");
    }
}

// Interface inheritance
interface ElectricVehicle extends MotoVehicle {
    void chargeBattery();
    
    @Override
    default void honk() {
        System.out.println("Electric beep!");
    }
}

// Functional Interface
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
    
    // Can have default and static methods
    default void printResult(int result) {
        System.out.println("Result: " + result);
    }
}

// Abstract class for comparison
abstract class AbstractVehicle {
    protected String brand; // Can have instance variables
    
    public AbstractVehicle(String brand) { // Can have constructor
        this.brand = brand;
    }
    
    public void displayBrand() { // Concrete method
        System.out.println("Brand: " + brand);
    }
    
    public abstract void engineType(); // Abstract method
}
class Car implements MotoVehicle {
    @Override
    public void start() {
        System.out.println("Car is starting with " + fueltype);
    }

    @Override
    public void stop() {
        System.out.println("Car is stopping.");
    }
}

// Multiple interface implementation
class AmphibianVehicle implements MotoVehicle, Swimmable {
    @Override
    public void start() {
        System.out.println("Amphibian vehicle starting on land");
    }
    
    @Override
    public void stop() {
        System.out.println("Amphibian vehicle stopping");
    }
    
    @Override
    public void swim() {
        System.out.println("Swimming in water");
    }
}

// Flying car implementing three interfaces
class FlyingCar implements MotoVehicle, Flyable, Swimmable {
    @Override
    public void start() {
        System.out.println("Flying car starting");
    }
    
    @Override
    public void stop() {
        System.out.println("Flying car stopping");
    }
    
    @Override
    public void fly() {
        System.out.println("Flying in the air");
    }
    
    @Override
    public void swim() {
        System.out.println("Swimming underwater");
    }
}

// Electric vehicle implementation
class Tesla implements ElectricVehicle {
    @Override
    public void start() {
        System.out.println("Tesla starting silently");
    }
    
    @Override
    public void stop() {
        System.out.println("Tesla stopping");
    }
    
    @Override
    public void chargeBattery() {
        System.out.println("Charging Tesla battery");
    }
}

// Abstract class implementation
class SportsCar extends AbstractVehicle {
    public SportsCar(String brand) {
        super(brand);
    }
    
    @Override
    public void engineType() {
        System.out.println("V8 Engine");
    }
}

// Demo classes
class MultipleInterfaceDemo {
    public static void demonstrate() {
        AmphibianVehicle amphibian = new AmphibianVehicle();
        amphibian.start();
        amphibian.swim();
        amphibian.dive(); // default method from Swimmable
        
        System.out.println("\nFlying Car Demo:");
        FlyingCar flyingCar = new FlyingCar();
        flyingCar.start();
        flyingCar.fly();
        flyingCar.swim();
        flyingCar.honk(); // default method from MotoVehicle
    }
}

class InterfaceInheritanceDemo {
    public static void demonstrate() {
        Tesla tesla = new Tesla();
        tesla.start();
        tesla.chargeBattery();
        tesla.honk(); // Overridden in ElectricVehicle interface
    }
}

class FunctionalInterfaceDemo {
    public static void demonstrate() {
        // Lambda expression with functional interface
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        
        int sum = add.calculate(5, 3);
        int product = multiply.calculate(5, 3);
        
        System.out.println("5 + 3 = " + sum);
        System.out.println("5 * 3 = " + product);
        
        add.printResult(sum); // Using default method
        multiply.printResult(product);
        
        // Method reference
        Calculator subtract = Integer::sum; // This won't subtract, just demo
        System.out.println("Method reference demo: " + subtract.calculate(10, 5));
    }
}

class InterfaceVsAbstractDemo {
    public static void demonstrate() {
        System.out.println("Abstract Class Example:");
        SportsCar ferrari = new SportsCar("Ferrari");
        ferrari.displayBrand(); // Concrete method from abstract class
        ferrari.engineType(); // Implemented abstract method
        
        System.out.println("\nInterface vs Abstract Class:");
        System.out.println("Interface: Multiple inheritance, only constants, all methods public");
        System.out.println("Abstract Class: Single inheritance, instance variables, any access modifier");
    }
}

class PolymorphismDemo {
    public static void demonstrate() {
        // Interface polymorphism
        MotoVehicle[] vehicles = {
            new Car(),
            new Tesla(),
            new FlyingCar()
        };
        
        System.out.println("Polymorphism with interfaces:");
        for (MotoVehicle vehicle : vehicles) {
            vehicle.start(); // Different implementations called
            vehicle.honk();  // May use default or overridden
            System.out.println("---");
        }
        
        // Interface reference
        Flyable flyingObject = new FlyingCar();
        flyingObject.fly(); // Only Flyable methods accessible
        flyingObject.takeOff(); // Default method
    }
}
public class InterfacePractice {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.start();
        myCar.stop();

        myCar.honk(); // Calling the default method
        
        // Demonstrating interface variables are static and final
        System.out.println("Number of wheels: " + MotoVehicle.wheels);
        System.out.println("Fuel type: " + MotoVehicle.fueltype);
        
        // These variables are accessible without creating an instance
        // because they are STATIC
        System.out.println("Wheels (accessed via interface): " + MotoVehicle.wheels);
        
        // This would cause a compilation error because variables are FINAL:
        // MotoVehicle.wheels = 6; // Cannot assign a value to final variable 'wheels'
        
        // They are also PUBLIC, so accessible from anywhere
        System.out.println("Accessible from anywhere because they're public");
        
        System.out.println("\n=== Additional Interface Concepts ===");
        
        // 1. Multiple Interface Implementation
        System.out.println("\n1. Multiple Interface Implementation:");
        MultipleInterfaceDemo.demonstrate();
        
        // 2. Interface Inheritance
        System.out.println("\n2. Interface Inheritance:");
        InterfaceInheritanceDemo.demonstrate();
        
        // 3. Static Methods in Interface (Java 8+)
        System.out.println("\n3. Static Methods in Interface:");
        System.out.println("Max speed from interface: " + MotoVehicle.getMaxSpeed());
        
        // 4. Functional Interface Example
        System.out.println("\n4. Functional Interface:");
        FunctionalInterfaceDemo.demonstrate();
        
        // 5. Interface vs Abstract Class
        System.out.println("\n5. Interface vs Abstract Class:");
        InterfaceVsAbstractDemo.demonstrate();
        
        // 6. Polymorphism with Interfaces
        System.out.println("\n6. Polymorphism with Interfaces:");
        PolymorphismDemo.demonstrate();
    }
}
