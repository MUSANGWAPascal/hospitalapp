package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lab_results")
public class LabResult extends Model {

    @Id
    @Column(name = "result_id")
    public Long id;

    @Column(name = "result")
    public String resultDetails;

    @Column(name = "result_date")
    public Date resultDate;

    @Column(name = "status")
    public String status;

    @ManyToOne
    @JoinColumn(name = "test_id")
    public LabTest test;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    public Patient patient;

    public static Finder<Long, LabResult> find = new Finder<>(Long.class, LabResult.class);
}
