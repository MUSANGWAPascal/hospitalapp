package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends Model {
    @Id
    public Long id;

    public String username;
    public String password;
    public String role; // Admin, Doctor, Staff
    public String fullName;

    public static Finder<Long, User> find = new Finder<>(Long.class, User.class);

    public static User authenticate(String username, String password) {
        return find.where()
                .eq("username", username)
                .eq("password", password)
                .findUnique();
    }
}
