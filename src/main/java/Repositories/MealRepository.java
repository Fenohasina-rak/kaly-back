package Repositories;


import Models.Entities.MealItem;
import Models.Entities.Meals;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MealRepository {
    @Transactional
    public Boolean addMeal(String name, Integer calories, String userId, String description){
        try {
            Meals meal = new Meals();
            meal.setCalories(calories);
            meal.setName(name);
            meal.setDescription(description);
            meal.setUserId(userId);
            meal.persist();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Boolean addItemToMeal(Integer mealId, Integer itemId, Integer quantity){
        try {
            MealItem mealItem = new MealItem();
            mealItem.setMealId(mealId);
            mealItem.setItemId(itemId);
            mealItem.setQuantity(quantity);
            mealItem.persist();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public Boolean addCustomItemToMeal(Integer mealId, Integer customItemId, Integer quantity){
        try {
            MealItem mealItem = new MealItem();
            mealItem.setMealId(mealId);
            mealItem.setCustomItemId(customItemId);
            mealItem.setQuantity(quantity);
            mealItem.persist();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Meals> getAllMealsByUser(String userId){
        return Meals.find("userId", userId).list();
    }

}
