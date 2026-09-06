package model;

/**
 * Represents one past hospital visit belonging to a patient.
 * This class doubles as the node type for VisitLinkedList (it has a `next` pointer),
 * which is a common, simple way to implement a singly linked list in Java.
 */
public class Visit {

    private int visitId;
    private String visitDate;
    private String doctorName;
    private String diagnosis;
    private String treatment;

    // Pointer to the next visit in the list (public so structures.VisitLinkedList, in another package, can link nodes)
    public Visit next;

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.next = null;
    }

    public int getVisitId() {
        return visitId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    @Override
    public String toString() {
        return "Visit ID: " + visitId +
                " | Date: " + visitDate +
                " | Doctor: " + doctorName +
                " | Diagnosis: " + diagnosis +
                " | Treatment: " + treatment;
    }
}