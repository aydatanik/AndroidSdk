package com.example.myhttpsdk.core;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import domain.Cocktail;
import domain.Constants;
import domain.ErrorType;
import domain.Ingredient;
import events.IngredientEventArgs;
import events.IngredientListener;
import exceptions.CocktailsSdkException;

public class HttpHelper {
    private static HttpHelper instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private HttpHelper(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized HttpHelper getInstance(Context context){
        if(instance == null){
            instance = new HttpHelper(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            // application Context will be modified...
            requestQueue = Volley.newRequestQueue(ctx);
        }
        return requestQueue;
    }

    /*public <T> void getRequest(String apiUrl, final Class<T[]> requestedClass,
                               final Response.Listener<List<T>> listener, final Response.ErrorListener errorListener){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                T[] resultArray = gson.fromJson(response.toString(), requestedClass);
                List<T> resultList = new ArrayList<>();
                for(T item : resultArray){
                    resultList.add(item);
                }
                listener.onResponse(resultList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorListener.onErrorResponse(error);
            }
        });
        getRequestQueue().add(jsonArrayRequest);
    }*/

    public  void getCocktailList(String apiUrl,
                               final Response.Listener<List<Cocktail>> listener, final Response.ErrorListener errorListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, apiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray cocktails = response.getJSONArray("drinks");
                    List<Cocktail> cocktailList = new ArrayList<>();
                    for(int i= 0; i< cocktails.length();i++){
                        JSONObject cocktailObject = cocktails.getJSONObject(i);

                        Map<String, String> ingredients = new HashMap<>();
                        for(int j= 1; j <= 15 ; j++){
                            String ingredientName  = "strIngredient" + j;
                            if(cocktailObject.has(ingredientName)){
                                ingredients.put(ingredientName,cocktailObject.getString(ingredientName));
                            }
                        }

                        Map<String, String> measures = new HashMap<>();
                        for(int j= 0; j < 16 ; j++){
                            String measureName  = "strMeasure" + j;
                            if(cocktailObject.has(measureName)){
                                measures.put(measureName,cocktailObject.getString(measureName));
                            }
                        }
                       Cocktail cocktail = new Cocktail();
                        cocktail.setIdDrink(cocktailObject.getString("idDrink"));
                        cocktail.setStrDrink(cocktailObject.getString("strDrink"));
                        cocktail.setStrDrinkAlternate(cocktailObject.getString("strDrinkAlternate"));
                        cocktail.setStrTags(cocktailObject.getString("strTags"));
                        cocktail.setStrVideo(cocktailObject.getString("strVideo"));
                        cocktail.setStrCategory(cocktailObject.getString("strCategory"));
                        cocktail.setStrIBA(cocktailObject.getString("strIBA"));
                        cocktail.setStrAlcoholic(cocktailObject.getString("strAlcoholic"));
                        cocktail.setStrGlass(cocktailObject.getString("strGlass"));
                        cocktail.setStrDrinkThumb(cocktailObject.getString("strDrinkThumb"));
                        cocktail.setStrImageSource(cocktailObject.getString("strImageSource"));
                        cocktail.setStrImageAttribution(cocktailObject.getString("strImageAttribution"));
                        cocktail.setStrCreativeCommonsConfirmed(cocktailObject.getString("strCreativeCommonsConfirmed"));
                        cocktail.setDateModified(cocktailObject.getString("dateModified"));
                        cocktail.setIngredients(ingredients);
                        cocktail.setMeasures(measures);

                        cocktailList.add(cocktail);
                    }
                    listener.onResponse(cocktailList);
                } catch (Exception e) {
                    errorListener.onErrorResponse(new VolleyError());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onErrorResponse(error);
                    }
                }

        );
        getRequestQueue().add(jsonObjectRequest);
    }


    public void getCocktailObject(String apiUrl,
                            final Response.Listener<Cocktail> listener, final Response.ErrorListener errorListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, apiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray cocktails = response.getJSONArray("drinks");
                    List<Cocktail> cocktailList = new ArrayList<>();
                    for(int i= 0; i< cocktails.length();i++){
                        JSONObject cocktailObject = cocktails.getJSONObject(i);

                        Map<String, String> ingredients = new HashMap<>();
                        for(int j= 1; j <= 15 ; j++){
                            String ingredientName  = "strIngredient" + j;
                            if(cocktailObject.has(ingredientName)){
                                ingredients.put(ingredientName,cocktailObject.getString(ingredientName));
                            }
                        }

                        Map<String, String> measures = new HashMap<>();
                        for(int j= 0; j < 16 ; j++){
                            String measureName  = "strMeasure" + j;
                            if(cocktailObject.has(measureName)){
                                measures.put(measureName,cocktailObject.getString(measureName));
                            }
                        }
                        Cocktail cocktail = new Cocktail();
                        cocktail.setIdDrink(cocktailObject.getString("idDrink"));
                        cocktail.setStrDrink(cocktailObject.getString("strDrink"));
                        cocktail.setStrDrinkAlternate(cocktailObject.getString("strDrinkAlternate"));
                        cocktail.setStrTags(cocktailObject.getString("strTags"));
                        cocktail.setStrVideo(cocktailObject.getString("strVideo"));
                        cocktail.setStrCategory(cocktailObject.getString("strCategory"));
                        cocktail.setStrIBA(cocktailObject.getString("strIBA"));
                        cocktail.setStrAlcoholic(cocktailObject.getString("strAlcoholic"));
                        cocktail.setStrGlass(cocktailObject.getString("strGlass"));
                        cocktail.setStrDrinkThumb(cocktailObject.getString("strDrinkThumb"));
                        cocktail.setStrImageSource(cocktailObject.getString("strImageSource"));
                        cocktail.setStrImageAttribution(cocktailObject.getString("strImageAttribution"));
                        cocktail.setStrCreativeCommonsConfirmed(cocktailObject.getString("strCreativeCommonsConfirmed"));
                        cocktail.setDateModified(cocktailObject.getString("dateModified"));
                        cocktail.setIngredients(ingredients);
                        cocktail.setMeasures(measures);

                        cocktailList.add(cocktail);
                    }
                    listener.onResponse(cocktailList.get(0));
                } catch (Exception e) {
                    errorListener.onErrorResponse(new VolleyError());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onErrorResponse(error);
                    }
                }
        );
        getRequestQueue().add(jsonObjectRequest);
    }


    public CompletableFuture<IngredientEventArgs> getIngredientAsync(String ingredientName, IngredientListener<IngredientEventArgs> listener){
        CompletableFuture<IngredientEventArgs> future =  new CompletableFuture<>();
        String fullApiUrl = Constants.BASE_URL + Constants.REST_SEARCH_BY_INGREDIENT + ingredientName;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, fullApiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray ingredients = response.getJSONArray("ingredients");
                    List<Ingredient> ingredientList = new ArrayList<>();
                    for(int i= 0; i< ingredients.length();i++){
                        JSONObject ingredientsObject = ingredients.getJSONObject(i);

                        Ingredient cocktail = new Ingredient();
                        cocktail.setIdIngredient(ingredientsObject.getString("idIngredient"));
                        cocktail.setStrIngredient(ingredientsObject.getString("strIngredient"));
                        cocktail.setStrDescription(ingredientsObject.getString("strDescription"));
                        cocktail.setStrType(ingredientsObject.getString("strType"));
                        cocktail.setStrAlcohol(ingredientsObject.getString("strAlcohol"));
                        cocktail.setStrABV(ingredientsObject.getString("strABV"));

                        ingredientList.add(cocktail);
                    }
                    IngredientEventArgs eventArgs = new IngredientEventArgs(ingredientList);
                    future.complete(eventArgs);
                    //future.complete(new IngredientEventArgs(ingredientList));
                    //listener.onIngredientSearchCompleted(eventArgs);
                } catch (Exception e) {
                    //errorListener.onErrorResponse(new VolleyError());
                    future.completeExceptionally(e);
                   // listener.onIngredientSearchError(e.getMessage());
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //errorListener.onErrorResponse(error);
                        future.completeExceptionally(error);
                        listener.onIngredientSearchError(error.getMessage());
                    }
                }
        );
        getRequestQueue().add(jsonObjectRequest);
        return future;
    }
}
