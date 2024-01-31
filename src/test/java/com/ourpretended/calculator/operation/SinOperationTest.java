package com.ourpretended.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomDoubles.somePositiveDouble;

class SinOperationTest {

    private SinOperation sinOperation;
    @BeforeEach
    void setUp() {
        sinOperation = new SinOperation();
    }

    @Test
    void Can_execute_operation(){
        final double number = somePositiveDouble();
        final double expected = Math.sin(number);
        final List<Double> operands = mock(List.class);

        // Given
        given(operands.get(0)).willReturn(number);


        // When
        double actual = sinOperation.execute(operands);

        // Then
        assertThat(actual, equalTo(expected));
    }
}