package callbacks;

import java.util.List;

import domain.Cocktail;
import exceptions.CocktailsSdkException;

public interface SearchCocktailsCallback {
    void onSearchCocktailsResult(List<Cocktail> cocktailList);
    void onSearchCocktailsFailed(CocktailsSdkException exception);
}
