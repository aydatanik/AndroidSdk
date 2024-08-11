package com.example.sampleappwithvolley;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhttpsdk.core.CocktailManager;

import java.util.List;

import callbacks.SearchCocktailsCallback;
import domain.Cocktail;
import exceptions.CocktailsSdkException;

public class SecondActivity extends AppCompatActivity {
private Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        search = findViewById(R.id.search_cocktails_button_2);
        search.setOnClickListener(view -> {
            CocktailManager.getInstance().searchCocktailsByName("mojito", new SearchCocktailsCallback() {
                @Override
                public void onSearchCocktailsResult(List<Cocktail> cocktailList) {
                    Toast.makeText(SecondActivity.this, "ayda", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSearchCocktailsFailed(CocktailsSdkException exception) {
                    String message = exception.getErrorType().toString() + " " + exception.getMessage();
                    Toast.makeText(SecondActivity.this, message, Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}