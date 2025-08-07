package collections;

import java.util.*;

public class HashMapExample {
    
    public static void main(String[] args) {
        System.out.println("=== HashMap Basic Demo ===\n");
        
        // 1. Creating and Basic Operations
        System.out.println("1. Creating HashMap and Basic Operations:");
        HashMap<String, Integer> ages = new HashMap<>();
        
        // Adding key-value pairs
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 35);
        ages.put("Diana", 28);
        System.out.println("Initial HashMap: " + ages);
        
        // Getting values
        System.out.println("Alice's age: " + ages.get("Alice"));
        System.out.println("Unknown person's age: " + ages.get("Unknown")); // returns null
        
        // Using getOrDefault
        System.out.println("Unknown person's age (with default): " + ages.getOrDefault("Unknown", 0));
        
        // 2. Size and Empty checks
        System.out.println("\n2. Size and Empty Operations:");
        System.out.println("Size: " + ages.size());
        System.out.println("Is empty: " + ages.isEmpty());
        
        // 3. Checking existence
        System.out.println("\n3. Checking Existence:");
        System.out.println("Contains key 'Bob': " + ages.containsKey("Bob"));
        System.out.println("Contains value 30: " + ages.containsValue(30));
        System.out.println("Contains key 'Eve': " + ages.containsKey("Eve"));
        
        // 4. Updating values
        System.out.println("\n4. Updating Values:");
        ages.put("Alice", 26); // Updates existing value
        System.out.println("After updating Alice's age: " + ages);
        
        // Using putIfAbsent
        ages.putIfAbsent("Eve", 22); // Adds only if key doesn't exist
        ages.putIfAbsent("Alice", 27); // Won't update because key exists
        System.out.println("After putIfAbsent operations: " + ages);
        
        // 5. Removing elements
        System.out.println("\n5. Removing Elements:");
        Integer removedAge = ages.remove("Charlie");
        System.out.println("Removed Charlie's age: " + removedAge);
        System.out.println("HashMap after removal: " + ages);
        
        // Remove with specific value
        boolean removed = ages.remove("Bob", 30);
        System.out.println("Removed Bob with age 30: " + removed);
        System.out.println("HashMap after conditional removal: " + ages);
        
        // 6. Iteration methods
        System.out.println("\n6. Different Ways to Iterate:");
        
        // Iterate over keys
        System.out.print("Keys: ");
        for (String key : ages.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();
        
        // Iterate over values
        System.out.print("Values: ");
        for (Integer value : ages.values()) {
            System.out.print(value + " ");
        }
        System.out.println();
        
        // Iterate over entries
        System.out.println("Entries:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        // Using forEach with lambda
        System.out.println("Using forEach with lambda:");
        ages.forEach((key, value) -> System.out.println("  " + key + " is " + value + " years old"));
        
        // 7. Bulk operations
        System.out.println("\n7. Bulk Operations:");
        HashMap<String, Integer> moreAges = new HashMap<>();
        moreAges.put("Frank", 40);
        moreAges.put("Grace", 32);
        
        ages.putAll(moreAges); // Add all entries from another map
        System.out.println("After putAll: " + ages);
        
        // 8. Advanced operations (Java 8+)
        System.out.println("\n8. Advanced Operations:");
        
        // compute - calculate new value based on key and current value
        ages.compute("Alice", (key, value) -> value != null ? value + 1 : 1);
        System.out.println("After compute (Alice +1): " + ages);
        
        // computeIfAbsent - compute value only if key is absent
        ages.computeIfAbsent("Henry", key -> key.length() * 5);
        System.out.println("After computeIfAbsent (Henry): " + ages);
        
        // computeIfPresent - compute value only if key is present
        ages.computeIfPresent("Grace", (key, value) -> value - 1);
        System.out.println("After computeIfPresent (Grace -1): " + ages);
        
        // merge - merge values if key exists, otherwise put new value
        ages.merge("Frank", 5, Integer::sum); // Adds 5 to Frank's age
        ages.merge("Ian", 45, Integer::sum); // Creates new entry for Ian
        System.out.println("After merge operations: " + ages);
        
        // 9. HashMap characteristics
        System.out.println("\n=== HashMap Key Characteristics ===");
        System.out.println("1. O(1) average time complexity for get/put operations");
        System.out.println("2. Allows null key (only one) and null values");
        System.out.println("3. Not thread-safe");
        System.out.println("4. No ordering guarantee");
        System.out.println("5. Uses hashing for fast access");
        
        // Demonstrating null handling
        HashMap<String, String> nullDemo = new HashMap<>();
        nullDemo.put(null, "null key value");
        nullDemo.put("key1", null);
        nullDemo.put("key2", null);
        System.out.println("Null handling demo: " + nullDemo);
        
        // 10. Common use cases
        System.out.println("\n=== Common Use Cases ===");
        
        // Frequency counting
        System.out.println("1. Frequency Counting:");
        String text = "hello world hello java world";
        HashMap<String, Integer> wordCount = new HashMap<>();
        
        for (String word : text.split(" ")) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        System.out.println("Word frequencies: " + wordCount);
        
        // Grouping data
        System.out.println("\n2. Grouping Data:");
        String[] students = {"Alice", "Bob", "Anna", "Charlie", "Andrew"};
        HashMap<Character, ArrayList<String>> groupedByFirstLetter = new HashMap<>();
        
        for (String student : students) {
            char firstLetter = student.charAt(0);
            if (!groupedByFirstLetter.containsKey(firstLetter)) {
                groupedByFirstLetter.put(firstLetter, new ArrayList<>());
            }
            groupedByFirstLetter.get(firstLetter).add(student);
        }
        System.out.println("Students grouped by first letter: " + groupedByFirstLetter);
        
        // Two-way mapping
        System.out.println("\n3. Two-way Mapping (ID to Name and Name to ID):");
        HashMap<Integer, String> idToName = new HashMap<>();
        HashMap<String, Integer> nameToId = new HashMap<>();
        
        idToName.put(1, "Alice");
        idToName.put(2, "Bob");
        nameToId.put("Alice", 1);
        nameToId.put("Bob", 2);
        
        System.out.println("ID 1 -> " + idToName.get(1));
        System.out.println("Name 'Bob' -> ID " + nameToId.get("Bob"));
        
        // 11. Comparing with other Map types
        System.out.println("\n=== Map Type Comparison ===");
        
        // HashMap - no ordering
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("C", 3);
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        System.out.println("HashMap (no ordering): " + hashMap);
        
        // LinkedHashMap - insertion order
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("C", 3);
        linkedHashMap.put("A", 1);
        linkedHashMap.put("B", 2);
        System.out.println("LinkedHashMap (insertion order): " + linkedHashMap);
        
        // TreeMap - sorted order
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("C", 3);
        treeMap.put("A", 1);
        treeMap.put("B", 2);
        System.out.println("TreeMap (sorted order): " + treeMap);
        
        System.out.println("\nWhen to use:");
        System.out.println("- HashMap: Default choice, fastest");
        System.out.println("- LinkedHashMap: When you need insertion order");
        System.out.println("- TreeMap: When you need sorted keys");
    }
}

