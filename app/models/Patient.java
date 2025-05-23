package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Patient extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String fullName;

    @Constraints.Required
    public String gender;

    public String address;
    public String phone;

    @Temporal(TemporalType.DATE)
    public Date dob;

    public String bloodGroup;

    @ManyToOne(fetch = FetchType.EAGER) // Ensure doctor is fetched with patient
    public Doctor assignedDoctor;

    public static Finder<Long, Patient> find = new Finder<>(Long.class, Patient.class);
}
