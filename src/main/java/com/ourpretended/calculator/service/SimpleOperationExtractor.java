package com.ourpretended.calculator.service;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.ourpretended.calculator.config.ApplicationConstants.OPERATION_REGEX;

public class SimpleOperationExtractor implements IOperationExtractor {

    @Override
    public String getOperation(String input) {
        Pattern operationPattern = Pattern.compile(OPERATION_REGEX);
        List<String> operations =
                operationPattern.matcher(input)
                        .results()
                        .map(MatchResult::group)
                        .collect(Collectors.toList());

        return operations.get(0);
    }
}
