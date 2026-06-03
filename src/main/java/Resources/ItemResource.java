package Resources;

import Models.Entities.Item;
import Models.Input.AddCutsomItemInput;
import Models.Input.DeleteCustomItemInput;
import Models.Input.ItemSearchInput;
import Models.Output.AddCustomItemOutput;
import Models.Output.LogoutOutput;
import Models.Output.Response;
import Services.ItemService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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

    @POST
    @Path("/addcustomitem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public AddCustomItemOutput addCustomItem(AddCutsomItemInput input) {
       return  itemService.addCustomItem(input.getName(), input.getCalories());
    }


    @DELETE
    @Path("/deletecustomitem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response deleteCustomItem(DeleteCustomItemInput input) {
        return itemService.deleteCustomItem(input.getName());
    }
}
