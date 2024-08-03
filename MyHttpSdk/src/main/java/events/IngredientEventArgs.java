package events;

import java.util.List;

import domain.Ingredient;

public class IngredientEventArgs {
    private final List<Ingredient> ingredients;

    public IngredientEventArgs(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }
}
