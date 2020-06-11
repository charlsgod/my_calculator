package com.charlsgod.mycalculator.calculator.presenters;

import com.charlsgod.mycalculator.calculator.interactors.CalculatorInteractor;
import com.charlsgod.mycalculator.calculator.interactors.ICalculatorInteractor;
import com.charlsgod.mycalculator.calculator.views.ICalculatorView;

import java.math.BigDecimal;

import androidx.annotation.NonNull;

public class CalculatorPresenter implements ICalculatorPresenter {

    private static final String ZERO = "0";
    private static final Character MINUS = '-', PERIOD = '.', COMMA = ',';

    private ICalculatorView mView;
    private ICalculatorInteractor mInteractor;

    public CalculatorPresenter(ICalculatorView view) {
        this.mView = view;
        this.mInteractor = new CalculatorInteractor(this);
    }

    public void changeSymbol(String string) {
        if (!string.isEmpty() && !string.equals(ZERO)) {
            if (string.charAt(0) == MINUS) {
                string = string.substring(1);
            } else {
                string = MINUS + string;
            }
            mView.showResult(string);
        }
    }

    @Override
    public void showResult(BigDecimal bigDecimal) {
        bigDecimal = bigDecimal.stripTrailingZeros();
        String string = bigDecimal.toPlainString();
        string = format(string);
        mView.showResult(string);
    }

    @Override
    public void showError() {
        mView.showError();
    }

    @Override
    public void add(String string) {
        string = format(string);
        mInteractor.add(new BigDecimal(string));
    }

    @Override
    public void subtract(String string) {
        string = format(string);
        mInteractor.subtract(new BigDecimal(string));
    }

    @Override
    public void multiply(String string) {
        string = format(string);
        mInteractor.multiply(new BigDecimal(string));
    }

    @Override
    public void divide(String string) {
        if (string.equals(ZERO)) {
            showError();
        } else {
            string = format(string);
            mInteractor.divide(new BigDecimal(string));
        }
    }

    @Override
    public void calculatePercentage(String string) {
        string = format(string);
        mInteractor.calculatePercentage(new BigDecimal(string));
    }

    @Override
    public void equal(String s) {
        s = format(s);
        BigDecimal value = new BigDecimal(s);
        mInteractor.equal(value);
    }

    private String format(@NonNull String string) {
        if (string.contains(COMMA.toString())) {
            return string.replace(COMMA, PERIOD);
        } else if (string.contains(PERIOD.toString())) {
            return string.replace(PERIOD, COMMA);
        }
        return string;
    }

    @Override
    public void clean() {
        mInteractor.clean();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void saveTheme(String key, String value) {
        mInteractor.saveTheme(key, value);
    }

    @Override
    public String getTheme(String key) {
        return mInteractor.getTheme(key);
    }
}
