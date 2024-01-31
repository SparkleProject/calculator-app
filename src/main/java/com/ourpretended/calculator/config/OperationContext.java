package com.ourpretended.calculator.config;

import com.ourpretended.calculator.operation.AdditionOperation;
import com.ourpretended.calculator.operation.DivisionOperation;
import com.ourpretended.calculator.operation.MultiplicationOperation;
import com.ourpretended.calculator.operation.SinOperation;
import com.ourpretended.calculator.operation.SubtractionOperation;

import java.util.HashMap;
import java.util.Map;

public class OperationContext {

    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";
    private static final String SIN = "sin";

    private static final Map<String, OperationConfig> operationMap = new HashMap<>();


    static {
      operationMap.put(ADDITION,
              new OperationConfig(ADDITION, 2, new AdditionOperation()));
      operationMap.put(SUBTRACTION,
              new OperationConfig(SUBTRACTION, 2, new SubtractionOperation()));
      operationMap.put(MULTIPLICATION,
              new OperationConfig(MULTIPLICATION, 2, new MultiplicationOperation()));
      operationMap.put(DIVISION,
              new OperationConfig(DIVISION, 2, new DivisionOperation()));
      operationMap.put(SIN,
                new OperationConfig(SIN, 1, new SinOperation()));
    }

    public OperationConfig fromOperationString(
            String operationName
    ){
        return operationMap.get(operationName);
    }

    public Map<String, OperationConfig> getOperationMap(){
        return operationMap;
    }

}
