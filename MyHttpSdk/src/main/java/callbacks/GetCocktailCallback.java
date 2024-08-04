package callbacks;

import com.example.myhttpsdk.core.CocktailManager;

import java.util.List;

import domain.Cocktail;
import exceptions.CocktailsSdkException;

public interface GetCocktailCallback {
    void onGetRandomCocktailResult(Cocktail cocktaiL);
    void onGetRandomCocktailFailed(CocktailsSdkException exception);
}
