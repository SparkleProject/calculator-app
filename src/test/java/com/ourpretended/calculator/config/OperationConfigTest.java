package com.ourpretended.calculator.config;

import org.junit.jupiter.api.Test;
import shiver.me.timbers.data.random.RandomEnums;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class OperationConfigTest {

    @Test
    void can_get_operation_config() {
        final OperationConfig config = RandomEnums.someEnum(OperationConfig.class);

        // When
        final OperationConfig actual = OperationConfig.fromOperationString(config.getOperation());

        // Then
        assertThat(actual, is(config));
    }
}