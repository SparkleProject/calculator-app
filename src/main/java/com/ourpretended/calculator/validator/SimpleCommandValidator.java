package com.ourpretended.calculator.validator;

import com.ourpretended.calculator.exception.IllegalExpressionException;

import static com.ourpretended.calculator.config.ApplicationConstants.OPERAND_REGEX;
import static com.ourpretended.calculator.config.ApplicationConstants.OPERATION_REGEX;
import static com.ourpretended.calculator.config.ApplicationConstants.OPERATION_TRIGONOMETRIC_REGEX;

public class SimpleCommandValidator implements ICommandValidator {

    private final static String COMMAND_SINGLE_OPERATION = OPERAND_REGEX+"\\s+"+OPERATION_REGEX+"\\s+"+OPERAND_REGEX;

    private final static String COMMAND_TRIGONOMETRIC_OPERATION = OPERATION_TRIGONOMETRIC_REGEX+"\\(+"+OPERAND_REGEX+"\\)+";

    @Override
    public void validate(String input){
        if(!input.matches(COMMAND_SINGLE_OPERATION) && !input.matches(COMMAND_TRIGONOMETRIC_OPERATION)){
            throw new IllegalExpressionException("Illegal command.");
        }
    }

}
