package Models.Output;

import Models.Entities.Meals;

public class AddMealOuput extends Response{
    private Meals meal;

    public AddMealOuput() {
    }

    public Meals getMeal() {
        return meal;
    }

    public void setMeal(Meals meal) {
        this.meal = meal;
    }
}
