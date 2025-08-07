package java_8;
// Interface inheritance demonstration

interface Animal {
    void eat();
    
    default void sleep() {
        System.out.println("Animal is sleeping");
    }
}

// Interface extending another interface
interface Mammal extends Animal {
    void breathe();
    
    default void giveBirth() {
        System.out.println("Giving birth to live young");
    }
}

// Interface extending multiple interfaces
interface FlyingAnimal extends Animal {
    void fly();
    
    default void migrate() {
        System.out.println("Migrating to warmer regions");
    }
}

// Interface extending FlyingAnimal (which already extends Animal)
interface Bird extends FlyingAnimal {
    void chirp();
    
    @Override
    default void sleep() {
        System.out.println("Bird is sleeping in nest");
    }
}

// Multiple interface inheritance
interface AquaticAnimal extends Animal {
    void swim();
    
    default void holdBreath() {
        System.out.println("Holding breath underwater");
    }
}

// Class implementing inherited interface
class Dog implements Mammal {
    @Override
    public void eat() {
        System.out.println("Dog is eating dog food");
    }
    
    @Override
    public void breathe() {
        System.out.println("Dog is breathing through lungs");
    }
}

// Class implementing complex interface hierarchy
class Eagle implements Bird {
    @Override
    public void eat() {
        System.out.println("Eagle is hunting prey");
    }
    
    @Override
    public void fly() {
        System.out.println("Eagle is soaring high");
    }
    
    @Override
    public void chirp() {
        System.out.println("Eagle is screeching");
    }
}

// Class implementing multiple interface inheritance
class Dolphin implements Mammal, AquaticAnimal {
    @Override
    public void eat() {
        System.out.println("Dolphin is eating fish");
    }
    
    @Override
    public void breathe() {
        System.out.println("Dolphin is breathing air at surface");
    }
    
    @Override
    public void swim() {
        System.out.println("Dolphin is swimming gracefully");
    }
}

public class InterfaceInheritanceExample {
    public static void main(String[] args) {
        System.out.println("=== Interface Inheritance Demo ===\n");
        
        // Dog implementing Mammal (which extends Animal)
        System.out.println("1. Dog (Mammal):");
        Dog dog = new Dog();
        dog.eat(); // from Animal
        dog.breathe(); // from Mammal
        dog.sleep(); // default from Animal
        dog.giveBirth(); // default from Mammal
        
        // Eagle implementing Bird (which extends Animal and FlyingAnimal)
        System.out.println("\n2. Eagle (Bird):");
        Eagle eagle = new Eagle();
        eagle.eat(); // from Animal
        eagle.fly(); // from FlyingAnimal
        eagle.chirp(); // from Bird
        eagle.sleep(); // overridden default from Bird
        eagle.migrate(); // default from FlyingAnimal
        
        // Dolphin implementing multiple interfaces
        System.out.println("\n3. Dolphin (Mammal + AquaticAnimal):");
        Dolphin dolphin = new Dolphin();
        dolphin.eat(); // from Animal
        dolphin.breathe(); // from Mammal
        dolphin.swim(); // from AquaticAnimal
        dolphin.giveBirth(); // default from Mammal
        dolphin.holdBreath(); // default from AquaticAnimal
        
        // Polymorphism with interface hierarchy
        System.out.println("\n4. Polymorphism with Interface Hierarchy:");
        Animal[] animals = {dog, eagle, dolphin};
        
        for (Animal animal : animals) {
            animal.eat(); // Polymorphic behavior
            animal.sleep(); // May use default or overridden
        }
        
        System.out.println("\nKey Points:");
        System.out.println("- Interfaces can extend other interfaces");
        System.out.println("- Interface can extend multiple interfaces");
        System.out.println("- Implementing class must implement all abstract methods");
        System.out.println("- Default methods can be overridden in child interfaces");
        System.out.println("- Creates flexible inheritance hierarchies");
    }
}

