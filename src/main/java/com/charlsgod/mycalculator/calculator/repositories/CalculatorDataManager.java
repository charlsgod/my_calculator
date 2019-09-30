package com.charlsgod.mycalculator.calculator.repositories;


import com.charlsgod.mycalculator.plataform.repositories.DataManager;
import com.charlsgod.mycalculator.plataform.repositories.localStore.ILocalStoreDataManager;

public class CalculatorDataManager implements ICalculatorDataManager {

    private final DataManager dataManager;

    public CalculatorDataManager() {
        dataManager = DataManager.getInstance();
    }

    @Override
    public String getData(String key) {
        ILocalStoreDataManager localStoreDataManager = dataManager.getLocalStoreDataManager();
        return localStoreDataManager.getString(key, "");
    }

    @Override
    public void setData(String key, String value) {
        ILocalStoreDataManager localStoreDataManager = dataManager.getLocalStoreDataManager();
        localStoreDataManager.setString(key, value);
    }
}
