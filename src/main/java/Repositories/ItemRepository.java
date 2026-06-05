package Repositories;


import Models.Entities.CustomItem;
import Models.Entities.Item;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


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
            newItem.persist();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Models.Entities.Records.CustomItem> listAllCustomItemsByUser(String userId){
        List<CustomItem> listCustomItem = CustomItem.find("userId", userId).list();
        return listCustomItem
                .stream()
                .map(item -> new Models.Entities.Records.CustomItem(item.getId(), item.getName(), item.getCalories() , item.getUserId()))
                .toList();
    }

    public Optional<CustomItem> getCustomItemByUserById(String userId, Integer id){
        return CustomItem.find("userId = ?1 and id = ?2 ", userId, id).firstResultOptional();
    }

    @Transactional
    public Boolean deleteItemByName(String itemName) {
        try {
            long deletedCount = CustomItem.delete("name", itemName);
            return deletedCount > 0;
        } catch (Exception e) {
            return false;
        }
    }


}
