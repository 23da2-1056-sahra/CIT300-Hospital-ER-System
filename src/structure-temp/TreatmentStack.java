package structure;

import model.TreatmentRecord;

/**
 * REQUIREMENT 3: Treatment History - Stack (LIFO)
 *
 * Implemented as a linked-node stack so the mechanics are explicit.
 * Supports: push, pop, display, and empty-stack handling.
 */
public class TreatmentStack {

    private class SNode {
        TreatmentRecord record;
        SNode next;

        SNode(TreatmentRecord record) {
            this.record = record;
        }
    }

    private SNode top;
    private int size;

    /** Pushes a newly completed treatment record onto the top of the stack. */
    public void push(TreatmentRecord record) {
        SNode node = new SNode(record);
        node.next = top;
        top = node;
        size++;
        System.out.println("Treatment record for Patient " + record.getPatientId() + " pushed onto history stack.");
    }

    /** Removes and returns the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    /** Displays all treatment records, most recent first (true stack/LIFO order). */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No treatment records available.");
            return;
        }
        System.out.println("Treatment history (most recent first):");
        SNode current = top;
        while (current != null) {
            System.out.println("   " + current.record);
            current = current.next;
        }
    }
}