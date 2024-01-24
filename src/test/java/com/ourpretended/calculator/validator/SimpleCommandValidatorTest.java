package com.ourpretended.calculator.validator;

import com.ourpretended.calculator.config.OperationConfig;
import com.ourpretended.calculator.exception.IllegalExpressionException;

import com.ourpretended.calculator.operation.SubtractionOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static shiver.me.timbers.data.random.RandomDoubles.somePositiveDouble;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomStrings.someAlphanumericString;


class SimpleCommandValidatorTest {

    private static final String LEGAL_INPUT_FORMAT = "%.9f %s %.9f";
    private static final String ILLEGAL_INPUT_FORMAT = "%s %s %s";

    private SimpleCommandValidator simpleCommandValidator;

    @BeforeEach
    void setUp() {
        simpleCommandValidator = new SimpleCommandValidator();
    }


    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "*", "/"})
    void Can_validate_legal_input_command(String operation){
        final double firstNum = somePositiveDouble();
        final double secondNum = somePositiveDouble();
        final String command = String.format(LEGAL_INPUT_FORMAT, firstNum, operation, secondNum);

        //Then
        assertDoesNotThrow(() -> simpleCommandValidator.validate(command));

    }

    @Test
    void Can_throw_exception_with_illegal_input_command(){
        final String operationConfig = someAlphanumericString();
        final String firstNum = someAlphanumericString();
        final String secondNum = someAlphanumericString();
        final String command = String.format(ILLEGAL_INPUT_FORMAT, firstNum, operationConfig, secondNum);

        //Then
        assertThrows(IllegalExpressionException.class, () -> simpleCommandValidator.validate(command));

    }
}