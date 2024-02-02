package com.ourpretended.calculator.operation;

import java.math.BigDecimal;

public class AdditionOperation implements IOperation {

    @Override
    public Double execute(double fistNumber, double secondNumber) {
        return BigDecimal.valueOf(fistNumber)
                .add(BigDecimal.valueOf(secondNumber))
                .doubleValue();
    }

}
