package Utils;

import Models.Entities.Item;
import Repositories.ItemRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ApplicationScoped
public class ItemUtils {
    private ItemRepository itemRepository;
    public static final List<Item> listItems = new ArrayList<>();
    public static final Map<String, Item> itemMap = new HashMap<>();

    @Inject
    public ItemUtils(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    void onStart(@Observes StartupEvent event) {
        listItems.addAll(itemRepository.listAllItems());
        listItems.forEach(item -> itemMap.put(item.name.trim().toLowerCase(), item));
    }
}
