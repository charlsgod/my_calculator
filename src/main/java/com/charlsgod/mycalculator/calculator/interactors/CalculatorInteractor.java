package com.charlsgod.mycalculator.calculator.interactors;

import com.charlsgod.mycalculator.calculator.entities.Operation;
import com.charlsgod.mycalculator.calculator.presenters.CalculatorPresenter;
import com.charlsgod.mycalculator.calculator.repositories.CalculatorDataManager;
import com.charlsgod.mycalculator.calculator.repositories.ICalculatorDataManager;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorInteractor implements ICalculatorInteractor {

    private BigDecimal mAccumulatedOp = null;

    private Operation mLastOperation = Operation.EMPTY;

    private CalculatorPresenter mPresenter;
    private ICalculatorDataManager mDataManager;

    public CalculatorInteractor(CalculatorPresenter presenter) {
        this.mPresenter = presenter;
        this.mDataManager = new CalculatorDataManager();
    }

    @Override
    public void add(BigDecimal operand) {
        mLastOperation = Operation.ADDITION;
        if (mAccumulatedOp == null)
            mAccumulatedOp = BigDecimal.ZERO;
        mAccumulatedOp = mAccumulatedOp.add(operand);
        mPresenter.showResult(mAccumulatedOp);
    }

    @Override
    public void subtract(BigDecimal operand) {
        mLastOperation = Operation.SUBTRACTION;
        if (mAccumulatedOp == null) {
            mAccumulatedOp = operand;
        } else {
            mAccumulatedOp = mAccumulatedOp.subtract(operand);
        }
        mPresenter.showResult(mAccumulatedOp);
    }

    @Override
    public void multiply(BigDecimal operand) {
        mLastOperation = Operation.MULTIPLICATION;
        if (mAccumulatedOp == null)
            mAccumulatedOp = BigDecimal.ONE;
        mAccumulatedOp = mAccumulatedOp.multiply(operand);
        mPresenter.showResult(mAccumulatedOp);
    }

    @Override
    public void divide(BigDecimal operand) {
        try {
            mLastOperation = Operation.DIVISION;
            if (mAccumulatedOp == null) {
                mAccumulatedOp = operand;
            } else {
                mAccumulatedOp = mAccumulatedOp.divide(operand, 5, RoundingMode.HALF_EVEN);
            }
            mPresenter.showResult(mAccumulatedOp);
        } catch (ArithmeticException aEx) {
            mPresenter.showError();
            mLastOperation = Operation.EMPTY;
            aEx.printStackTrace();
        }
    }

    @Override
    public void calculatePercentage(BigDecimal operand) {
        if (mAccumulatedOp == null) {
            mAccumulatedOp = BigDecimal.ONE;
        } 
        mAccumulatedOp = mAccumulatedOp.multiply(operand)
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN);
        mPresenter.showResult(mAccumulatedOp);
        mLastOperation = Operation.PERCENTAGE;
    }

    private void operate(BigDecimal operand) {
        switch (mLastOperation) {
            case ADDITION:
                add(operand);
                break;
            case SUBTRACTION:
                subtract(operand);
                break;
            case MULTIPLICATION:
                multiply(operand);
                break;
            case DIVISION:
                divide(operand);
                break;
            case PERCENTAGE:
                calculatePercentage(operand);
                break;
        }

    }

    @Override
    public void equal(BigDecimal operand) {
        operate(operand);
        if (mLastOperation != Operation.EMPTY)
            mPresenter.showResult(mAccumulatedOp.stripTrailingZeros());
        else
            mPresenter.showResult(operand.stripTrailingZeros());
        clean();
    }

    @Override
    public void clean() {
        mLastOperation = Operation.EMPTY;
        mAccumulatedOp = null;
    }

    @Override
    public void saveTheme(String key, String value) {
        mDataManager.setData(key, value);
    }

    @Override
    public String getTheme(String key) {
        return mDataManager.getData(key);
    }
}
