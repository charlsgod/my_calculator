package com.charlsgod.mycalculator.plataform.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.charlsgod.mycalculator.MyApplication;
import com.charlsgod.mycalculator.plataform.repositories.localStore.ILocalStoreDataManager;
import com.charlsgod.mycalculator.plataform.repositories.localStore.LocalStoreDataManager;

public class DataManager {

    private static DataManager instance;

    private static ILocalStoreDataManager localStore;

    private DataManager(MyApplication app) {
        SharedPreferences preferences = app.getSharedPreferences(app.getApplicationInfo().name, Context.MODE_PRIVATE);
        localStore = new LocalStoreDataManager(preferences);
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager(MyApplication.getInstance());
        }
        return instance;
    }

    public ILocalStoreDataManager getLocalStoreDataManager() {
        return localStore;
    }
}
