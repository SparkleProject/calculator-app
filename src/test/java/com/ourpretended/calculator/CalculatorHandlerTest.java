package com.ourpretended.calculator;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static shiver.me.timbers.data.random.RandomStrings.someAlphanumericString;
import static shiver.me.timbers.data.random.RandomDoubles.someDouble;

import com.ourpretended.calculator.factory.OperationFactory;
import com.ourpretended.calculator.model.Expression;
import com.ourpretended.calculator.operation.IOperation;
import com.ourpretended.calculator.service.SimpleCommandResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CalculatorHandlerTest {

    private CalculatorHandler calculator;
    private SimpleCommandResolver commandResolver;
    private OperationFactory operationFactory;

    @BeforeEach
    void setUp() {
        commandResolver = mock(SimpleCommandResolver.class);
        operationFactory = mock(OperationFactory.class);
        calculator = new CalculatorHandler(
                commandResolver,
                operationFactory
        );
    }

    @Test
    void Can_calculate_with_command_input(){
        final String command = someAlphanumericString();
        final String operationStr = someAlphanumericString();
        final Expression expression = mock(Expression.class);
        final Double expected = someDouble();
        final IOperation operation = mock(IOperation.class);
        final List<Double> operands = mock(List.class);

        // Given
        doNothing().when(commandResolver).validateInput(command);
        given(operationFactory.buildOperation(expression)).willReturn(operation);
        given(commandResolver.mapToExpression(command)).willReturn(expression);
        given(expression.getOperation()).willReturn(operationStr);
        given(expression.getOperands()).willReturn(operands);
        given(operation.execute(operands)).willReturn(expected);

        // When
        final String actual = calculator.calculate(command);

        // Then
        assertThat(actual, equalTo(String.valueOf(expected)));

    }


}