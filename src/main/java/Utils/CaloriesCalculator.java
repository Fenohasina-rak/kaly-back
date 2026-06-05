package Utils;

public class CaloriesCalculator {
    public static Integer calculateCalories(Integer calories, Integer quantity){
        return (quantity * calories) / 100;
    }
}
