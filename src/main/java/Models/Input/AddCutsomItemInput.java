package Models.Input;

public class AddCutsomItemInput {
    private String name;
    private Integer calories;

    public AddCutsomItemInput() {
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
