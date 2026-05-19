package Models.Session;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends PanacheEntityBase {
    @Id
    @Column(name="id")
    public String id;

    @Column(name="username")
    public String username;

    @Column(name="user_password")
    public String password;

    @Column(name="user_role")
    public String role;
}
