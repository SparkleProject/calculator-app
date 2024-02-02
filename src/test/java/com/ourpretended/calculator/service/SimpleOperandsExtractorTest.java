package com.ourpretended.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static shiver.me.timbers.data.random.RandomStrings.someAlphaString;
import static shiver.me.timbers.data.random.RandomDoubles.somePositiveDouble;

class SimpleOperandsExtractorTest {

    private static final String LEGAL_INPUT_FORMAT = "%.9f %s %.9f";
    private SimpleOperandsExtractor operandsExtractor;

    @BeforeEach
    void setUp() {
        operandsExtractor = new SimpleOperandsExtractor();
    }

    @Test
    void Can_extract_operands(){
        final String operation = someAlphaString(1);
        final Double firstNumber = somePositiveDouble();
        final Double secondNumber = somePositiveDouble();
        final String input = String.format(LEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);

        // When
        final List<Double> operands = operandsExtractor.getOperands(input);

        // Then
        assertThat(operands, notNullValue());
        assertThat(operands.size(), is(2));
        assertThat(operands.get(0), equalTo(firstNumber));
        assertThat(operands.get(1), equalTo(secondNumber));

    }
}