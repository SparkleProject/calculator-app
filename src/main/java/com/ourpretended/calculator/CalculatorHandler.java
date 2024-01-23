package com.ourpretended.calculator;

import com.ourpretended.calculator.factory.OperationFactory;
import com.ourpretended.calculator.model.Expression;
import com.ourpretended.calculator.operation.Operation;
import com.ourpretended.calculator.service.CommandResolver;
import com.ourpretended.calculator.service.SimpleCommandResolver;


public class CalculatorHandler {

    private CommandResolver commandResolver;
    private OperationFactory operationFactory;

    public CalculatorHandler(
            CommandResolver commandResolver,
            OperationFactory operationFactory
    ){
        this.commandResolver = commandResolver;
        this.operationFactory = operationFactory;
    }


    public String calculate(String input){
        Expression expression = commandResolver.mapToExpression(input);
        Operation operation =  operationFactory.buildOperation(expression);
        Double result = operation.execute(expression.getOperands());
        return String.valueOf(result);
    }

    /*public static void main(String[] args) {
        String input = "4.664 / 3.3";

        CommandResolver resolver = new SimpleCommandResolver();
        OperationFactory factory = new OperationFactory();
        CalculatorHandler calculator = new CalculatorHandler(resolver, factory);
        String result = calculator.calculate(input);
        System.out.println(result);

    }*/
}
