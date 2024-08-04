package callbacks;

import java.util.List;

import domain.Ingredient;
import events.IngredientEventArgs;

public interface SearchIngredientsCallback {

    void onSearchResult(IngredientEventArgs eventArgs);
    void onSearchFailed(String message);
}
