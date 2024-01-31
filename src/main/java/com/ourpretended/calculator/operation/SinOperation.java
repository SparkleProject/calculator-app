package com.ourpretended.calculator.operation;

import java.math.BigDecimal;
import java.util.List;

public class SinOperation implements IOperation {

    @Override
    public Double execute(List<Double> operands) {
        double firstNumber = operands.get(0);
        return Math.sin(firstNumber);
    }

}
