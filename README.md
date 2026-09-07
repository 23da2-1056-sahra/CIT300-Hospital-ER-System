# CIT300 Hospital Emergency Management System

A console-based Java application built for **CIT300 - Data Structures and Algorithms** (Individual Mid Assignment).

It simulates patient registration, emergency treatment requests, treatment completion, and patient visit history using four core data structures, each implemented from scratch (no `java.util` collections used for the core logic).

## Data Structures Used

| Requirement | Data Structure | File(s) | Purpose |
|---|---|---|---|
| 1 | **Binary Search Tree (BST)** | `structures/PatientBST.java` | Stores all patient records, keyed by Patient ID. Supports insert, search, delete, in-order traversal. |
| 2 | **Queue (FIFO)** | `structures/EmergencyQueue.java` | Manages patients waiting for emergency treatment. |
| 3 | **Stack (LIFO)** | `structures/TreatmentStack.java` | Stores completed treatment records; most recent treatment is on top. |
| 4 | **Singly Linked List** | `structures/VisitLinkedList.java` | Each `Patient` owns one linked list of their past hospital visits. |

## Project Structure

```
CIT300-Hospital-ER-System/
├── README.md
└── src/
    ├── Main.java                       # Console menu / entry point
    ├── model/
    │   ├── Patient.java                # Patient record (BST payload)
    │   ├── Visit.java                  # Visit record (linked list node)
    │   └── TreatmentRecord.java        # Treatment record (Stack payload)
    └── structures/
        ├── PatientBST.java             # BST: insert, search, delete, in-order traversal
        ├── EmergencyQueue.java         # Queue: enqueue, dequeue, display
        ├── TreatmentStack.java         # Stack: push, pop, display
        └── VisitLinkedList.java        # Linked list: add, remove, search, display
```

## How to Run

1. Make sure you have a Java JDK installed (Java 11 or later is fine).
2. Open a terminal in the `src` folder.
3. Compile (this compiles Main.java plus everything in the model/ and structures/ packages):

   On Mac/Linux/PowerShell (wildcards work):
   ```
   javac -d ../bin Main.java model/*.java structures/*.java
   ```

   On Windows Command Prompt (list files explicitly, since cmd.exe doesn't expand wildcards):
   ```
   javac -d ..\bin Main.java model\Patient.java model\Visit.java model\TreatmentRecord.java structures\PatientBST.java structures\EmergencyQueue.java structures\TreatmentStack.java structures\VisitLinkedList.java
   ```

4. Run:
   ```
   cd ../bin
   java Main
   ```
5. Use the on-screen menu to register patients, manage the emergency queue, complete treatments, and manage visit history.

The program starts with 3 sample patients (IDs 101, 102, 103) already loaded so the menu isn't empty on first run.

## Design Notes

- Classes are split into two packages: `model` (plain data-holding classes: `Patient`, `Visit`, `TreatmentRecord`) and `structures` (the four required data structures that operate on that data).
- The Queue and Stack are implemented using custom linked nodes (`QNode`, `SNode`) rather than `java.util.LinkedList`/`Stack`, to clearly demonstrate the underlying mechanics as required by the assignment.
- The BST deletion handles all three cases: leaf node, single child, and two children (replaced by in-order successor).
- Each `Patient` object owns its own `VisitLinkedList`, so visit history is naturally scoped per patient.
- When a patient is treated via the emergency queue (menu option 6), a `TreatmentRecord` is pushed to the stack **and** a `Visit` is automatically added to that patient's history, showing how the four structures work together in one workflow.