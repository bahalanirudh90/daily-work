package java_8;

// Basic interface demonstration
interface Vehicle {
    int wheels = 4; // public static final (implicit)
    String fuelType = "Petrol"; // public static final (implicit)
    
    void start(); // public abstract (implicit)
    void stop(); // public abstract (implicit)
    
    default void honk() { // default method (Java 8+)
        System.out.println("Honk! Honk!");
    }
    
    static int getMaxSpeed() { // static method (Java 8+)
        return 120;
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting with " + fuelType);
    }
    
    @Override
    public void stop() {
        System.out.println("Car is stopping");
    }
    // Removed getMaxSpeed() instance method because static interface methods cannot be overridden
}

public class BasicInterface {
    public static void main(String[] args) {
        System.out.println("=== Basic Interface Demo ===\n");
        
        Car car = new Car();
        car.start();
        car.stop();
        car.honk(); // default method
        
        // Interface variables are public static final
        System.out.println("Wheels: " + Vehicle.wheels);
        System.out.println("Fuel Type: " + Vehicle.fuelType);
        
        // Static method call
        System.out.println("Max Speed: " + Vehicle.getMaxSpeed());
        
        // Variables cannot be modified (final)
        // Vehicle.wheels = 6; // Compilation error!
        
        System.out.println("\nInterface Properties:");
        System.out.println("1. Variables are public static final by default");
        System.out.println("2. Methods are public abstract by default");
        System.out.println("3. Can have default and static methods (Java 8+)");
        System.out.println("4. Cannot be instantiated directly");
        System.out.println("5. Supports multiple inheritance");
    }
}
