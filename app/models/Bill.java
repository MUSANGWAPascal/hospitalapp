package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bills")  // Matches the real table name
public class Bill extends Model {

    @Id
    @Column(name = "bill_id") // assuming the primary key is bill_id
    public Long id;

    public Double amount;
    public String status;

    @Column(name = "billing_date")
    public Date billingDate;

    public String details;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    public Patient patient;

    public static Finder<Long, Bill> find = new Finder<>(Long.class, Bill.class);
}
