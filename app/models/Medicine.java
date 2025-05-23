package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medicines")
public class Medicine extends Model {
    @Id
    @Column(name = "medicine_id")
    public Long id;

    public String name;
    public String description;

    @Column(name = "quantity_in_stock")
    public Integer quantityInStock;

    public Double price;

    @Column(name = "expiry_date")
    public Date expiryDate;

    public static Finder<Long, Medicine> find = new Finder<>(Long.class, Medicine.class);
}
