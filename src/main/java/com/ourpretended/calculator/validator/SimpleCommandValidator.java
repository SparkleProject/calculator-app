package com.ourpretended.calculator.validator;

import com.ourpretended.calculator.exception.IllegalExpressionException;

import static com.ourpretended.calculator.config.ApplicationConstants.OPERAND_REGEX;
import static com.ourpretended.calculator.config.ApplicationConstants.OPERATION_REGEX;

public final class SimpleCommandValidator {

    private final static String COMMAND_SINGLE_OPERATION = OPERAND_REGEX+"\\s+"+OPERATION_REGEX+"\\s+"+OPERAND_REGEX;

    public static void validate(String input){
        if(!input.matches(COMMAND_SINGLE_OPERATION)){
            throw new IllegalExpressionException("Illegal command inputs.");
        }
    }

}
