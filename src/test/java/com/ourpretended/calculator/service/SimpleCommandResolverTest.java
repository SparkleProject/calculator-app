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

import java.util.List;
import java.util.stream.Stream;


class SimpleCommandResolverTest {

    private static final String LEGAL_INPUT_FORMAT = "%.9f %s %.9f";
    private static final String ILLEGAL_INPUT_FORMAT = "%s %s %s";
    private static final String[] EXPECTED_OPERATIONS = {"+", "-", "*", "/"};
    private SimpleCommandResolver commandResolver;
    private SimpleOperationExtractor operationExtractor;
    private SimpleOperandsExtractor operandsExtractor;


    @BeforeEach
    void setUp() {
        operationExtractor = mock(SimpleOperationExtractor.class);
        operandsExtractor = mock(SimpleOperandsExtractor.class);
        commandResolver = new SimpleCommandResolver(operationExtractor, operandsExtractor);
    }

    @ParameterizedTest
    @MethodSource(value = "provideRandomOperation")
    void Can_map_command_to_expression(String operation){
        final Double firstNumber = somePositiveDouble();
        final Double secondNumber = somePositiveDouble();
        final String command = String.format(LEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);
        final OperationConfig operationConfig = mock(OperationConfig.class);

        // Given
        given(operationExtractor.getOperation(command)).willReturn(operation);
        given(operandsExtractor.getOperands(command)).willReturn(List.of(firstNumber, secondNumber));
        given(operationConfig.getRequiredNumbers()).willReturn(2);

        // When
        final Expression actual = commandResolver.mapToExpression(command);

        // Then
        assertThat(actual.getOperation(), equalTo(operation));
        assertThat(actual.getOperands().size(), equalTo(2));
        assertThat(actual.getOperands().get(0), equalTo(firstNumber));
        assertThat(actual.getOperands().get(1), equalTo(secondNumber));

    }


    private static Stream<Arguments> provideRandomOperation() {
        final int index = someIntegerBetween(0, 3);
        return Stream.of(
                Arguments.of(EXPECTED_OPERATIONS[index])
        );
    }
}