package com.ourpretended.calculator;

import com.ourpretended.calculator.config.OperationContext;
import com.ourpretended.calculator.factory.OperationFactory;
import com.ourpretended.calculator.service.SimpleCommandResolver;
import com.ourpretended.calculator.validator.SimpleCommandValidator;

public class CalculatorApplication {

    // Invoke example
    public static void main(String[] args) {

        // Application Initialize
        OperationContext context = new OperationContext();
        SimpleCommandValidator commandValidator = new SimpleCommandValidator();
        SimpleCommandResolver resolver = new SimpleCommandResolver(context, commandValidator);
        OperationFactory factory = new OperationFactory(context);
        CalculatorHandler calculator = new CalculatorHandler(resolver, factory);

        // invoke
        //String input = "4.6 + 5.8";
        String input = "sin(4.5)";
        String result = calculator.calculate(input);
        System.out.println(result);

    }
}
