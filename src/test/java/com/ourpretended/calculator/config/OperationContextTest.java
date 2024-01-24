package com.ourpretended.calculator.config;

import com.ourpretended.calculator.operation.IOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

class OperationContextTest {

    private OperationContext context;

    @BeforeEach
    void setUp() {
        context = new OperationContext();
    }

    @Test
    void Can_initialize_context_with_operation_map(){
        Map<String, OperationConfig> operationMap = context.getOperationMap();

        // Then
        assertThat(operationMap, notNullValue());
        assertThat(operationMap.size(), is(4));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "*", "/"})
    void Can_get_operation_config_by_name(String operationString){

        // when
        OperationConfig actual = context.fromOperationString(operationString);

        // Then
        assertThat(actual, notNullValue());
        assertThat(actual.getOperationClass(), notNullValue());
    }
}