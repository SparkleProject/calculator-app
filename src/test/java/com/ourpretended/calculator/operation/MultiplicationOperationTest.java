package com.ourpretended.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomDoubles.someDouble;

class MultiplicationOperationTest {

    private MultiplicationOperation multiplicationOperation;

    @BeforeEach
    void setUp() {
        multiplicationOperation = new MultiplicationOperation();
    }

    @Test
    void Can_subtract_two_operands(){
        final List<Double> operands = mock(List.class);
        final Double first = someDouble();
        final Double second = someDouble();
        final Double expected = BigDecimal.valueOf(first).multiply(BigDecimal.valueOf(second)).doubleValue();

        // Given
        given(operands.get(0)).willReturn(first);
        given(operands.get(1)).willReturn(second);

        // When
        final Double actual = multiplicationOperation.execute(operands);

        // Then
        assertThat(actual, equalTo(expected));
    }
}