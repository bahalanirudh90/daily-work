# Data Structures and Algorithms (DSA) Notes

## Tree Data Structure

### What is a Tree?
A **tree** is a hierarchical data structure consisting of nodes connected by edges. It's a non-linear data structure that resembles an inverted tree with roots at the top.

### Key Terminology

#### Basic Terms:
- **Node**: Basic unit containing data and references to child nodes
- **Root**: Topmost node of the tree (has no parent)
- **Parent**: Node that has one or more child nodes
- **Child**: Node that has a parent node
- **Leaf/Terminal Node**: Node with no children
- **Internal Node**: Node with at least one child
- **Sibling**: Nodes that share the same parent
- **Ancestor**: Any node on the path from root to a given node
- **Descendant**: Any node reachable from a given node

#### Tree Properties:
- **Height of Tree**: Length of longest path from root to any leaf
- **Depth of Node**: Number of edges from root to that node
- **Level**: All nodes at same depth are at same level
- **Degree of Node**: Number of children a node has
- **Degree of Tree**: Maximum degree of any node in the tree

### Types of Trees

#### 1. Binary Tree
- Each node has **at most 2 children** (left and right)
- **Perfect Binary Tree**: All internal nodes have 2 children, all leaves at same level
- **Complete Binary Tree**: All levels filled except possibly the last, filled left to right
- **Full Binary Tree**: Every node has either 0 or 2 children
- **Balanced Binary Tree**: Height difference between left and right subtrees ≤ 1

#### 2. Binary Search Tree (BST)
- **Left subtree** contains nodes with values **less than** parent
- **Right subtree** contains nodes with values **greater than** parent
- **In-order traversal** gives sorted sequence
- **Search, Insert, Delete**: O(log n) average, O(n) worst case

#### 3. AVL Tree
- **Self-balancing** binary search tree
- **Balance factor** of any node is -1, 0, or 1
- **Rotations** maintain balance after insertions/deletions
- **Guaranteed O(log n)** operations

#### 4. Red-Black Tree
- **Self-balancing** BST with color property
- **Every node** is either red or black
- **Root and leaves** are always black
- **Red nodes** cannot have red children
- **Guaranteed O(log n)** operations

### Tree Traversal Methods

#### 1. Depth-First Search (DFS)
- **Pre-order**: Root → Left → Right
  - Use: Tree copying, expression tree evaluation
- **In-order**: Left → Root → Right  
  - Use: BST sorting, expression tree to infix
- **Post-order**: Left → Right → Root
  - Use: Tree deletion, calculating directory sizes

#### 2. Breadth-First Search (BFS)
- **Level-order**: Visit nodes level by level
- **Use**: Finding shortest path, level-wise processing

### Tree Applications

#### Real-World Uses:
- **File Systems**: Directory structure
- **HTML DOM**: Web page structure  
- **Decision Trees**: AI and machine learning
- **Expression Parsing**: Compilers and calculators
- **Database Indexing**: B-trees and B+ trees
- **Heap**: Priority queues
- **Trie**: String searching and autocomplete

#### Common Algorithms:
- **Height Calculation**: Recursive depth measurement
- **Node Counting**: Total nodes in tree
- **Diameter**: Longest path between any two nodes
- **Lowest Common Ancestor**: Nearest common parent
- **Path Sum**: Sum of values along root-to-leaf paths

### Advantages and Disadvantages

#### Advantages:
- **Hierarchical organization** of data
- **Efficient searching** in BST (O(log n))
- **Dynamic size** - can grow/shrink during runtime
- **No memory waste** compared to arrays

#### Disadvantages:
- **No constant-time access** to arbitrary elements
- **Extra memory** for storing pointers
- **Can become unbalanced** leading to O(n) operations

---

## Sorting Algorithms

### 1. Bubble Sort

#### How it Works:
- **Repeatedly compares** adjacent elements
- **Swaps them** if they're in wrong order
- **"Bubbles"** largest element to end in each pass
- **Continues** until no swaps are needed

#### Characteristics:
- **Time Complexity**: O(n²) average and worst case, O(n) best case
- **Space Complexity**: O(1) - in-place sorting
- **Stability**: Stable (maintains relative order of equal elements)
- **Adaptive**: Yes (performs better on nearly sorted data)

#### When to Use:
- **Very small datasets** (< 10 elements)
- **Educational purposes** - easy to understand
- **When simplicity** is more important than efficiency
- **Nearly sorted data** with optimization

### 2. Selection Sort

#### How it Works:
- **Finds minimum** element in unsorted portion
- **Swaps it** with first unsorted element
- **Moves boundary** of sorted/unsorted portions
- **Repeats** until entire array is sorted

#### Characteristics:
- **Time Complexity**: O(n²) in all cases
- **Space Complexity**: O(1) - in-place sorting
- **Stability**: Unstable (can change relative order)
- **Adaptive**: No (always performs same number of comparisons)

#### When to Use:
- **Small datasets** where simplicity matters
- **Memory is limited** (only O(1) extra space)
- **When number of swaps** should be minimized
- **When writing to memory is costly**

### 3. Insertion Sort

#### How it Works:
- **Takes one element** from unsorted portion
- **Finds correct position** in sorted portion
- **Shifts elements** to make space
- **Inserts element** at correct position

#### Characteristics:
- **Time Complexity**: O(n²) average/worst, O(n) best case
- **Space Complexity**: O(1) - in-place sorting
- **Stability**: Stable
- **Adaptive**: Yes (efficient for nearly sorted data)

#### When to Use:
- **Small datasets** (< 50 elements)
- **Nearly sorted data** - very efficient
- **Online algorithms** (can sort data as it arrives)
- **Hybrid approaches** (combined with other algorithms)
- **When simplicity and stability** are required

### 4. Merge Sort

#### How it Works:
- **Divides array** into two halves recursively
- **Sorts each half** separately
- **Merges** the sorted halves back together
- **Uses divide-and-conquer** approach

#### Characteristics:
- **Time Complexity**: O(n log n) in all cases
- **Space Complexity**: O(n) - requires extra space
- **Stability**: Stable
- **Adaptive**: No (always performs same operations)

#### When to Use:
- **Large datasets** where consistent performance is needed
- **When stability is required**
- **External sorting** (data doesn't fit in memory)
- **Linked lists** - no random access penalty
- **When worst-case performance** matters
- **Parallel processing** - easily parallelizable

### 5. Quick Sort

#### How it Works:
- **Chooses a pivot** element
- **Partitions array** so elements < pivot are left, > pivot are right
- **Recursively sorts** the partitions
- **No merging step** needed

#### Characteristics:
- **Time Complexity**: O(n log n) average, O(n²) worst case
- **Space Complexity**: O(log n) average, O(n) worst case
- **Stability**: Unstable
- **Adaptive**: Somewhat (depends on pivot selection)

#### When to Use:
- **Large datasets** where average-case performance matters
- **Memory is limited** (in-place sorting)
- **When stability is not required**
- **General-purpose sorting** - most programming languages' default
- **Cache-friendly** operations are important

### Sorting Algorithm Comparison

| Algorithm | Best Case | Average Case | Worst Case | Space | Stable | When to Use |
|-----------|-----------|--------------|------------|-------|--------|-------------|
| **Bubble** | O(n) | O(n²) | O(n²) | O(1) | Yes | Very small data, educational |
| **Selection** | O(n²) | O(n²) | O(n²) | O(1) | No | Small data, memory limited |
| **Insertion** | O(n) | O(n²) | O(n²) | O(1) | Yes | Small/nearly sorted data |
| **Merge** | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes | Large data, stability needed |
| **Quick** | O(n log n) | O(n log n) | O(n²) | O(log n) | No | General purpose, large data |

### Choosing the Right Algorithm

#### For Small Data (< 50 elements):
- **Insertion Sort** - simple and efficient for small datasets
- **Selection Sort** - if memory is extremely limited

#### For Large Data:
- **Quick Sort** - general purpose, fastest on average
- **Merge Sort** - when stability is required or worst-case performance matters

#### Special Cases:
- **Nearly Sorted Data**: Insertion Sort
- **Stability Required**: Merge Sort or Insertion Sort
- **Memory Constrained**: Quick Sort or Insertion Sort
- **Worst-case Matters**: Merge Sort
- **External Sorting**: Merge Sort