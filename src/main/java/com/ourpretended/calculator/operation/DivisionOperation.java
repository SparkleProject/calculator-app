package com.ourpretended.calculator.operation;

import com.ourpretended.calculator.config.ApplicationConstants;
import com.ourpretended.calculator.exception.IllegalOperandException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DivisionOperation implements Operation{

    @Override
    public Double execute(List<Double> operands) {

        validate(operands);

        final double firstNum = operands.get(0);
        final double secondNum = operands.get(1);

        return BigDecimal.valueOf(firstNum)
                .divide(BigDecimal.valueOf(secondNum),
                        ApplicationConstants.RESULT_SCALE_QUOTIENT,
                        RoundingMode.HALF_UP)
                .doubleValue();
    }

    private void validate(List<Double> operands){
        if(operands.get(1).equals(0.0)){
            throw new IllegalOperandException("Can not divide by ZERO.");
        }
    }

}
