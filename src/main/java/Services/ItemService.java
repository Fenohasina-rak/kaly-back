package Services;

import Models.Entities.Item;
import Models.Entities.Records.CustomItem;
import Models.Output.AddCustomItemOutput;
import Models.Output.Response;
import Repositories.ItemRepository;
import Utils.ItemUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemService {
    private JsonWebToken jwt;
    private ItemRepository itemRepository;
    @Inject
    public ItemService(JsonWebToken jwt, ItemRepository itemRepository) {
        this.jwt = jwt;
        this.itemRepository = itemRepository;
    }

    private final List<Item> LIST_ALL_ITEMS = ItemUtils.listItems;
    public List<Item> getAllItemsByUser(String userId){
        List<CustomItem> customItem = itemRepository.listAllCustomItemsByUser(userId);
        List<Item> customItemToItemList = customItem.stream().map(recordEntity -> new Item(recordEntity.id(), recordEntity.item_name(), recordEntity.calories())).collect(Collectors.toList());
        customItemToItemList.addAll(LIST_ALL_ITEMS);
        return customItemToItemList;
    }

    public List<Item> searchItemAutoComplete(String searchKey){
        List<Item> resultSearch ;
        String lowerKey = searchKey.toLowerCase().trim();
        String userId = jwt.getClaim("userId");
        resultSearch = getAllItemsByUser(userId).stream()
                .filter(item -> item.name.toLowerCase().contains(lowerKey))
                .sorted(Comparator.comparing(Item::getName))
                .limit(10)
                .toList();
        return resultSearch;
    }

    public AddCustomItemOutput addCustomItem(String name, Integer calories){
        String userId = jwt.getClaim("userId");
        AddCustomItemOutput response = new AddCustomItemOutput();
        if(ItemUtils.itemMap.containsKey(name.trim().toLowerCase()) || itemRepository.listAllCustomItemsByUser(userId).stream().anyMatch(item -> item.item_name().equalsIgnoreCase(name.trim()))){
            response.setComment("Item name already exists");
            response.setIsAdded("false");
            response.setResponseCode("200");
        } else {
            if(itemRepository.addCustomItem(name, calories, userId)){
                response.setIsAdded("true");
                response.setComment("CustomItem added");
                response.setResponseCode("200");
            } else {
                response.setResponseCode("204");
                response.setIsAdded("false");
                response.setComment("Error while adding item");
            }
        }

        return response;

    }

    public Response deleteCustomItem(String name){
        String userId = jwt.getClaim("userId");
        Response response = new Response();
        if(itemRepository.listAllCustomItemsByUser(userId).stream().filter(item -> item.item_name().trim().equalsIgnoreCase(name.trim())).toList().isEmpty()){
            response.setComment("No such Item");
            response.setResponseCode("204");
        } else {
            if(itemRepository.deleteItemByName(name)){
                response.setResponseCode("0");
                response.setComment("Item " + name + " deleted");
            } else {
                response.setResponseCode("204");
                response.setComment("0 item deleted");
            }
        }
        return response;
    }

}
