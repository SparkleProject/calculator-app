package com.ourpretended.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static shiver.me.timbers.data.random.RandomDoubles.somePositiveDouble;


class SimpleOperationExtractorTest {

    private SimpleOperationExtractor operationExtractor;

    private static final String LEGAL_INPUT_FORMAT = "%.9f %s %.9f";

    @BeforeEach
    void setUp() {
        operationExtractor = new SimpleOperationExtractor();
    }

    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "*", "/"})
    void Can_extract_operation(String operation){
        final Double firstNumber = somePositiveDouble();
        final Double secondNumber = somePositiveDouble();
        final String input = String.format(LEGAL_INPUT_FORMAT, firstNumber, operation, secondNumber);

        // When
        final String actual = operationExtractor.getOperation(input);

        // Then
        assertThat(actual, notNullValue());
        assertThat(actual, equalTo(operation));
    }
}