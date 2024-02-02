package com.ourpretended.calculator.operation;

import com.ourpretended.calculator.config.ApplicationConstants;
import com.ourpretended.calculator.exception.IllegalOperandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomDoubles.someDouble;
import static shiver.me.timbers.data.random.RandomDoubles.somePositiveDouble;

class DivisionOperationTest {
    private DivisionOperation divisionOperation;

    @BeforeEach
    void setUp() {
        divisionOperation = new DivisionOperation();
    }

    @Test
    void Can_subtract_two_operands(){
        final List<Double> operands = mock(List.class);
        final double first = someDouble();
        final double second = somePositiveDouble();
        final double expected = first/second;

        // Given
        given(operands.get(0)).willReturn(first);
        given(operands.get(1)).willReturn(second);

        // When
        final Double actual = divisionOperation.execute(first, second);

        // Then
        assertThat(actual, equalTo(expected));
    }

    @Test
    void Can_throw_exception_when_divisor_is_zero(){
        final List<Double> operands = mock(List.class);
        final double first = someDouble();
        final double second = 0.0;

        // Given
        given(operands.get(0)).willReturn(first);
        given(operands.get(1)).willReturn(second);


        // Then
        assertThrows(IllegalOperandException.class, () -> divisionOperation.execute(first, second));
    }
}