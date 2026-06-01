package Repositories;

import Models.Entities.CustomItem;
import Models.Entities.Item;
import Models.Entities.User;
import Utils.ItemUtils;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ItemRepository {
    public List<Item> listAllItems(){
        return Item.listAll();
    }
    @Transactional
    public Boolean addCustomItem(String name, Integer calories, String userId){
        try {
            CustomItem newItem = new CustomItem();
            newItem.setCalories(calories);
            newItem.setName(name);
            newItem.setUserId(userId);
            PanacheEntityBase.persist(newItem);
            PanacheEntityBase.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
