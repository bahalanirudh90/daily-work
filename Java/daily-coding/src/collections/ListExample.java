package collections;

import java.util.*;

public class ListExample {
    
    public static void main(String[] args) {
        System.out.println("=== ArrayList Operations Demo ===\n");
        
        // Creating ArrayList
        ArrayList<String> fruits = new ArrayList<>();
        
        // 1. Adding elements
        System.out.println("1. Adding elements:");
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add(1, "Mango"); // Insert at specific index
        System.out.println("Fruits list: " + fruits);
        
        // 2. Accessing elements
        System.out.println("\n2. Accessing elements:");
        System.out.println("First fruit: " + fruits.get(0));
        System.out.println("Last fruit: " + fruits.get(fruits.size() - 1));
        
        // 3. Size and capacity operations
        System.out.println("\n3. Size operations:");
        System.out.println("Size: " + fruits.size());
        System.out.println("Is empty: " + fruits.isEmpty());
        
        // 4. Searching operations
        System.out.println("\n4. Searching operations:");
        System.out.println("Contains 'Apple': " + fruits.contains("Apple"));
        System.out.println("Index of 'Banana': " + fruits.indexOf("Banana"));
        System.out.println("Last index of 'Orange': " + fruits.lastIndexOf("Orange"));
        
        // 5. Modifying elements
        System.out.println("\n5. Modifying elements:");
        fruits.set(2, "Grapes"); // Replace element at index 2
        System.out.println("After replacing index 2 with 'Grapes': " + fruits);
        
        // 6. Removing elements
        System.out.println("\n6. Removing elements:");
        fruits.remove("Mango"); // Remove by value
        System.out.println("After removing 'Mango': " + fruits);
        fruits.remove(0); // Remove by index
        System.out.println("After removing index 0: " + fruits);
        
        // 7. Bulk operations
        System.out.println("\n7. Bulk operations:");
        ArrayList<String> moreFruits = new ArrayList<>();
        moreFruits.add("Pineapple");
        moreFruits.add("Strawberry");
        fruits.addAll(moreFruits); // Add all elements
        System.out.println("After adding more fruits: " + fruits);
        
        // 8. Iteration methods
        System.out.println("\n8. Different ways to iterate:");
        
        // Traditional for loop
        System.out.print("Traditional for loop: ");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.print(fruits.get(i) + " ");
        }
        System.out.println();
        
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
        
        // 9. Converting to array
        System.out.println("\n9. Converting to array:");
        String[] fruitsArray = fruits.toArray(new String[0]);
        System.out.println("Array: " + Arrays.toString(fruitsArray));
        
        // 10. Sublist operations
        System.out.println("\n10. Sublist operations:");
        List<String> subList = fruits.subList(1, 3);
        System.out.println("Sublist (index 1 to 2): " + subList);
        
        // 11. Sorting and reversing
        System.out.println("\n11. Sorting and reversing:");
        Collections.sort(fruits);
        System.out.println("Sorted fruits: " + fruits);
        Collections.reverse(fruits);
        System.out.println("Reversed fruits: " + fruits);
        
        // 12. Working with different data types
        System.out.println("\n=== Working with Integer ArrayList ===");
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("Original numbers: " + numbers);
        
        Collections.sort(numbers);
        System.out.println("Sorted numbers: " + numbers);
        
        // Finding min and max
        System.out.println("Min: " + Collections.min(numbers));
        System.out.println("Max: " + Collections.max(numbers));
        
        // 13. Clear and capacity
        System.out.println("\n13. Clear operations:");
        ArrayList<String> tempList = new ArrayList<>(fruits);
        System.out.println("Temp list before clear: " + tempList);
        tempList.clear();
        System.out.println("Temp list after clear: " + tempList);
        System.out.println("Is temp list empty: " + tempList.isEmpty());
        
        // 14. List interface methods
        System.out.println("\n=== List Interface Methods ===");
        demonstrateListInterface();
        
        // 15. ArrayList vs other List implementations
        System.out.println("\n=== Comparing List Implementations ===");
        compareListImplementations();
    }
    
    public static void demonstrateListInterface() {
        // Using List interface reference
        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");
        
        System.out.println("Colors list: " + colors);
        
        // List-specific operations
        System.out.println("First occurrence of 'Blue': " + colors.indexOf("Blue"));
        
        // Replace all occurrences
        Collections.replaceAll(colors, "Blue", "Navy Blue");
        System.out.println("After replacing Blue with Navy Blue: " + colors);
        
        // Retain only specific elements
        List<String> toRetain = Arrays.asList("Red", "Green");
        List<String> colorsCopy = new ArrayList<>(colors);
        colorsCopy.retainAll(toRetain);
        System.out.println("After retaining only Red and Green: " + colorsCopy);
    }
    
    public static void compareListImplementations() {
        // ArrayList
        List<String> arrayList = new ArrayList<>();
        arrayList.add("ArrayList");
        arrayList.add("Fast random access");
        arrayList.add("Slow insertion/deletion in middle");
        
        // LinkedList
        List<String> linkedList = new LinkedList<>();
        linkedList.add("LinkedList");
        linkedList.add("Slow random access");
        linkedList.add("Fast insertion/deletion anywhere");
        
        // Vector (synchronized)
        List<String> vector = new Vector<>();
        vector.add("Vector");
        vector.add("Synchronized");
        vector.add("Legacy class");
        
        System.out.println("ArrayList characteristics: " + arrayList);
        System.out.println("LinkedList characteristics: " + linkedList);
        System.out.println("Vector characteristics: " + vector);
        
        // Performance demonstration
        System.out.println("\n--- Performance Comparison ---");
        
        // Adding elements at the end
        long startTime = System.nanoTime();
        List<Integer> perfArrayList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            perfArrayList.add(i);
        }
        long arrayListTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        List<Integer> perfLinkedList = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            perfLinkedList.add(i);
        }
        long linkedListTime = System.nanoTime() - startTime;
        
        System.out.println("ArrayList add time: " + arrayListTime / 1000000.0 + " ms");
        System.out.println("LinkedList add time: " + linkedListTime / 1000000.0 + " ms");
        
        // Random access performance
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            perfArrayList.get(i * 10 % perfArrayList.size());
        }
        long arrayListAccessTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            perfLinkedList.get(i * 10 % perfLinkedList.size());
        }
        long linkedListAccessTime = System.nanoTime() - startTime;
        
        System.out.println("ArrayList random access time: " + arrayListAccessTime / 1000000.0 + " ms");
        System.out.println("LinkedList random access time: " + linkedListAccessTime / 1000000.0 + " ms");
    }
}
