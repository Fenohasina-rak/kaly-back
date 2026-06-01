package Resources;

import Models.Entities.Item;
import Models.Input.AddCutsomItemInput;
import Models.Input.ItemSearchInput;
import Models.Output.AddCustomItemOutput;
import Models.Output.LogoutOutput;
import Services.ItemService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/item")
public class ItemResource {
    private ItemService itemService;

    @Inject
    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public List<Item> searchItem(ItemSearchInput input) {
        return itemService.searchItemAutoComplete(input.getSearchKey());
    }

    @GET
    @Path("/addcustomitem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public AddCustomItemOutput addCustomItem(AddCutsomItemInput input) {
       return  itemService.addCustomItem(input.getName(), input.getCalories());
    }
}
