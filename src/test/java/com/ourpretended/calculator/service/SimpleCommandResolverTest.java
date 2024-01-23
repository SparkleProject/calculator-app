package com.ourpretended.calculator.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomDoubles.somePositiveDouble;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;

import com.ourpretended.calculator.config.OperationConfig;
import com.ourpretended.calculator.exception.IllegalOperationException;
import com.ourpretended.calculator.exception.IllegalOperandException;
import com.ourpretended.calculator.model.Expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleCommandResolverTest {

    private static final String LEGAL_INPUT_FORMAT = "%.9f %s %.9f";
    private static final String ILLEGAL_INPUT_FORMAT = "%s %s %s";
    private SimpleCommandResolver commandResolver;


    @BeforeEach
    void setUp() {
        commandResolver = new SimpleCommandResolver();
    }

    @Test
    void Can_map_command_to_expression(){
        // Given
        final String operation = someEnum(OperationConfig.class).getOperation();
        final Double firstNum = somePositiveDouble();
        final Double secondNum = somePositiveDouble();
        final String inputCommand = String.format(LEGAL_INPUT_FORMAT, firstNum, operation, secondNum);


        // When
        final Expression actual = commandResolver.mapToExpression(inputCommand);

        // Then
        assertThat(actual.getOperation(), equalTo(operation));
        assertThat(actual.getOperands().size(), equalTo(2));
        assertThat(actual.getOperands().get(0), equalTo(firstNum));

    }

    @Test
    void Can_throw_exception_when_contain_illegal_operation(){
        // Given
        final String operation = someAlphaString(1, 10);
        final Double firstNum = somePositiveDouble();
        final Double secondNum = somePositiveDouble();
        final String inputCommand = String.format(LEGAL_INPUT_FORMAT, firstNum, operation, secondNum);

        // Then
        assertThrows(IllegalOperationException.class, () -> commandResolver.mapToExpression(inputCommand));

    }

    @Test
    void Can_throw_exception_when_contain_multiple_operation(){
        // Given
        final String operation = someEnum(OperationConfig.class).getOperation()+ someEnum(OperationConfig.class).getOperation();
        final Double firstNum = somePositiveDouble();
        final Double secondNum = somePositiveDouble();
        final String inputCommand = String.format(LEGAL_INPUT_FORMAT, firstNum, operation, secondNum);

        // Then
        assertThrows(IllegalOperationException.class, () -> commandResolver.mapToExpression(inputCommand));

    }

    @Test
    void Can_throw_exception_when_expected_operands_not_found(){
        // Given
        final String operation = someEnum(OperationConfig.class).getOperation();
        final String firstNum = someAlphaString();
        final String secondNum = someAlphaString();
        final String inputCommand = String.format(ILLEGAL_INPUT_FORMAT, firstNum, operation, secondNum);

        // Then
        assertThrows(IllegalOperandException.class, () -> commandResolver.mapToExpression(inputCommand));

    }

    @Test
    void Can_throw_exception_when_numbers_of_operands_not_expected(){
        // Given
        final OperationConfig operationConfig = someEnum(OperationConfig.class);
        final String operation = operationConfig.getOperation();
        final String firstNum = String.format("%.9f", somePositiveDouble());
        final String otherNum = operationConfig.getRequiredOperandNum() > 1 ? "" : String.format("%.9f", somePositiveDouble());

        final String inputCommand = String.format(ILLEGAL_INPUT_FORMAT, firstNum, operation, otherNum);

        // Then
        assertThrows(IllegalOperandException.class, () -> commandResolver.mapToExpression(inputCommand));
    }
}