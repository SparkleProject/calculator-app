package com.ourpretended.calculator.factory;

import com.ourpretended.calculator.config.OperationConfig;
import com.ourpretended.calculator.model.Expression;
import com.ourpretended.calculator.operation.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.BDDMockito.given;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;


class OperationFactoryTest {

    private OperationFactory operationFactory;

    @BeforeEach
    void setUp() {
        operationFactory = new OperationFactory();
    }

    @Test
    void Can_build_operation_with_expected_operation_type(){
        final OperationConfig operationConfig = someEnum(OperationConfig.class);
        final String operationStr = operationConfig.getOperation();
        final Class operationClass = operationConfig.getOperationClass();
        final Expression expression = mock(Expression.class);

        // Given
        given(expression.getOperation()).willReturn(operationStr);

        // When
        final Operation actual = operationFactory.buildOperation(expression);

        // Then
        assertThat(actual, notNullValue());
        assertThat(operationClass.isInstance(actual), is(true));
    }

    @Test
    void Can_fail_build_operation_with_unknown_operation(){
        final String operationStr = someAlphaString();
        final Expression expression = mock(Expression.class);

        // Given
        given(expression.getOperation()).willReturn(operationStr);

        // When
        final Operation actual = operationFactory.buildOperation(expression);

        // Then
        assertThat(actual, nullValue());
    }
}