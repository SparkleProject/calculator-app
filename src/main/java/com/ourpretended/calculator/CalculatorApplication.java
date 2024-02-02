package com.ourpretended.calculator;

import com.ourpretended.calculator.config.OperationContext;
import com.ourpretended.calculator.factory.OperationFactory;
import com.ourpretended.calculator.service.SimpleCommandResolver;
import com.ourpretended.calculator.service.SimpleOperandsExtractor;
import com.ourpretended.calculator.service.SimpleOperationExtractor;
import com.ourpretended.calculator.validator.SimpleCommandValidator;



public class CalculatorApplication {

    // Invoke example
    public static void main(String[] args) {

        // Application Initialize
        OperationContext context = new OperationContext();

        SimpleCommandValidator commandValidator = new SimpleCommandValidator();
        SimpleOperandsExtractor operandsExtractor = new SimpleOperandsExtractor();
        SimpleOperationExtractor operationExtractor = new SimpleOperationExtractor();
        SimpleCommandResolver resolver = new SimpleCommandResolver( operationExtractor, operandsExtractor);
        OperationFactory factory = new OperationFactory(context);
        CalculatorHandler calculator = new CalculatorHandler(resolver, factory);

        // invoke
        String input = "4.6 / 5.8";
        commandValidator.validate(input);
        String result = calculator.calculate(input);
        System.out.println(result);

    }
}
