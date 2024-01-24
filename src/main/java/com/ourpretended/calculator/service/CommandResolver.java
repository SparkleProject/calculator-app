package com.ourpretended.calculator.service;

import com.ourpretended.calculator.model.Expression;


public interface CommandResolver {
    Expression mapToExpression(String input);
    void validateInput(String input);
}
