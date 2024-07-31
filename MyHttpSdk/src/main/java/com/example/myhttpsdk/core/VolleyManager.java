package com.example.myhttpsdk.core;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class VolleyManager {

    private static WeakReference<VolleyManager> instance;

    private VolleyManager(){

    }

    public static VolleyManager getInstance(){
        if(instance == null){
            instance = new WeakReference<>(new VolleyManager());
        }
        return instance.get();
    }

    public void getObject(Context context){
        // Logger
        String url =  "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=a";
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: " + response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
