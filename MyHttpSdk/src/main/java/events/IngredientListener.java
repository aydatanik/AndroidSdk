package events;

public interface IngredientListener<IngredientEventArgs> {
    void onIngredientSearchCompleted(IngredientEventArgs  eventArgs);
    void onIngredientSearchError(String message);
}
