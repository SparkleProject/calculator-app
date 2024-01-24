package com.ourpretended.calculator;

import com.ourpretended.calculator.config.OperationContext;
import com.ourpretended.calculator.factory.OperationFactory;
import com.ourpretended.calculator.model.Expression;
import com.ourpretended.calculator.operation.IOperation;
import com.ourpretended.calculator.service.ICommandResolver;
import com.ourpretended.calculator.service.SimpleCommandResolver;
import com.ourpretended.calculator.validator.SimpleCommandValidator;


public class CalculatorHandler {

    private ICommandResolver commandResolver;
    private OperationFactory operationFactory;

    public CalculatorHandler(
            ICommandResolver commandResolver,
            OperationFactory operationFactory
    ){
        this.commandResolver = commandResolver;
        this.operationFactory = operationFactory;
    }


    public String calculate(String input){
        commandResolver.validateInput(input);
        Expression expression = commandResolver.mapToExpression(input);
        IOperation operation =  operationFactory.buildOperation(expression);
        Double result = operation.execute(expression.getOperands());
        return String.valueOf(result);
    }

}
