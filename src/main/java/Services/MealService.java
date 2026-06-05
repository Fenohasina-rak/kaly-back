package Services;

import Models.Entities.CustomItem;
import Models.Entities.Item;
import Models.Entities.Meals;
import Models.Input.AddItemToMealInput;
import Models.Input.CreateNewMealInput;
import Models.Output.AddMealOuput;
import Models.Output.Response;
import Repositories.ItemRepository;
import Repositories.MealRepository;
import Utils.CaloriesCalculator;
import Utils.ItemUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class MealService {
    public MealRepository mealRepository;
    public ItemRepository itemRepository;
    public ItemService itemService;
    private JsonWebToken jwt;
    @Inject
    public MealService(MealRepository mealRepository, JsonWebToken jwt, ItemRepository itemRepository,  ItemService itemService) {
        this.mealRepository = mealRepository;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.jwt = jwt;
    }

    public AddMealOuput createNewMeal(CreateNewMealInput input){
        AddMealOuput mealOutput = new AddMealOuput();
        String userId = jwt.getClaim("userId");
        Meals addedMeal = mealRepository.addMeal(input.getName(), 0, userId, input.getDescription());
        if(addedMeal.getId() != null){
            mealOutput.setMeal(addedMeal);
            mealOutput.setResponseCode("200");
            mealOutput.setComment("Added successfully");
        } else {
            mealOutput.setResponseCode("-1");
            mealOutput.setComment("Could not be added");
        }
        return mealOutput;
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

    @Transactional
    Boolean addItemtoMeal(AddItemToMealInput input){
        Boolean isAdded = false;
        String userId = jwt.getClaim("userId");
        Optional<Meals> listUserMeals = mealRepository.getAllMealsByUser(userId).stream().filter(meals -> meals.id.equals(input.getMealId())).findFirst();
        boolean isMealIdAuthorized = listUserMeals.isPresent();
        if(isMealIdAuthorized){
            if (input.getCustomItemId() != null ) {
                Optional<CustomItem> customItemToAdd = itemRepository.getCustomItemByUserById(userId, input.getCustomItemId());
                if(customItemToAdd.isPresent()){
                    mealRepository.addCustomItemToMeal(input.getMealId(), input.getCustomItemId(), input.getQuantity());
                    mealRepository.updateCaloriesMeal(input.getMealId(), CaloriesCalculator.calculateCalories(customItemToAdd.get().getCalories(), input.getQuantity()));
                    isAdded = true;
                }
            } else {
                Item itemToAdd = ItemUtils.itemMapById.get(input.getItemId());
                if (itemToAdd != null){
                    mealRepository.addItemToMeal(input.getMealId(), input.getItemId(), input.getQuantity());
                    mealRepository.updateCaloriesMeal(input.getMealId(), CaloriesCalculator.calculateCalories(itemToAdd.getCalories(), input.getQuantity()));
                    isAdded = true;
                }
            }
        }
        return isAdded;
    }

}
