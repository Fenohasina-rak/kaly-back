package Services;

import Models.Entities.Item;
import Utils.ItemUtils;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ItemService {
    private List<Item> LIST_ALL_ITEMS = ItemUtils.listItems;
    public List<Item> getAllItems(){
        return LIST_ALL_ITEMS;
    }

    public List<Item> searchItemAutoComplete(String searchKey){
        List<Item> resultSearch ;
        String lowerKey = searchKey.toLowerCase().trim();
        resultSearch = LIST_ALL_ITEMS.stream()
                .filter(item -> item.name.toLowerCase().contains(lowerKey))
                .sorted(Comparator.comparing(Item::getName))
                .limit(10)
                .toList();
        return resultSearch;
    }

}
