package com.ourpretended.calculator.factory;


import com.ourpretended.calculator.config.OperationConfig;
import com.ourpretended.calculator.model.Expression;
import com.ourpretended.calculator.operation.Operation;



public class OperationFactory {

    public Operation buildOperation(
            Expression expression
    ) {
        try {
            OperationConfig operationConfig = OperationConfig.fromOperationString(expression.getOperation());
            if(operationConfig != null){
                Class<?> operationCls = operationConfig.getOperationClass();
                return (Operation) operationCls.getDeclaredConstructor().newInstance();
            }
        }catch (Exception exception){
            System.out.println("Failed to build Operation with name: " + expression.getOperation());
        }
        return null;
    }

}
