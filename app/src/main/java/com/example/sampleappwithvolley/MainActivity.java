package com.example.sampleappwithvolley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhttpsdk.core.CocktailManager;
import com.example.myhttpsdk.core.VSdkManager;


import java.util.List;

import callbacks.GetCocktailCallback;
import callbacks.SearchCocktailsCallback;
import domain.Cocktail;
import domain.Ingredient;
import events.IngredientEventArgs;
import events.IngredientListener;
import exceptions.CocktailsSdkException;

public class MainActivity extends AppCompatActivity  implements IngredientListener<IngredientEventArgs> {

    private Button searchCocktailsButton;
    private Button getRandomCocktailsButton;
    private Button searchIngredientButton;
    private ListView cocktailListView;
    private CocktailAdapter adapter;
    private TextView cocktailTextView;
    private  IngredientAdapter ingredientAdapter;
    private Button navigateToSecondPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        searchCocktailsButton = findViewById(R.id.search_cocktails_button);
        getRandomCocktailsButton = findViewById(R.id.get_random_cocktails_button);
        searchIngredientButton = findViewById(R.id.search_ingredient_button);
        navigateToSecondPage = findViewById(R.id.navigate_second_activity);
        cocktailListView = findViewById(R.id.cocktail_list);
        cocktailTextView = findViewById(R.id.cocktail);
        cocktailListView.setAdapter(adapter);

       // VSdkManager.getInstance().startSdk(getApplicationContext());
        CocktailManager.getInstance().startSdk(getApplicationContext());
        searchCocktailsButton.setOnClickListener(view -> {
            try {
                CocktailManager.getInstance().searchCocktailsByName("mojito", new SearchCocktailsCallback() {
                    @Override
                    public void onSearchCocktailsResult(List<Cocktail> cocktailList) {
                        adapter = new CocktailAdapter(view.getContext(), cocktailList);
                        cocktailListView.setAdapter(adapter);
                    }

                    @Override
                    public void onSearchCocktailsFailed(CocktailsSdkException exception) {
                        String message = exception.getErrorType().toString() + " " + exception.getMessage();
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
            }catch(Exception e){
                System.out.println(e.getMessage().toString());
                System.out.println(e.getStackTrace().toString());
            }

        });

        navigateToSecondPage.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
           startActivity(intent);
        });

        getRandomCocktailsButton.setOnClickListener(view -> {
            cocktailTextView.setText("");
            CocktailManager.getInstance().getRandomCocktail(new GetCocktailCallback() {
                @Override
                public void onGetRandomCocktailResult(Cocktail cocktaiL) {
                   cocktailTextView.setText(cocktaiL.toString());
                }

                @Override
                public void onGetRandomCocktailFailed(CocktailsSdkException exception) {
                    String message = exception.getErrorType().toString() + " " + exception.getMessage();
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            });
        });


        searchIngredientButton.setOnClickListener(view -> {
            CocktailManager.getInstance().searchIngredientByName(  this,"vodka");
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @Override
    public void onIngredientSearchCompleted(IngredientEventArgs eventArgs) {
        List<Ingredient> ingredients =  eventArgs.getIngredients();
        ingredientAdapter = new IngredientAdapter(this,ingredients);
        cocktailListView.setAdapter(ingredientAdapter);
    }

    @Override
    public void onIngredientSearchError(String message) {
        Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_LONG).show();
    }
}