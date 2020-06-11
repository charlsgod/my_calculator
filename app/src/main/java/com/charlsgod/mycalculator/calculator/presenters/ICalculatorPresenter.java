package com.charlsgod.mycalculator.calculator.presenters;

import java.math.BigDecimal;

public interface ICalculatorPresenter {

    void changeSymbol(String s);

    void showResult(BigDecimal bd);

    void showError();

    void add(String s);

    void subtract(String s);

    void multiply(String s);

    void divide(String s);

    void calculatePercentage(String s);

    void equal(String s);

    void clean();

    void onDestroy();

    void saveTheme(String key, String value);

    String getTheme(String key);
}
