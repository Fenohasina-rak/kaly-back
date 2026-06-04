package Services;

import Models.Entities.Meals;
import Models.Input.AddItemToMealInput;
import Models.Input.CreateNewMealInput;
import Models.Output.Response;
import Repositories.ItemRepository;
import Repositories.MealRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MealService {
    public MealRepository mealRepository;
    public ItemRepository itemRepository;
    private JsonWebToken jwt;
    @Inject
    public MealService(MealRepository mealRepository, JsonWebToken jwt, ItemRepository itemRepository) {
        this.mealRepository = mealRepository;
        this.itemRepository = itemRepository;
        this.jwt = jwt;
    }

    public Response  createNewMeal(CreateNewMealInput input){
        Response response = new Response();
        String userId = jwt.getClaim("userId");
        if(mealRepository.addMeal(input.getName(), 0, userId, input.getDescription())){
            response.setResponseCode("200");
            response.setComment("Added successfully");
        } else {
            response.setResponseCode("-1");
            response.setComment("Could not be added");
        }
        return response;
    }

    public Response addItemToMeal(AddItemToMealInput input){
        Response response = new Response();
        if(addItemtoMeal(input)){
            response.setComment("Successfully added");
            response.setResponseCode("200");
        } else {
            response.setComment("Item not added");
            response.setResponseCode("-1");
        }
        return response;
    }

    private Boolean addItemtoMeal(AddItemToMealInput input){
        Boolean isAdded = false;
        String userId = jwt.getClaim("userId");
        Optional<Meals> listUserMeals = mealRepository.getAllMealsByUser(userId).stream().filter(meals -> meals.id.equals(input.getMealId())).findFirst();
        boolean isMealIdAuthorized = listUserMeals.isPresent();
        if(isMealIdAuthorized){
            if (input.getCustomItemId() != null || input.getCustomItemId() != 0 ) {
                if(!itemRepository.listAllCustomItemsByUser(userId).stream().filter(customItem -> customItem.id().equals(input.getCustomItemId())).toList().isEmpty()){
                    isAdded =  mealRepository.addCustomItemToMeal(input.getMealId(), input.getCustomItemId(), input.getQuantity());
                }
            } else {
                isAdded =  mealRepository.addItemToMeal(input.getMealId(), input.getItemId(), input.getQuantity());
            }
        }
        return isAdded;
    }


}
