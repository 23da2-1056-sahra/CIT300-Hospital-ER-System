package structure;

import model.Visit;

/**
 * REQUIREMENT 4: Patient Visit History - Singly Linked List
 *
 * Each Patient owns exactly one VisitLinkedList (see model.Patient).
 * Supports: add, remove, search, and display of visit records.
 */
public class VisitLinkedList {

    private Visit head;

    /** Adds a new visit to the end of the list. */
    public void addVisit(Visit visit) {
        if (head == null) {
            head = visit;
            return;
        }
        Visit current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = visit;
    }

    /** Removes a visit by its visitId. Returns true if something was removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }

        if (head.getVisitId() == visitId) {
            head = head.next;
            return true;
        }

        Visit previous = head;
        Visit current = head.next;
        while (current != null) {
            if (current.getVisitId() == visitId) {
                previous.next = current.next;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /** Searches for a visit by visitId. Returns null if not found. */
    public Visit searchVisit(int visitId) {
        Visit current = head;
        while (current != null) {
            if (current.getVisitId() == visitId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /** Prints every visit in the list, oldest (added first) to newest. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("   No visit history found for this patient.");
            return;
        }
        Visit current = head;
        while (current != null) {
            System.out.println("   " + current);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}