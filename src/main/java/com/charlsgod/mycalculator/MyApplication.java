package com.charlsgod.mycalculator;

import android.app.Application;

public class MyApplication extends Application{

    private static MyApplication APP;
    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;
    }


    public static MyApplication getInstance() {
        return APP;
    }
}
