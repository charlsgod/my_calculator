package com.charlsgod.mycalculator.calculator.views;

public interface ICalculatorView {

    void showResult(String string);

    void changeSymbol();

    void showError();

    void add();

    void subtract();

    void multiply();

    void addComma();

    void divide();

    void calculatePercentage();

    void equal();

    void clean();

    void saveTheme(String value);

    String getTheme(String key);
}
