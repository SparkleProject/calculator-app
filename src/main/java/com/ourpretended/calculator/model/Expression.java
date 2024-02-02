package com.ourpretended.calculator.model;


import java.util.List;

public class Expression {
    private String operation;
    private List<Double> operands;

    public Expression(
            String operation,
            List<Double> operands
    ){
        this.operation = operation;
        this.operands = operands;
    }

    public String getOperation() {
        return operation;
    }

    public List<Double> getOperands() {
        return operands;
    }

}
