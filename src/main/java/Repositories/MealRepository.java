package Repositories;


import Models.Entities.MealItem;
import Models.Entities.Meals;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class MealRepository {
    @Transactional
    public Meals addMeal(String name, Integer calories, String userId, String description){
        try {
            Meals meal = new Meals();
            meal.setCalories(calories);
            meal.setName(name);
            meal.setDescription(description);
            meal.setUserId(userId);
            meal.persist();
            return meal;
        } catch (Exception e) {
            return new Meals();
        }
    }


    public void addItemToMeal(Integer mealId, Integer itemId, Integer quantity){
        MealItem mealItem = new MealItem();
        mealItem.setMealId(mealId);
        mealItem.setItemId(itemId);
        mealItem.setQuantity(quantity);
        mealItem.persist();
    }

    public void addCustomItemToMeal(Integer mealId, Integer customItemId, Integer quantity){
        MealItem mealItem = new MealItem();
        mealItem.setMealId(mealId);
        mealItem.setCustomItemId(customItemId);
        mealItem.setQuantity(quantity);
        mealItem.persist();
    }

    public List<Meals> getAllMealsByUser(String userId){
        return Meals.find("userId", userId).list();
    }

    public  void updateCaloriesMeal(Integer mealId, Integer caloriesToAdd){
        Meals meal = Meals.find("id", mealId).firstResult();
        meal.setCalories(meal.getCalories() + caloriesToAdd);
    }

}
