package Utils;

import Models.Entities.Item;
import Repositories.ItemRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemUtils {
    private ItemRepository itemRepository;
    public static final List<Item> listItems = new ArrayList<>();

    @Inject
    public ItemUtils(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    void onStart(@Observes StartupEvent event) {
        listItems.addAll(itemRepository.listAllItems());
    }
}
