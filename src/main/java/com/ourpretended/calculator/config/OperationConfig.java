package com.ourpretended.calculator.config;

import com.ourpretended.calculator.operation.AdditionOperation;
import com.ourpretended.calculator.operation.DivisionOperation;
import com.ourpretended.calculator.operation.MultiplicationOperation;
import com.ourpretended.calculator.operation.Operation;
import com.ourpretended.calculator.operation.SubtractionOperation;

import java.util.HashMap;
import java.util.Map;

public enum OperationConfig {
    ADD("+", 2, AdditionOperation.class),
    SUB("-", 2,SubtractionOperation.class),
    MUL("*", 2, MultiplicationOperation.class),
    DIV("/",2, DivisionOperation.class);

    private static final Map<String, OperationConfig> map = new HashMap<>();

    private final String operation;

    private final int requiredOperandNum;

    private final Class<? extends Operation> operationClass;

    OperationConfig(
           String operation,
           int requiredOperandNum,
           Class<? extends Operation> operationClass
    ){
        this.operation = operation;
        this.requiredOperandNum = requiredOperandNum;
        this.operationClass = operationClass;
    }

    static {
        for (OperationConfig config : OperationConfig.values()) {
            map.put(config.getOperation(), config);
        }
    }

    public static OperationConfig fromOperationString(
            String operationStr
    ){
        return map.get(operationStr);
    }
    public Class<? extends Operation> getOperationClass() {
        return operationClass;
    }

    public String getOperation() {
        return operation;
    }

    public int getRequiredOperandNum() {
        return requiredOperandNum;
    }
}
