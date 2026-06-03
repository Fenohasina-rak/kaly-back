package Models.Entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item extends PanacheEntityBase {
    @Id
    @Column(name="id")
    public Integer id;

    @Column(name="item_name")
    public String name;

    @Column(name="calories")
    public Integer calories;

    public Item(Integer id, String name, Integer calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

}
