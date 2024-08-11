package com.example.myhttpsdk.core;

import android.content.Context;

import java.lang.ref.WeakReference;

public class VSdkManager {

    private static WeakReference<VSdkManager> instance;
    private WeakReference<Context> applicationContext;

    //private constructor
     private VSdkManager(){

     }

    public static VSdkManager getInstance(){
        if(instance == null){
            instance = new WeakReference<>(new VSdkManager());
        }
        return instance.get();
    }


    public void startSdk(Context context){
         // Take the application content, it is important for the volley
        applicationContext = new WeakReference<>(context);
         //To-do...
    }
    public Context getApplicationContent(){
          return applicationContext.get();
    }
}
