package com.ourpretended.calculator.operation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static shiver.me.timbers.data.random.RandomDoubles.someDouble;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class AdditionOperationTest {

    private AdditionOperation additionOperation;

    @BeforeEach
    void setUp() {
        additionOperation = new AdditionOperation();
    }

    @Test
    void Can_add_two_operands(){
        final List<Double> operands = mock(List.class);
        final Double first = someDouble();
        final Double second = someDouble();
        final Double expected = BigDecimal.valueOf(first).add(BigDecimal.valueOf(second)).doubleValue();

        // Given
        given(operands.get(0)).willReturn(first);
        given(operands.get(1)).willReturn(second);

        // When
        final Double actual = additionOperation.execute(first, second);
        // Then
        assertThat(actual, equalTo(expected));
    }
}