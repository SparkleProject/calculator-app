package com.ourpretended.calculator.service;

import static com.ourpretended.calculator.config.ApplicationConstants.OPERAND_REGEX;

import com.ourpretended.calculator.config.OperationContext;
import com.ourpretended.calculator.validator.ICommandValidator;
import com.ourpretended.calculator.exception.IllegalOperandException;
import com.ourpretended.calculator.model.Expression;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class SimpleCommandResolver implements ICommandResolver {

    private final IOperationExtractor operationExtractor;
    private final IOperandsExtractor operandsExtractor;

    public SimpleCommandResolver(
            IOperationExtractor operationExtractor,
            IOperandsExtractor operandsExtractor
    ){
        this.operationExtractor = operationExtractor;
        this.operandsExtractor = operandsExtractor;
    }

    @Override
    public Expression mapToExpression(String input){
        String operation = operationExtractor.getOperation(input);
        List<Double> operands = operandsExtractor.getOperands(input);
        return new Expression(operation, operands);
    }

}
