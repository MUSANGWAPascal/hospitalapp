package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medical_records")
public class MedicalRecord extends Model {
    @Id
    public Long recordId;

    @ManyToOne
    public Patient patient;

    @ManyToOne
    public Doctor doctor;

    public String diagnosis;

    @Column(length = 2000)
    public String treatment;

//    @Column(length = 2000)
//    public String prescription;

    public Date recordDate;

    public static Finder<Long, MedicalRecord> find = new Finder<>(Long.class, MedicalRecord.class);
}
