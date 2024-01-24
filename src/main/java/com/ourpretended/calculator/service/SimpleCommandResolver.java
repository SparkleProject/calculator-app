package com.ourpretended.calculator.service;

import static com.ourpretended.calculator.config.ApplicationConstants.OPERAND_REGEX;
import static com.ourpretended.calculator.config.ApplicationConstants.OPERATION_REGEX;

import com.ourpretended.calculator.config.OperationContext;
import com.ourpretended.calculator.validator.ICommandValidator;
import com.ourpretended.calculator.config.OperationConfig;
import com.ourpretended.calculator.exception.IllegalOperandException;
import com.ourpretended.calculator.exception.IllegalOperationException;
import com.ourpretended.calculator.model.Expression;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class SimpleCommandResolver implements ICommandResolver {

    private final ICommandValidator validator;
    private final OperationContext context;

    public SimpleCommandResolver(
            OperationContext context,
            ICommandValidator validator
    ){
        this.context = context;
        this.validator = validator;
    }

    @Override
    public Expression mapToExpression(String input){

        String operation = getOperation(input);
        int requiredOperands = context.fromOperationString(operation).getRequiredNumbers();
        List<Double> operands = getOperands(input, requiredOperands);
        return new Expression(operation, operands);
    }

    @Override
    public void validateInput(String input) {
        validator.validate(input);
    }


    private List<Double> getOperands(
            String input,
            int requiredNumbers
    ){
        Pattern operandPattern = Pattern.compile(OPERAND_REGEX);
        List<Double> operands =
                operandPattern.matcher(input)
                        .results()
                        .map(MatchResult::group)
                        .map(Double::valueOf)
                        .collect(Collectors.toList());

        if(operands.isEmpty() || operands.size() != requiredNumbers){
            throw new IllegalOperandException("Operands not found or input is illegal.");
        }

        return operands;
    }

    private String getOperation(String input){
        Pattern operationPattern = Pattern.compile(OPERATION_REGEX);
        List<String> operations =
                operationPattern.matcher(input)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());

        if(operations.size() != 1){
            throw new IllegalOperationException("Operation not found or input is illegal.");
        }

        return operations.get(0);
    }

}
