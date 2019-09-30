package com.charlsgod.mycalculator.plataform.repositories.localStore;

public interface ILocalStoreDataManager {

    boolean contain(String key);

    String getString(String key, String defaultValue);
    boolean getBoolean(String key, boolean defaultValue);
    int getInt(String key, int defaultValue);
    long getLong(String key, long defaultValue);
    float getFloat(String key, float defaultValue);
    double getDouble(String key, double defaultValue);

    void setString(String key, String value);
    void setBoolean(String key, boolean value);
    void setInt(String key, int value);
    void setLong(String key, long value);
    void setFloat(String key, float value);
    void setDouble(String key, double value);

    default String getString(String key){
        return getString(key,null);
    }

    default boolean getBoolean(String key){
        return getBoolean(key,false);
    }

    default int getInt(String key){
        return getInt(key,0);
    }

    default long getLong(String key){
        return getLong(key,0L);
    }

    default float getFloat(String key){
        return getFloat(key,0.0f);
    }

    default double getDouble(String key){
        return getDouble(key,0.0);
    }
}
