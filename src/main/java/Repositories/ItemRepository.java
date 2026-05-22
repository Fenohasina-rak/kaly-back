package Repositories;

import Models.Entities.Item;
import Models.Entities.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ItemRepository {
    public List<Item> listAllItems(){
        return Item.listAll();
    }
}
