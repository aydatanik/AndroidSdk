package callbacks;

import java.util.List;

import domain.Cocktail;

public interface SearchCocktailsCallback {
    void onSearchResult(List<Cocktail> cocktailList);
    void onSearchFailed(String message);
}
