package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment extends Model {
    @Id
    public Long id;

    @ManyToOne
    public Patient patient;

    @ManyToOne
    public Doctor doctor;

    public Date appointmentDate;

    public String status; // Scheduled, Completed, Cancelled

    public static Finder<Long, Appointment> find = new Finder<>(Long.class, Appointment.class);
}
