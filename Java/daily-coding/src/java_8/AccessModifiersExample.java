package java_8;
// Comprehensive demonstration of Access Modifiers in Java

// Parent class demonstrating all access modifiers
class Parent {
    // 1. PRIVATE - Most restrictive
    private String privateVar = "Private Variable";
    private void privateMethod() {
        System.out.println("Private method called");
    }
    
    // 2. PACKAGE-PRIVATE (Default) - No modifier
    String packageVar = "Package-Private Variable";
    void packageMethod() {
        System.out.println("Package-private method called");
    }
    
    // 3. PROTECTED - Accessible to subclasses and same package
    protected String protectedVar = "Protected Variable";
    protected void protectedMethod() {
        System.out.println("Protected method called");
    }
    
    // 4. PUBLIC - Most accessible
    public String publicVar = "Public Variable";
    public void publicMethod() {
        System.out.println("Public method called");
    }
    
    // Constructor
    public Parent() {
        System.out.println("Parent constructor");
    }
    
    // Method to demonstrate access within same class
    public void demonstrateAccessWithinClass() {
        System.out.println("\n--- Access from WITHIN Parent class ---");
        System.out.println("Private: " + privateVar);        // ✅ Accessible
        System.out.println("Package: " + packageVar);        // ✅ Accessible
        System.out.println("Protected: " + protectedVar);    // ✅ Accessible
        System.out.println("Public: " + publicVar);          // ✅ Accessible
        
        privateMethod();    // ✅ Accessible
        packageMethod();    // ✅ Accessible
        protectedMethod();  // ✅ Accessible
        publicMethod();     // ✅ Accessible
    }
    
    // Getter for private variable (encapsulation)
    public String getPrivateVar() {
        return privateVar;
    }
    
    // Setter for private variable
    public void setPrivateVar(String value) {
        this.privateVar = value;
    }
}

// Child class in SAME package
class Child extends Parent {
    public Child() {
        super(); // Call parent constructor
        System.out.println("Child constructor");
    }
    
    public void demonstrateInheritanceAccess() {
        System.out.println("\n--- Access from CHILD class (same package) ---");
        
        // Private: NOT accessible directly
        // System.out.println("Private: " + privateVar);     // ❌ Compilation Error
        System.out.println("Private (via getter): " + getPrivateVar()); // ✅ Via public method
        
        // Package-private: Accessible (same package)
        System.out.println("Package: " + packageVar);        // ✅ Accessible
        
        // Protected: Accessible (inheritance)
        System.out.println("Protected: " + protectedVar);    // ✅ Accessible
        
        // Public: Accessible (always)
        System.out.println("Public: " + publicVar);          // ✅ Accessible
        
        // Method calls
        // privateMethod();    // ❌ Compilation Error
        packageMethod();       // ✅ Accessible (same package)
        protectedMethod();     // ✅ Accessible (inheritance)
        publicMethod();        // ✅ Accessible (always)
    }
    
    // Overriding protected method
    @Override
    protected void protectedMethod() {
        System.out.println("Overridden protected method in Child");
    }
    
    // Can't reduce visibility when overriding
    // private void protectedMethod() { } // ❌ Would cause compilation error
    
    // Can increase visibility when overriding
    @Override
    public void packageMethod() { // Package-private becomes public
        System.out.println("Overridden package method (now public) in Child");
    }
}

// Class in SAME package (not inheritance)
class SamePackageClass {
    public void demonstrateSamePackageAccess() {
        System.out.println("\n--- Access from SAME PACKAGE class (no inheritance) ---");
        
        Parent parent = new Parent();
        
        // Private: NOT accessible
        // System.out.println("Private: " + parent.privateVar);     // ❌ Compilation Error
        System.out.println("Private (via getter): " + parent.getPrivateVar()); // ✅ Via public method
        
        // Package-private: Accessible (same package)
        System.out.println("Package: " + parent.packageVar);     // ✅ Accessible
        
        // Protected: Accessible (same package)
        System.out.println("Protected: " + parent.protectedVar); // ✅ Accessible
        
        // Public: Accessible (always)
        System.out.println("Public: " + parent.publicVar);       // ✅ Accessible
        
        // Method calls
        // parent.privateMethod();    // ❌ Compilation Error
        parent.packageMethod();       // ✅ Accessible (same package)
        parent.protectedMethod();     // ✅ Accessible (same package)
        parent.publicMethod();        // ✅ Accessible (always)
    }
}

public class AccessModifiersExample {
    public static void main(String[] args) {
        System.out.println("=== Java Access Modifiers Demonstration ===");
        
        // Create instances
        Parent parent = new Parent();
        Child child = new Child();
        SamePackageClass samePackage = new SamePackageClass();
        
        // Demonstrate access from within class
        parent.demonstrateAccessWithinClass();
        
        // Demonstrate inheritance access
        child.demonstrateInheritanceAccess();
        
        // Demonstrate same package access
        samePackage.demonstrateSamePackageAccess();
        
        // Demonstrate access from main method (same package)
        System.out.println("\n--- Access from MAIN method (same package) ---");
        
        // Direct access to parent object
        // System.out.println("Private: " + parent.privateVar);     // ❌ Compilation Error
        System.out.println("Package: " + parent.packageVar);        // ✅ Same package
        System.out.println("Protected: " + parent.protectedVar);    // ✅ Same package
        System.out.println("Public: " + parent.publicVar);          // ✅ Always accessible
        
        // Access modifiers summary
        printAccessModifiersSummary();
        
        // Inheritance rules
        printInheritanceRules();
        
        // Best practices
        printBestPractices();
    }
    
    public static void printAccessModifiersSummary() {
        System.out.println("\n=== ACCESS MODIFIERS SUMMARY ===");
        System.out.println("┌─────────────┬─────────────┬─────────────┬─────────────┬─────────────┐");
        System.out.println("│   Modifier  │ Same Class  │ Same Package│  Subclass   │  Everywhere │");
        System.out.println("├─────────────┼─────────────┼─────────────┼─────────────┼─────────────┤");
        System.out.println("│   private   │      ✅     │      ❌     │      ❌     │      ❌     │");
        System.out.println("│   default   │      ✅     │      ✅     │      ❌     │      ❌     │");
        System.out.println("│  protected  │      ✅     │      ✅     │      ✅     │      ❌     │");
        System.out.println("│   public    │      ✅     │      ✅     │      ✅     │      ✅     │");
        System.out.println("└─────────────┴─────────────┴─────────────┴─────────────┴─────────────┘");
    }
    
    public static void printInheritanceRules() {
        System.out.println("\n=== INHERITANCE RULES ===");
        System.out.println("1. Private members are NOT inherited");
        System.out.println("2. Package-private members inherited if in same package");
        System.out.println("3. Protected members are always inherited");
        System.out.println("4. Public members are always inherited");
        System.out.println("5. Cannot reduce visibility when overriding methods");
        System.out.println("6. Can increase visibility when overriding methods");
        System.out.println("7. Access modifiers don't change during inheritance (unlike C++)");
    }
    
    public static void printBestPractices() {
        System.out.println("\n=== BEST PRACTICES ===");
        System.out.println("1. Use PRIVATE for internal implementation details");
        System.out.println("2. Use PROTECTED for subclass-specific functionality");
        System.out.println("3. Use PUBLIC for class interface/API");
        System.out.println("4. Use package-private (default) for package-internal utilities");
        System.out.println("5. Follow principle of least privilege - start with most restrictive");
        System.out.println("6. Use getters/setters for controlled access to private fields");
        System.out.println("7. Consider using protected for methods that subclasses might override");
    }
}

