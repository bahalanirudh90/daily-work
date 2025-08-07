package collections;

import java.util.*;

public class HashsetExample {
    
    public static void main(String[] args) {
        System.out.println("=== HashSet Basic Demo ===\n");
        
        // 1. Creating and Basic Operations
        System.out.println("1. Creating HashSet and Basic Operations:");
        HashSet<String> fruits = new HashSet<>();
        
        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple"); // Duplicate - won't be added
        System.out.println("Fruits set: " + fruits);
        System.out.println("Size: " + fruits.size()); // Will be 3, not 4
        
        // 2. Checking existence
        System.out.println("\n2. Checking Existence:");
        System.out.println("Contains 'Apple': " + fruits.contains("Apple"));
        System.out.println("Contains 'Grapes': " + fruits.contains("Grapes"));
        System.out.println("Is empty: " + fruits.isEmpty());
        
        // 3. Removing elements
        System.out.println("\n3. Removing Elements:");
        boolean removed = fruits.remove("Banana");
        System.out.println("Removed 'Banana': " + removed);
        System.out.println("After removal: " + fruits);
        
        boolean notRemoved = fruits.remove("Grapes");
        System.out.println("Tried to remove 'Grapes': " + notRemoved);
        
        // 4. Iteration methods
        System.out.println("\n4. Different Ways to Iterate:");
        
        // Enhanced for loop
        System.out.print("Enhanced for loop: ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();
        
        // Iterator
        System.out.print("Using Iterator: ");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // forEach with lambda
        System.out.print("Using forEach with lambda: ");
        fruits.forEach(fruit -> System.out.print(fruit + " "));
        System.out.println();
        
        // 5. Bulk operations
        System.out.println("\n5. Bulk Operations:");
        HashSet<String> moreFruits = new HashSet<>();
        moreFruits.add("Mango");
        moreFruits.add("Pineapple");
        moreFruits.add("Apple"); // Already exists in fruits
        
        fruits.addAll(moreFruits); // Add all elements
        System.out.println("After addAll: " + fruits);
        
        // Creating another set for set operations
        HashSet<String> tropicalFruits = new HashSet<>();
        tropicalFruits.add("Mango");
        tropicalFruits.add("Pineapple");
        tropicalFruits.add("Coconut");
        
        // 6. Set operations
        System.out.println("\n6. Set Operations:");
        System.out.println("Original fruits: " + fruits);
        System.out.println("Tropical fruits: " + tropicalFruits);
        
        // Union (using a copy to preserve original)
        HashSet<String> union = new HashSet<>(fruits);
        union.addAll(tropicalFruits);
        System.out.println("Union: " + union);
        
        // Intersection
        HashSet<String> intersection = new HashSet<>(fruits);
        intersection.retainAll(tropicalFruits);
        System.out.println("Intersection: " + intersection);
        
        // Difference
        HashSet<String> difference = new HashSet<>(fruits);
        difference.removeAll(tropicalFruits);
        System.out.println("Difference (fruits - tropical): " + difference);
        
        // 7. Converting between collections
        System.out.println("\n7. Converting Between Collections:");
        
        // Array to HashSet
        String[] fruitsArray = {"Apple", "Banana", "Apple", "Cherry"};
        HashSet<String> fromArray = new HashSet<>(Arrays.asList(fruitsArray));
        System.out.println("From array (duplicates removed): " + fromArray);
        
        // List to HashSet (removes duplicates)
        ArrayList<String> fruitsWithDuplicates = new ArrayList<>();
        fruitsWithDuplicates.add("Apple");
        fruitsWithDuplicates.add("Banana");
        fruitsWithDuplicates.add("Apple");
        fruitsWithDuplicates.add("Cherry");
        fruitsWithDuplicates.add("Banana");
        
        System.out.println("Original list with duplicates: " + fruitsWithDuplicates);
        HashSet<String> fromList = new HashSet<>(fruitsWithDuplicates);
        System.out.println("HashSet from list (duplicates removed): " + fromList);
        
        // HashSet back to List
        ArrayList<String> backToList = new ArrayList<>(fromList);
        System.out.println("Back to list: " + backToList);
        
        // 8. HashSet with numbers
        System.out.println("\n8. HashSet with Numbers:");
        HashSet<Integer> numbers = new HashSet<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(2); // Duplicate
        numbers.add(1);
        
        System.out.println("Numbers set: " + numbers);
        System.out.println("Size: " + numbers.size());
        
        // Finding min and max (need to convert to collection for Collections methods)
        if (!numbers.isEmpty()) {
            System.out.println("Min: " + Collections.min(numbers));
            System.out.println("Max: " + Collections.max(numbers));
        }
        
        // 9. HashSet characteristics
        System.out.println("\n=== HashSet Key Characteristics ===");
        System.out.println("1. No duplicates allowed");
        System.out.println("2. No ordering guarantee");
        System.out.println("3. O(1) average time for add, remove, contains");
        System.out.println("4. Allows one null value");
        System.out.println("5. Not thread-safe");
        System.out.println("6. Uses hashing for fast operations");
        
        // Demonstrating null handling
        HashSet<String> nullDemo = new HashSet<>();
        nullDemo.add(null);
        nullDemo.add("Hello");
        nullDemo.add(null); // Only one null allowed
        System.out.println("Null handling demo: " + nullDemo);
        
        // 10. Common use cases
        System.out.println("\n=== Common Use Cases ===");
        
        // Remove duplicates from data
        System.out.println("1. Removing Duplicates:");
        int[] numbersWithDuplicates = {1, 2, 3, 2, 4, 1, 5, 3};
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        for (int num : numbersWithDuplicates) {
            uniqueNumbers.add(num);
        }
        System.out.println("Original array: " + Arrays.toString(numbersWithDuplicates));
        System.out.println("Unique numbers: " + uniqueNumbers);
        
        // Fast membership testing
        System.out.println("\n2. Fast Membership Testing:");
        HashSet<String> validUsers = new HashSet<>();
        validUsers.add("john");
        validUsers.add("jane");
        validUsers.add("bob");
        validUsers.add("alice");
        
        String[] loginAttempts = {"john", "hacker", "jane", "unknown", "bob"};
        System.out.println("Valid users: " + validUsers);
        System.out.print("Login attempts: ");
        for (String user : loginAttempts) {
            if (validUsers.contains(user)) {
                System.out.print(user + "(✓) ");
            } else {
                System.out.print(user + "(✗) ");
            }
        }
        System.out.println();
        
        // Finding unique elements in multiple collections
        System.out.println("\n3. Finding Unique Elements Across Collections:");
        String[] group1 = {"Alice", "Bob", "Charlie"};
        String[] group2 = {"Bob", "Diana", "Eve"};
        String[] group3 = {"Charlie", "Frank", "Alice"};
        
        HashSet<String> allPeople = new HashSet<>();
        allPeople.addAll(Arrays.asList(group1));
        allPeople.addAll(Arrays.asList(group2));
        allPeople.addAll(Arrays.asList(group3));
        
        System.out.println("Group 1: " + Arrays.toString(group1));
        System.out.println("Group 2: " + Arrays.toString(group2));
        System.out.println("Group 3: " + Arrays.toString(group3));
        System.out.println("All unique people: " + allPeople);
        
        // 11. Comparing with other Set types
        System.out.println("\n=== Set Type Comparison ===");
        
        // HashSet - no ordering
        Set<String> hashSet = new HashSet<>();
        hashSet.add("C");
        hashSet.add("A");
        hashSet.add("B");
        System.out.println("HashSet (no ordering): " + hashSet);
        
        // LinkedHashSet - insertion order
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("C");
        linkedHashSet.add("A");
        linkedHashSet.add("B");
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);
        
        // TreeSet - sorted order
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("C");
        treeSet.add("A");
        treeSet.add("B");
        System.out.println("TreeSet (sorted order): " + treeSet);
        
        System.out.println("\nWhen to use:");
        System.out.println("- HashSet: Default choice, fastest for unique elements");
        System.out.println("- LinkedHashSet: When you need insertion order preserved");
        System.out.println("- TreeSet: When you need sorted unique elements");
        
        // 12. Clear operation
        System.out.println("\n12. Clear Operation:");
        HashSet<String> tempSet = new HashSet<>(fruits);
        System.out.println("Temp set before clear: " + tempSet);
        tempSet.clear();
        System.out.println("Temp set after clear: " + tempSet);
        System.out.println("Is temp set empty: " + tempSet.isEmpty());
    }
}
