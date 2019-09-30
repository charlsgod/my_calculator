package com.charlsgod.mycalculator.calculator.repositories;

public interface ICalculatorDataManager {

    String getData(String key);

    void setData(String key, String value);
}
