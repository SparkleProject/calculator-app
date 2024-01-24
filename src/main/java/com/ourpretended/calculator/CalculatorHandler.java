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

    public static void main(String[] args) {

        // initialize
        OperationContext context = new OperationContext();
        SimpleCommandValidator commandValidator = new SimpleCommandValidator();
        SimpleCommandResolver resolver = new SimpleCommandResolver(context, commandValidator);
        OperationFactory factory = new OperationFactory(context);
        CalculatorHandler calculator = new CalculatorHandler(resolver, factory);

        // invoke
        String input = "4.664 + 3.1";
        String result = calculator.calculate(input);
        System.out.println(result);

    }
}
