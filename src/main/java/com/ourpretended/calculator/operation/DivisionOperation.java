package com.ourpretended.calculator.operation;

import com.ourpretended.calculator.config.ApplicationConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DivisionOperation implements Operation{

    @Override
    public Double execute(List<Double> operands) {
        final double firstNum = operands.get(0);
        final double secondNum = operands.get(1);
        return BigDecimal.valueOf(firstNum)
                .divide(BigDecimal.valueOf(secondNum),
                        ApplicationConstants.RESULT_SCALE_QUOTIENT,
                        RoundingMode.HALF_UP)
                .doubleValue();
    }

}
