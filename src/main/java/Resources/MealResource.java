package Resources;

import Models.Input.AddItemToMealInput;
import Models.Input.CreateNewMealInput;
import Models.Output.Response;
import Services.MealService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


@Path("/api/meal")
public class MealResource {
    private MealService mealService;

    @Inject
    public MealResource(MealService mealService) {
        this.mealService = mealService;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response addMeal(CreateNewMealInput input) {
        return mealService.createNewMeal(input);
    }

    @POST
    @Path("/addItem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response addItemToMeal(AddItemToMealInput input){
        return mealService.addItemToMeal(input);
    }
}
