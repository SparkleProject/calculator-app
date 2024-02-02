package com.ourpretended.calculator.operation;

import com.ourpretended.calculator.config.ApplicationConstants;
import com.ourpretended.calculator.exception.IllegalOperandException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DivisionOperation implements IOperation {
    private static final int RESULT_SCALE_QUOTIENT = 18;

    @Override
    public Double execute(double dividend, double divisor) {

        if(Double.valueOf(0.0).equals(divisor)){
            throw new IllegalOperandException("Can not divide by ZERO.");
        }

        return BigDecimal.valueOf(dividend)
                .divide(BigDecimal.valueOf(divisor),
                        RESULT_SCALE_QUOTIENT,
                        RoundingMode.HALF_UP)
                .doubleValue();
    }


}
