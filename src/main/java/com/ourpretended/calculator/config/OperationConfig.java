package com.ourpretended.calculator.config;

import com.ourpretended.calculator.operation.IOperation;

public class OperationConfig {

    private  String operationName;
    private  int requiredNumbers;
    private IOperation operationClass;

    protected OperationConfig(
            String operationName,
            int requiredNumbers,
            IOperation operationClass

    ){
        this.operationName = operationName;
        this.requiredNumbers = requiredNumbers;
        this.operationClass = operationClass;
    }

    public String getOperationName() {
        return operationName;
    }

    public int getRequiredNumbers() {
        return requiredNumbers;
    }

    public IOperation getOperationClass() {
        return operationClass;
    }
}
