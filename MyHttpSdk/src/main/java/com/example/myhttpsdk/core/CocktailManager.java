package com.example.myhttpsdk.core;

import android.app.Activity;
import android.bluetooth.le.ScanCallback;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
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
import domain.ErrorType;
import events.IngredientEventArgs;
import events.IngredientListener;
import exceptions.CocktailsSdkException;

public class CocktailManager {

    // first method search cocktails by name ->  searchCocktailsByName(String apiUrl, String cocktailName)
    // Second method get random cocktails -> getRandomCocktails
    // will use HttpHelper
    // Volley manager will be deleted

    private static CocktailManager  instance;
    private HttpHelper httpHelper;
    private WeakReference<Context> applicationContext;

    private CocktailManager(){
    }

    public static CocktailManager getInstance(){
          if(instance == null){
              instance = new CocktailManager();
          }
          return instance;
    }

    public void searchCocktailsByName(String cocktailName, SearchCocktailsCallback callback) {
        initializeHttpHelper();
        String fullApiUrl = Constants.BASE_URL + Constants.REST_SEARCH_COCKTAILS_BY_NAME + cocktailName;
        httpHelper.getCocktailList(fullApiUrl, new Response.Listener<List<Cocktail>>() {
            @Override
            public void onResponse(List<Cocktail> cocktails) {
                 callback.onSearchCocktailsResult(cocktails);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int responseCode = 0;
               if(error.networkResponse != null){
                   responseCode = error.networkResponse.statusCode;
               }
                CocktailsSdkException exception = getCocktailSdkError(responseCode);
                callback.onSearchCocktailsFailed(exception);
            }
        });
    }

    public void getRandomCocktail(GetCocktailCallback callback){
        initializeHttpHelper();
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
                int responseCode = 0;
                if(error.networkResponse != null){
                    responseCode = error.networkResponse.statusCode;
                }
                CocktailsSdkException exception = getCocktailSdkError(responseCode);
                callback.onGetRandomCocktailFailed(exception);
            }
        });
    }

    public void searchIngredientByName( IngredientListener<IngredientEventArgs> listener, String ingredientName){
        initializeHttpHelper();
        CompletableFuture<IngredientEventArgs> future =  httpHelper.getIngredientAsync(ingredientName, listener);
        future.thenAccept (eventArgs -> {
           /* ((Activity) context).runOnUiThread(new Runnable() {
                public void run() {
                  onIngredientSearchCompleted(callback,eventArgs);
                }
            });*/
            new Handler(Looper.getMainLooper()).post(() -> {
                listener.onIngredientSearchCompleted(eventArgs);
            });
        }).exceptionally(throwable -> {
            new Handler(Looper.getMainLooper()).post(() -> {
                listener.onIngredientSearchError(throwable.getMessage());
            });
            return null;
        });
    }

    private CocktailsSdkException getCocktailSdkError(int responseCode){
        CocktailsSdkException exception;
        if(responseCode == 401){
            exception =  new CocktailsSdkException(ErrorType.UNAUTHORIZED_ERROR, "Unauthorized access -status code 401");
        }
        if(responseCode > 400){
            exception =  new CocktailsSdkException(ErrorType.CLIENT_ERROR, "Client error");
        }
        if(responseCode > 500){
            exception =  new CocktailsSdkException(ErrorType.SERVER_ERROR, "Server error");
        }else{
            exception =  new CocktailsSdkException(ErrorType.NETWORK_ERROR, "Please check the internet connection");
        }
        return exception;
    }

    public void startSdk(Context context){
        // Take the application content, it is important for the volley
        applicationContext = new WeakReference<>(context);
        //To-do...
    }
    public Context getApplicationContent(){
        return applicationContext.get();
    }

    private void initializeHttpHelper(){
        httpHelper = HttpHelper.getInstance(getApplicationContent());
        httpHelper.getRequestQueue();
    }
}

