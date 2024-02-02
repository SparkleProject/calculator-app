package com.ourpretended.calculator.operation;

import java.math.BigDecimal;

public class MultiplicationOperation implements IOperation {

    @Override
    public Double execute(double firstNumber, double secondNumber) {
        return BigDecimal.valueOf(firstNumber)
                .multiply(BigDecimal.valueOf(secondNumber))
                .doubleValue();
    }

}
