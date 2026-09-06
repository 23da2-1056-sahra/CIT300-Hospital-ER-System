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