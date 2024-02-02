package com.ourpretended.calculator.service;

import com.ourpretended.calculator.exception.IllegalOperandException;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.ourpretended.calculator.config.ApplicationConstants.OPERAND_REGEX;

public class SimpleOperandsExtractor implements IOperandsExtractor {

    @Override
    public List<Double> getOperands(String input) {
        Pattern operandPattern = Pattern.compile(OPERAND_REGEX);
        List<Double> operands =
                operandPattern.matcher(input)
                        .results()
                        .map(MatchResult::group)
                        .map(Double::valueOf)
                        .collect(Collectors.toList());

        return operands;
    }
}
