package com.example.myhttpsdk.core;

import android.bluetooth.le.ScanCallback;
import android.provider.SyncStateContract;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import callbacks.GetCocktailCallback;
import callbacks.SearchCocktailsCallback;
import domain.Cocktail;
import domain.Constants;
import events.IngredientEventArgs;

public class CocktailManager {

    // first method search cocktails by name ->  searchCocktailsByName(String apiUrl, String cocktailName)
    // Second method get random cocktails -> getRandomCocktails
    // will use HttpHelper
    // Volley manager will be deleted

    private static WeakReference<CocktailManager>  instance;
    private HttpHelper httpHelper;

    private CocktailManager(){
        httpHelper = HttpHelper.getInstance(VSdkManager.getInstance().getApplicationContent());
        httpHelper.getRequestQueue();
    }

    public static CocktailManager getInstance(){
          if(instance == null){
              instance = new WeakReference<>(new CocktailManager());
          }
          return instance.get();
    }

    public void searchCocktailsByName(String cocktailName, SearchCocktailsCallback callback){
        String fullApiUrl = Constants.BASE_URL + Constants.REST_SEARCH_COCKTAILS_BY_NAME + cocktailName;
        httpHelper.getRequest(fullApiUrl, new Response.Listener<List<Cocktail>>() {
            @Override
            public void onResponse(List<Cocktail> cocktails) {
                 callback.onSearchResult(cocktails);
                //Toast.makeText(MainActivity.this, "Foods: \n" + cocktailDetails.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Error"  );
                callback.onSearchFailed(error.getMessage());
            }
        });
    }

    public void getRandomCocktail(GetCocktailCallback callback){
        String fullApiUrl = Constants.BASE_URL + Constants.REST_GET_RANDOM_COCKTAIL;
        httpHelper.getCocktailObject(fullApiUrl, new Response.Listener<Cocktail>() {
            @Override
            public void onResponse(Cocktail cocktail) {
                callback.onGetRandomCocktailResult(cocktail);
                //Toast.makeText(MainActivity.this, "Foods: \n" + cocktailDetails.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Error"  );
                callback.onGetFailed(error.getMessage());
            }
        });
    }

    public void searchByIngredient(){
        String fullApiUrl = Constants.BASE_URL + Constants.REST_GET_RANDOM_COCKTAIL;
        CompletableFuture<IngredientEventArgs> future =  httpHelper.getIngredientAsync(fullApiUrl);
        future.thenAccept(this::onIngredientSearchCompleted);
                //.exceptionally(this::onIngredientSearchError);
    }

    public void onIngredientSearchCompleted(IngredientEventArgs eventArgs){
       /* runOnUiThread() -> {

        }*/
    }

    public void onIngredientSearchError(Throwable throwable){
        /*runOnUiThread() -> {

        }*/
    }

}

