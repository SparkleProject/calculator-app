package com.ourpretended.calculator.factory;

import com.ourpretended.calculator.config.OperationConfig;
import com.ourpretended.calculator.config.OperationContext;
import com.ourpretended.calculator.model.Expression;
import com.ourpretended.calculator.operation.AdditionOperation;
import com.ourpretended.calculator.operation.IOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;


class OperationFactoryTest {

    private OperationFactory operationFactory;
    private OperationContext context;

    @BeforeEach
    void setUp() {
        context = mock(OperationContext.class);
        operationFactory = new OperationFactory(context);
    }


    @Test
    void Can_build_operation_with_expected_operation_type(){
        final String operationString = someAlphaString();
        final OperationConfig operationConfig = mock(OperationConfig.class);
        final IOperation operation =  mock(IOperation.class);


        // Given
        given(context.fromOperationString(operationString)).willReturn(operationConfig);
        given(operationConfig.getOperationClass()).willReturn(operation);

        // When
        final IOperation actual = operationFactory.buildOperation(operationString);

        // Then
        assertThat(actual, notNullValue());
        assertThat(actual, is(operation));

    }

    @Test
    void Can_fail_build_operation_with_unknown_operation(){
        final String operationString = someAlphaString();

        // When
        final IOperation actual = operationFactory.buildOperation(operationString);

        // Then
        assertThat(actual, nullValue());
    }
}