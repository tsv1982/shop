package com.tsv.shop2.dagger;

import android.app.Application;

public class DaggerApp extends Application {


    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
    }

    public static AppComponent getComponent() {
        return component;
    }



}