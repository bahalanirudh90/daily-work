package java_8;
// This file demonstrates cross-package access modifiers
// Note: This would normally be in a different package, but for simplicity 
// we'll simulate it with comments showing what would happen

class CrossPackageParent {
    private String privateVar = "Private from another package";
    String packageVar = "Package-private from another package";  
    protected String protectedVar = "Protected from another package";
    public String publicVar = "Public from another package";
    
    private void privateMethod() {
        System.out.println("Private method from another package");
    }
    
    void packageMethod() {
        System.out.println("Package method from another package");
    }
    
    protected void protectedMethod() {
        System.out.println("Protected method from another package");
    }
    
    public void publicMethod() {
        System.out.println("Public method from another package");
    }
    
    public String getPrivateVar() {
        return privateVar;
    }
}

// Simulating a class in DIFFERENT package that extends CrossPackageParent
class CrossPackageChild extends CrossPackageParent {
    
    public void demonstrateCrossPackageInheritance() {
        System.out.println("\n--- Cross-Package Inheritance Access ---");
        
        // What would be accessible if this class was in a different package:
        
        // Private: NOT accessible
        // System.out.println("Private: " + privateVar);     // ❌ Compilation Error
        System.out.println("Private (via getter): " + getPrivateVar()); // ✅ Via public method
        
        // Package-private: NOT accessible (different package)
        // System.out.println("Package: " + packageVar);     // ❌ Compilation Error
        System.out.println("Package: NOT accessible from different package");
        
        // Protected: Accessible (inheritance, even across packages)
        System.out.println("Protected: " + protectedVar);    // ✅ Accessible via inheritance
        
        // Public: Accessible (always)
        System.out.println("Public: " + publicVar);          // ✅ Always accessible
        
        // Method access
        // privateMethod();     // ❌ Compilation Error
        // packageMethod();     // ❌ Compilation Error (different package)
        protectedMethod();      // ✅ Accessible via inheritance
        publicMethod();         // ✅ Always accessible
    }
}

// Simulating a class in DIFFERENT package with NO inheritance
class CrossPackageUnrelated {
    
    public void demonstrateCrossPackageAccess() {
        System.out.println("\n--- Cross-Package Non-Inheritance Access ---");
        
        CrossPackageParent parent = new CrossPackageParent();
        
        // What would be accessible if this class was in a different package:
        
        // Private: NOT accessible
        // System.out.println("Private: " + parent.privateVar);     // ❌ Compilation Error
        System.out.println("Private (via getter): " + parent.getPrivateVar()); // ✅ Via public method
        
        // Package-private: NOT accessible (different package)
        // System.out.println("Package: " + parent.packageVar);     // ❌ Compilation Error
        System.out.println("Package: NOT accessible from different package");
        
        // Protected: NOT accessible (different package, no inheritance)
        // System.out.println("Protected: " + parent.protectedVar); // ❌ Compilation Error
        System.out.println("Protected: NOT accessible (no inheritance, different package)");
        
        // Public: Accessible (always)
        System.out.println("Public: " + parent.publicVar);          // ✅ Always accessible
        
        // Method access
        // parent.privateMethod();     // ❌ Compilation Error
        // parent.packageMethod();     // ❌ Compilation Error (different package)
        // parent.protectedMethod();   // ❌ Compilation Error (different package, no inheritance)
        parent.publicMethod();         // ✅ Always accessible
    }
}

public class CrossPackageAccessExample {
    public static void main(String[] args) {
        System.out.println("=== Cross-Package Access Demonstration ===");
        
        CrossPackageChild child = new CrossPackageChild();
        CrossPackageUnrelated unrelated = new CrossPackageUnrelated();
        
        child.demonstrateCrossPackageInheritance();
        unrelated.demonstrateCrossPackageAccess();
        
        // Real-world scenarios
        System.out.println("\n=== REAL-WORLD SCENARIOS ===");
        
        System.out.println("\n1. PRIVATE - Internal Implementation:");
        System.out.println("   - Database connection details");
        System.out.println("   - Internal calculations");
        System.out.println("   - Helper methods for class-specific logic");
        
        System.out.println("\n2. PACKAGE-PRIVATE - Package Utilities:");
        System.out.println("   - Utility classes within same package");
        System.out.println("   - Internal APIs not meant for external use");
        System.out.println("   - Configuration classes for package");
        
        System.out.println("\n3. PROTECTED - Inheritance Support:");
        System.out.println("   - Methods that subclasses might need to override");
        System.out.println("   - Common functionality for family of classes");
        System.out.println("   - Template method pattern implementations");
        
        System.out.println("\n4. PUBLIC - External Interface:");
        System.out.println("   - API methods for external clients");
        System.out.println("   - Main functionality of the class");
        System.out.println("   - Constants that other classes need");
        
        // Package visibility table
        printPackageVisibilityTable();
        
        // Common mistakes
        printCommonMistakes();
    }
    
    public static void printPackageVisibilityTable() {
        System.out.println("\n=== PACKAGE VISIBILITY TABLE ===");
        System.out.println("┌─────────────┬─────────────┬─────────────┬─────────────┐");
        System.out.println("│   Modifier  │ Same Package│Different Pkg│Different Pkg│");
        System.out.println("│             │             │(Inheritance)│(No Inherit) │");
        System.out.println("├─────────────┼─────────────┼─────────────┼─────────────┤");
        System.out.println("│   private   │      ❌     │      ❌     │      ❌     │");
        System.out.println("│   default   │      ✅     │      ❌     │      ❌     │");
        System.out.println("│  protected  │      ✅     │      ✅     │      ❌     │");
        System.out.println("│   public    │      ✅     │      ✅     │      ✅     │");
        System.out.println("└─────────────┴─────────────┴─────────────┴─────────────┘");
    }
    
    public static void printCommonMistakes() {
        System.out.println("\n=== COMMON MISTAKES ===");
        System.out.println("❌ Making everything public (breaks encapsulation)");
        System.out.println("❌ Using package-private when protected is needed");
        System.out.println("❌ Trying to reduce visibility when overriding methods");
        System.out.println("❌ Forgetting that protected is accessible in same package");
        System.out.println("❌ Not understanding the difference between protected and package-private");
        System.out.println("❌ Making fields public instead of using getters/setters");
        
        System.out.println("\n✅ CORRECT APPROACHES:");
        System.out.println("✅ Start with most restrictive access modifier");
        System.out.println("✅ Use private for implementation details");
        System.out.println("✅ Use protected for subclass extension points");
        System.out.println("✅ Use public only for the class's main interface");
        System.out.println("✅ Provide controlled access through methods");
    }
}

