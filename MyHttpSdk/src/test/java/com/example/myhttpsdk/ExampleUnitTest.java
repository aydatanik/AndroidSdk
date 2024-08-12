package com.example.myhttpsdk;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myhttpsdk.core.HttpHelper;

import java.util.List;

import domain.Cocktail;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Mock
    private Context context;

    private HttpHelper httpHelper;

    @Mock
    private RequestQueue mockQueue;

    @Mock
    private Response.Listener mockListenerCocktail;

    @Mock
    private Response.ErrorListener mockErrorListener;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        httpHelper = HttpHelper.getInstance(context);
        when(httpHelper.getRequestQueue()).thenReturn(mockQueue);
        //helper = HttpHelper.getInstance(context);
    }

   /* @Test
    public void  TestGetCocktailListSuccess(){
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita";
        JSONObject mockResponse = new JSONObject();
        ArgumentCaptor<JsonObjectRequest> requestCaptor = ArgumentCaptor.forClass(JsonObjectRequest.class);
        helper.getCocktailList(url,mockListenerCocktail,mockErrorListener);

        verify(mockQueue).add(requestCaptor.capture());
        JsonObjectRequest request = requestCaptor.getValue();

         request.getErrorListener()
    }*/

  /* @Test
    public void  TestGetCocktailListError(){
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita";
        VolleyError mockError = new VolleyError("Error");
        ArgumentCaptor<JsonObjectRequest> requestCaptor = ArgumentCaptor.forClass(JsonObjectRequest.class);
       HttpHelper.getInstance(context).getCocktailList(url,mockListenerCocktail,mockErrorListener);

        verify(mockQueue).add(requestCaptor.capture());
        JsonObjectRequest request = requestCaptor.getValue();

        request.getErrorListener().onErrorResponse(mockError);
        verify(mockErrorListener).onErrorResponse(mockError);
    }*/

}