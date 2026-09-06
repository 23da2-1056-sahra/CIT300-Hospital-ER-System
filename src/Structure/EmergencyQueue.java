package structure;

import model.Patient;

/**
 * REQUIREMENT 2: Emergency Patient Queue - Queue (FIFO)
 *
 * Implemented as a linked-node queue (not java.util.Queue) so the underlying
 * data structure mechanics are explicit, as expected for this assignment.
 * Supports: enqueue, dequeue, display, and empty-queue handling.
 */
public class EmergencyQueue {

    private class QNode {
        Patient patient;
        QNode next;

        QNode(Patient patient) {
            this.patient = patient;
        }
    }

    private QNode front;
    private QNode rear;
    private int size;

    /** Adds a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        QNode node = new QNode(patient);
        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
        System.out.println("Patient " + patient.getPatientId() + " (" + patient.getName() + ") added to the emergency queue.");
    }

    /** Removes and returns the patient at the front of the queue (next to be treated). */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient is waiting.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return patient;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    /** Displays every patient currently waiting, in queue order. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting in the emergency queue.");
            return;
        }
        QNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }
}