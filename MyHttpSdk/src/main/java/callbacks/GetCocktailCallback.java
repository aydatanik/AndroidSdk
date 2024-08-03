package callbacks;

import com.example.myhttpsdk.core.CocktailManager;

import java.util.List;

import domain.Cocktail;

public interface GetCocktailCallback {
    void onGetRandomCocktailResult(Cocktail cocktaiL);
    void onGetFailed(String message);
}
