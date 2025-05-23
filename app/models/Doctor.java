package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class Doctor extends Model {

    @Id
    public Long id;

    public String name;
    public String specialty;
    public String phone;
    public String email;
    @OneToOne
    public User user;
    public static Finder<Long, Doctor> find = new Finder<>(Long.class, Doctor.class);
}
