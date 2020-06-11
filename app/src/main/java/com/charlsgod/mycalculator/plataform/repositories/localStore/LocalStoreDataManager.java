package com.charlsgod.mycalculator.plataform.repositories.localStore;

import android.content.SharedPreferences;

public class LocalStoreDataManager implements ILocalStoreDataManager {

    private final SharedPreferences preferences;

    public LocalStoreDataManager(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean contain(String key) {
        return preferences.contains(key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        String value = getString(key, "0");
        return Double.parseDouble(value);
    }

    @Override
    public void setString(String key, String value) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    @Override
    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    @Override
    public void setInt(String key, int value) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    @Override
    public void setLong(String key, long value) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    @Override
    public void setFloat(String key, float value) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    @Override
    public void setDouble(String key, double value) {
        String string = Double.toString(value);
        setString(key, string);
    }
}
