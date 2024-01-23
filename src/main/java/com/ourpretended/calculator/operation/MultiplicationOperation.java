package com.ourpretended.calculator.operation;

import java.math.BigDecimal;
import java.util.List;

public class MultiplicationOperation implements Operation{

    @Override
    public Double execute(List<Double> operands) {
        final double firstNum = operands.get(0);
        final double secondNum = operands.get(1);
        return BigDecimal.valueOf(firstNum)
                .multiply(BigDecimal.valueOf(secondNum))
                .doubleValue();
    }

}
