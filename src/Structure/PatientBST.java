package structures;

import model.Patient;

/**
 * REQUIREMENT 1: Patient Records - Binary Search Tree (BST)
 *
 * Stores Patient objects, keyed by patientId.
 * Supports: insert, search, delete, and in-order traversal (ascending Patient ID order).
 */
public class PatientBST {

    // A single node in the tree. Kept private/inner since only PatientBST needs to know about it.
    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node root;

    /** Inserts a new patient into the tree, ordered by patientId. */
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }

        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("A patient with ID " + patient.getPatientId() + " already exists. Insert skipped.");
        }
        return node;
    }

    /** Searches for a patient by ID. Returns null if not found. */
    public Patient search(int patientId) {
        return searchRec(root, patientId);
    }

    private Patient searchRec(Node node, int patientId) {
        if (node == null) {
            return null;
        }
        if (patientId == node.patient.getPatientId()) {
            return node.patient;
        }
        return patientId < node.patient.getPatientId()
                ? searchRec(node.left, patientId)
                : searchRec(node.right, patientId);
    }

    /** Deletes a patient by ID. Returns true if a patient was actually removed. */
    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRec(root, patientId);
        return true;
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found - handle the 3 classic BST deletion cases

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3: two children -> replace with in-order successor (smallest in right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** Prints all patients in ascending order of Patient ID. */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.patient);
            inOrderRec(node.right);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}