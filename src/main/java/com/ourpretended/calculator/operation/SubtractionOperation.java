package com.ourpretended.calculator.operation;

import java.math.BigDecimal;

public class SubtractionOperation implements IOperation {

    @Override
    public Double execute(double firstNumber, double secondNumber) {
        return BigDecimal.valueOf(firstNumber)
                .subtract(BigDecimal.valueOf(secondNumber))
                .doubleValue();
    }

}
