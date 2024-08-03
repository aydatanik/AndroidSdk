package com.example.sampleappwithvolley;

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

import java.util.LinkedList;
import java.util.List;

import callbacks.GetCocktailCallback;
import callbacks.SearchCocktailsCallback;
import domain.Cocktail;

public class MainActivity extends AppCompatActivity {

    private Button searchCocktailsButton;
    private Button getRandomCocktailsButton;
    private ListView cocktailListView;
    private CocktailAdapter adapter;
    private TextView cocktailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        searchCocktailsButton = findViewById(R.id.search_cocktails_button);
        getRandomCocktailsButton = findViewById(R.id.get_random_cocktails_button);
        cocktailListView = findViewById(R.id.cocktail_list);
        cocktailTextView = findViewById(R.id.cocktail);
        cocktailListView.setAdapter(adapter);

        VSdkManager.getInstance().startSdk(getApplicationContext());

        searchCocktailsButton.setOnClickListener(view -> {
            CocktailManager.getInstance().searchCocktailsByName("mojito", new SearchCocktailsCallback() {
                @Override
                public void onSearchResult(List<Cocktail> cocktailList) {
                    adapter = new CocktailAdapter(view.getContext(), cocktailList);
                    cocktailListView.setAdapter(adapter);
                }

                @Override
                public void onSearchFailed(String message) {
                    Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_LONG).show();
                }
            });
        });

        getRandomCocktailsButton.setOnClickListener(view -> {
            cocktailTextView.setText("");
            CocktailManager.getInstance().getRandomCocktail(new GetCocktailCallback() {
                @Override
                public void onGetRandomCocktailResult(Cocktail cocktaiL) {
                   cocktailTextView.setText(cocktaiL.toString());
                }

                @Override
                public void onGetFailed(String message) {
                    Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_LONG).show();
                }
            });
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}