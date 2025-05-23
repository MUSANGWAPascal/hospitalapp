package models;

import play.db.ebean.Model;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryItem extends Model {
    @Id
    public Long id;

    @Column(name = "item_name", length = 100)
    public String itemName;

    @Column(length = 255)
    public String description;

    public Integer quantity;

    @Column(length = 50)
    public String unit;

    @Column(name = "last_updated")
    @Temporal(TemporalType.DATE)
    public Date lastUpdated;

    public static final Finder<Long, InventoryItem> find = new Finder<>(Long.class, InventoryItem.class);
}