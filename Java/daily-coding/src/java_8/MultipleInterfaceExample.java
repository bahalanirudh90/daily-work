package java_8;
// Multiple interface implementation demonstration

interface Flyable {
    void fly();
    
    default void takeOff() {
        System.out.println("Taking off into the sky...");
    }
}

interface Swimmable {
    void swim();
    
    default void dive() {
        System.out.println("Diving underwater...");
    }
}

interface Drivable {
    void drive();
    
    default void startEngine() {
        System.out.println("Engine started!");
    }
}

// Class implementing multiple interfaces
class AmphibianVehicle implements Drivable, Swimmable {
    @Override
    public void drive() {
        System.out.println("Driving on land");
    }
    
    @Override
    public void swim() {
        System.out.println("Swimming in water");
    }
}

// Class implementing three interfaces
class FlyingCar implements Drivable, Flyable, Swimmable {
    @Override
    public void drive() {
        System.out.println("Flying car driving on road");
    }
    
    @Override
    public void fly() {
        System.out.println("Flying car soaring through air");
    }
    
    @Override
    public void swim() {
        System.out.println("Flying car swimming underwater");
    }
}

public class MultipleInterfaceExample {
    public static void main(String[] args) {
        System.out.println("=== Multiple Interface Implementation ===\n");
        
        // Amphibian vehicle demo
        System.out.println("1. Amphibian Vehicle:");
        AmphibianVehicle amphibian = new AmphibianVehicle();
        amphibian.drive();
        amphibian.startEngine(); // default method from Drivable
        amphibian.swim();
        amphibian.dive(); // default method from Swimmable
        
        System.out.println("\n2. Flying Car:");
        FlyingCar flyingCar = new FlyingCar();
        flyingCar.drive();
        flyingCar.fly();
        flyingCar.takeOff(); // default method from Flyable
        flyingCar.swim();
        flyingCar.dive(); // default method from Swimmable
        
        // Interface reference polymorphism
        System.out.println("\n3. Interface Reference Polymorphism:");
        Flyable flyingObject = new FlyingCar();
        flyingObject.fly();
        flyingObject.takeOff();
        
        Swimmable swimmingObject = new FlyingCar();
        swimmingObject.swim();
        swimmingObject.dive();
        
        System.out.println("\nKey Points:");
        System.out.println("- Java allows multiple interface implementation");
        System.out.println("- Solves multiple inheritance problem");
        System.out.println("- Interface reference can point to implementing class");
        System.out.println("- Only interface methods are accessible through interface reference");
    }
}

