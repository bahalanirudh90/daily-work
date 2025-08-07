package java_8;
// Abstract class example to compare with interfaces
abstract class Vehicle {
    // In abstract classes, variables are NOT automatically public static final
    int wheels = 4; // This is just a regular instance variable (package-private)
    protected String fuelType = "Petrol"; // You can use any access modifier
    private String brand = "Generic"; // Private variable
    public static final String CATEGORY = "VEHICLE"; // You can explicitly make it static final
    
    // Abstract methods (must be implemented by subclasses)
    abstract public void start();
    abstract void stop();

    // Concrete methods (can be inherited as-is)
    public void displayInfo() {
        System.out.println("This vehicle has " + wheels + " wheels");
        System.out.println("Fuel type: " + fuelType);
        System.out.println("Category: " + CATEGORY);
    }
    
    // Getter for private variable
    public String getBrand() {
        return brand;
    }
    
    // Setter for private variable
    public void setBrand(String brand) {
        this.brand = brand;
    }
}

class Motorcycle extends Vehicle {
    // Constructor to set specific values
    public Motorcycle() {
        this.wheels = 2; // Can modify inherited variables (they're not final)
        this.fuelType = "Petrol";
        setBrand("Yamaha");
    }
    
    @Override
    public void start() {
        System.out.println("Motorcycle is starting with kick-start");
    }
    
    @Override
    void stop() {
        System.out.println("Motorcycle is stopping");
    }
}

public class AbstractClassExample {
    public static void main(String[] args) {
        Motorcycle bike = new Motorcycle();
        
        bike.start();
        bike.stop();
        bike.displayInfo();
        
        System.out.println("Brand: " + bike.getBrand());
        
        // Demonstrating the differences:
        System.out.println("\n--- Key Differences ---");
        
        // Abstract class variables can be modified (they're not final by default)
        bike.wheels = 3; // This works!
        bike.fuelType = "Electric"; // This works!
        System.out.println("Modified wheels: " + bike.wheels);
        System.out.println("Modified fuel type: " + bike.fuelType);
        
        // Static final variable (explicitly declared)
        System.out.println("Category (static): " + Vehicle.CATEGORY);
        
        // Interface variables comparison
        System.out.println("\nInterface variables are constants:");
        System.out.println("MotoVehicle.wheels: " + MotoVehicle.wheels);
        // MotoVehicle.wheels = 6; // This would cause compilation error
    }
}
