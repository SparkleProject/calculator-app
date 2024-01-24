package com.ourpretended.calculator.validator;

import com.ourpretended.calculator.exception.IllegalOperandException;
import static com.ourpretended.calculator.config.ApplicationConstants.OPERAND_REGEX;
import static com.ourpretended.calculator.config.ApplicationConstants.OPERATION_REGEX;

public final class MultipleCommandValidator {

    private final static String COMMAND_MULTIPLE_OPERATION = OPERAND_REGEX+"(\\s+"+OPERATION_REGEX+"\\s+"+OPERAND_REGEX+")+";

    public static void validate(String input){
        if(!input.matches(COMMAND_MULTIPLE_OPERATION)){
            throw new IllegalOperandException("Illegal command inputs.");
        }
    }

}
