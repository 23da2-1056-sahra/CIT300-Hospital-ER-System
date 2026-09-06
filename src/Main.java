import java.util.Scanner;

import model.Patient;
import model.Visit;
import model.TreatmentRecord;
import structures.PatientBST;
import structures.EmergencyQueue;
import structures.TreatmentStack;

/**
 * Mini Hospital Emergency Management System
 *
 * Ties together all four required data structures:
 *   1. PatientBST       - patient records (BST)
 *   2. EmergencyQueue   - patients waiting for treatment (Queue / FIFO)
 *   3. TreatmentStack   - completed treatment history (Stack / LIFO)
 *   4. VisitLinkedList  - each patient's past-visit history (Singly Linked List)
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static PatientBST patientRecords = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentHistory = new TreatmentStack();

    public static void main(String[] args) {
        seedSampleData(); // a couple of starter patients so the menu isn't empty on first run
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: registerPatient(); break;
                case 2: searchPatient(); break;
                case 3: deletePatient(); break;
                case 4: patientRecords.displayInOrder(); break;

                case 5: addPatientToQueue(); break;
                case 6: treatNextPatient(); break;
                case 7: emergencyQueue.displayQueue(); break;

                case 8: treatmentHistory.pop(); break; // undo/remove most recent treatment record
                case 9: treatmentHistory.displayStack(); break;

                case 10: addVisitHistory(); break;
                case 11: removeVisitHistory(); break;
                case 12: searchVisitHistory(); break;
                case 13: displayVisitHistory(); break;

                case 0:
                    running = false;
                    System.out.println("Exiting Hospital Emergency Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please pick a number from the menu.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=================================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("=================================================");
        System.out.println(" -- Patient Records (BST) --");
        System.out.println(" 1. Register new patient");
        System.out.println(" 2. Search patient by ID");
        System.out.println(" 3. Delete patient by ID");
        System.out.println(" 4. Display all patients (ascending Patient ID)");
        System.out.println(" -- Emergency Queue --");
        System.out.println(" 5. Add patient to emergency queue");
        System.out.println(" 6. Treat next patient (dequeue + record treatment)");
        System.out.println(" 7. Display waiting queue");
        System.out.println(" -- Treatment History (Stack) --");
        System.out.println(" 8. Undo last completed treatment (pop)");
        System.out.println(" 9. Display treatment history");
        System.out.println(" -- Patient Visit History (Linked List) --");
        System.out.println(" 10. Add a visit record to a patient");
        System.out.println(" 11. Remove a visit record from a patient");
        System.out.println(" 12. Search a visit record for a patient");
        System.out.println(" 13. Display a patient's visit history");
        System.out.println(" 0. Exit");
        System.out.println("=================================================");
    }

    // ---------- Patient Records (BST) ----------

    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientRecords.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        String name = readString("Enter Patient Name: ");
        int age = readInt("Enter Age: ");
        String contact = readString("Enter Contact Number: ");
        String condition = readString("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientRecords.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient found = patientRecords.search(id);
        if (found == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Found: " + found);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean removed = patientRecords.delete(id);
        System.out.println(removed ? "Patient deleted successfully." : "No patient found with ID " + id);
    }

    // ---------- Emergency Queue ----------

    private static void addPatientToQueue() {
        int id = readInt("Enter Patient ID to add to emergency queue: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No such patient on record. Register the patient first (option 1).");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) {
            return; // dequeue() already printed the "empty queue" message
        }
        System.out.println("Now treating: " + patient);

        String treatmentDetails = readString("Enter treatment given: ");
        String date = readString("Enter completion date (e.g. 2026-09-05): ");

        TreatmentRecord record = new TreatmentRecord(patient.getPatientId(), patient.getName(), treatmentDetails, date);
        treatmentHistory.push(record);

        // Optionally record this as a visit in the patient's own history too
        int visitId = (int) (Math.random() * 90000) + 10000;
        Visit visit = new Visit(visitId, date, "Duty Doctor", patient.getMedicalCondition(), treatmentDetails);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Treatment completed and recorded. Auto-generated Visit ID: " + visitId);
    }

    // ---------- Patient Visit History (Linked List) ----------

    private static void addVisitHistory() {
        Patient patient = findPatientOrPrint();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID: ");
        String date = readString("Enter Visit Date: ");
        String doctor = readString("Enter Doctor Name: ");
        String diagnosis = readString("Enter Diagnosis: ");
        String treatment = readString("Enter Treatment: ");

        Visit visit = new Visit(visitId, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit record added.");
    }

    private static void removeVisitHistory() {
        Patient patient = findPatientOrPrint();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit record removed." : "No visit found with that ID.");
    }

    private static void searchVisitHistory() {
        Patient patient = findPatientOrPrint();
        if (patient == null) return;

        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit == null ? "No visit found with that ID." : "Found: " + visit);
    }

    private static void displayVisitHistory() {
        Patient patient = findPatientOrPrint();
        if (patient == null) return;

        System.out.println("Visit history for " + patient.getName() + ":");
        patient.getVisitHistory().displayVisits();
    }

    private static Patient findPatientOrPrint() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        }
        return patient;
    }

    // ---------- Sample seed data ----------

    private static void seedSampleData() {
        patientRecords.insert(new Patient(101, "Nimal Perera", 45, "0771234567", "Fracture"));
        patientRecords.insert(new Patient(102, "Kamala Silva", 30, "0719876543", "High Fever"));
        patientRecords.insert(new Patient(103, "Ruwan Fernando", 60, "0752223344", "Chest Pain"));
    }

    // ---------- Input helpers ----------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}