package models;

import play.db.ebean.Model;
import javax.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription extends Model {

    @Id
    public Long prescriptionId;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    public Appointment appointment;

    public String medicine;
    public String dosage;
    public String instructions;

    public static Finder<Long, Prescription> find = new Finder<>(Long.class, Prescription.class);
}