package com.charlsgod.mycalculator.calculator.entities;

public enum Operation {

    EMPTY(""),
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    PERCENTAGE("%");

    private final String symbol;

    Operation(final String s){
        symbol = s;
    }

    public String getSymbol() {
        return symbol;
    }
}
