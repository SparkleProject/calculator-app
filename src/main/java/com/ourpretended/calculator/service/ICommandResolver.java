package com.ourpretended.calculator.service;

import com.ourpretended.calculator.model.Expression;


public interface ICommandResolver {
    Expression mapToExpression(String input);
}
