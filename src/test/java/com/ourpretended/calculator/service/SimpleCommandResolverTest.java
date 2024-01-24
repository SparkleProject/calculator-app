package com.ourpretended.calculator.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.in;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomDoubles.somePositiveDouble;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomIntegers.someIntegerBetween;

import com.ourpretended.calculator.config.OperationConfig;
import com.ourpretended.calculator.config.OperationContext;
import com.ourpretended.calculator.exception.IllegalExpressionException;
import com.ourpretended.calculator.exception.IllegalOperationException;
import com.ourpretended.calculator.exception.IllegalOperandException;
import com.ourpretended.calculator.model.Expression;

import com.ourpretended.calculator.validator.SimpleCommandValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;


class SimpleCommandResolverTest {

    private static final String LEGAL_INPUT_FORMAT = "%.9f %s %.9f";
    private static final String ILLEGAL_INPUT_FORMAT = "%s %s %s";
    private static final String[] EXPECTED_OPERATIONS = {"+", "-", "*", "/"};
    private SimpleCommandResolver commandResolver;
    private SimpleCommandValidator validator;
    private OperationContext context;


    @BeforeEach
    void setUp() {
        validator = mock(SimpleCommandValidator.class);
        context = mock(OperationContext.class);
        commandResolver = new SimpleCommandResolver(context, validator);
    }

    @ParameterizedTest
    @MethodSource(value = "provideRandomOperation")
    void Can_map_command_to_expression(String operation){
        final Double firstNumber = somePositiveDouble();
        final Double secondNumber = somePositiveDouble();
        final String command = String.format(LEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);
        final OperationConfig operationConfig = mock(OperationConfig.class);

        // Given
        given(context.fromOperationString(operation)).willReturn(operationConfig);
        given(operationConfig.getRequiredNumbers()).willReturn(2);

        // When
        final Expression actual = commandResolver.mapToExpression(command);

        // Then
        assertThat(actual.getOperation(), equalTo(operation));
        assertThat(actual.getOperands().size(), equalTo(2));
        assertThat(actual.getOperands().get(0), equalTo(firstNumber));
        assertThat(actual.getOperands().get(1), equalTo(secondNumber));

    }

    @Test
    void Can_throw_exception_when_contain_illegal_operation(){
        // Given
        final String operation = someAlphaString(1, 10);
        final Double firstNumber = somePositiveDouble();
        final Double secondNumber = somePositiveDouble();
        final String command = String.format(LEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);

        // Then
        assertThrows(IllegalOperationException.class, () -> commandResolver.mapToExpression(command));

    }


    @ParameterizedTest
    @MethodSource(value = "provideRandomOperation")
    void Can_throw_exception_when_contain_multiple_operation(String operationString){
        // Given
        final String operation = operationString + operationString;
        final Double firstNumber = somePositiveDouble();
        final Double secondNumber = somePositiveDouble();
        final String command = String.format(LEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);

        // Then
        assertThrows(IllegalOperationException.class, () -> commandResolver.mapToExpression(command));

    }

    @ParameterizedTest
    @MethodSource(value = "provideRandomOperation")
    void Can_throw_exception_when_expected_operands_not_found(String operation){

        final OperationConfig operationConfig = mock(OperationConfig.class);
        final String firstNumber = someAlphaString();
        final String secondNumber = someAlphaString();
        final String command = String.format(ILLEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);

        // Given
        given(context.fromOperationString(operation)).willReturn(operationConfig);
        given(operationConfig.getRequiredNumbers()).willReturn(2);

        // Then
        assertThrows(IllegalOperandException.class, () -> commandResolver.mapToExpression(command));

    }


    @ParameterizedTest
    @MethodSource(value = "provideRandomOperation")
    void Can_throw_exception_when_numbers_of_operands_not_expected(String operation){

        // Given
        final OperationConfig operationConfig = mock(OperationConfig.class);
        final String firstNumber = String.format("%.9f", somePositiveDouble());
        final String secondNumber = "";

        final String command = String.format(ILLEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);

        // Given
        given(context.fromOperationString(operation)).willReturn(operationConfig);
        given(context.fromOperationString(operation)).willReturn(operationConfig);
        given(operationConfig.getRequiredNumbers()).willReturn(2);

        // Then
        assertThrows(IllegalOperandException.class, () -> commandResolver.mapToExpression(command));
    }

    @ParameterizedTest
    @MethodSource(value = "provideRandomOperation")
    void Can_validate_legal_command(String operation){
        final Double firstNumber = somePositiveDouble();
        final Double secondNumber = somePositiveDouble();
        final String command = String.format(LEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);

        // Then
        assertDoesNotThrow(()->commandResolver.validateInput(command));

    }

    @Test
    void Can_validate_illegal_command(){
        final String operation = someAlphaString();
        final String firstNumber = someAlphaString();
        final String secondNumber = someAlphaString();
        final String command = String.format(ILLEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);

        // Given
        doThrow(IllegalExpressionException.class).when(validator).validate(command);

        // Then
        assertThrows(IllegalExpressionException.class, () -> commandResolver.validateInput(command));

    }

    private static Stream<Arguments> provideRandomOperation() {
        final int index = someIntegerBetween(0, 3);
        return Stream.of(
                Arguments.of(EXPECTED_OPERATIONS[index])
        );
    }
}