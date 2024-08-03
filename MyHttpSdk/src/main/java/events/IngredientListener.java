package events;

public interface IngredientListener {
    void onIngredientSearchCompleted(IngredientEventArgs  eventArgs);
    void onIngredientSearchError();
}
