package com.charlsgod.mycalculator.calculator.interactors;

import java.math.BigDecimal;

public interface ICalculatorInteractor {

    void add(BigDecimal bd);

    void subtract(BigDecimal bd);

    void multiply(BigDecimal bd);

    void divide(BigDecimal bd);

    void calculatePercentage(BigDecimal bd);

    void equal(BigDecimal bd);

    void clean();

    void saveTheme(String key, String value);

    String getTheme(String key);
}