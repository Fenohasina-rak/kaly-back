package Models.Input;

import com.fasterxml.jackson.annotation.JsonInclude;


public class AddItemToMealInput {
    private Integer mealId;
    private Integer itemId;
    private Integer quantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer customItemId;

    public AddItemToMealInput() {
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCustomItemId() {
        return customItemId;
    }

    public void setCustomItemId(Integer customItemId) {
        this.customItemId = customItemId;
    }
}
