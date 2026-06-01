package Services;

import Models.Entities.Item;
import Models.Output.AddCustomItemOutput;
import Repositories.ItemRepository;
import Repositories.UserRepository;
import Utils.ItemUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ItemService {
    private JsonWebToken jwt;
    private ItemRepository itemRepository;
    @Inject
    public ItemService(JsonWebToken jwt, ItemRepository itemRepository) {
        this.jwt = jwt;
        this.itemRepository = itemRepository;
    }

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

    public AddCustomItemOutput addCustomItem(String name, Integer calories){
        itemRepository.addCustomItem(name, calories, jwt.getName());
    }

}
