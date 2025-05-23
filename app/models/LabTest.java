package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name = "lab_tests")
public class LabTest extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    public Long testId;

    @Column(name = "test_name")
    public String testName;

    public String description;

    public Double cost;

    public static Finder<Long, LabTest> find = new Finder<>(Long.class, LabTest.class);
}
