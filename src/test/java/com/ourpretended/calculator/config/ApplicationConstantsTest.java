package com.ourpretended.calculator.config;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationConstantsTest {
    @Test
    void Can_get_instance() {

        // When
        final ApplicationConstants actual = ApplicationConstants.INSTANCE;

        // Then
        assertThat(actual, notNullValue());
    }
}