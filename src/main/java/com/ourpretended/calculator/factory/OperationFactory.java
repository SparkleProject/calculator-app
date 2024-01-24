package com.ourpretended.calculator.factory;


import com.ourpretended.calculator.config.OperationConfig;
import com.ourpretended.calculator.config.OperationContext;
import com.ourpretended.calculator.model.Expression;
import com.ourpretended.calculator.operation.IOperation;



public class OperationFactory {

    private final OperationContext context;

    public OperationFactory(
            OperationContext context
    ){
        this.context = context;
    }

    public IOperation buildOperation(
            Expression expression
    ) {
        try {
            OperationConfig operationConfig = context.fromOperationString(expression.getOperation());
            if(operationConfig != null){
                return operationConfig.getOperationClass();
            }
        }catch (Exception exception){
            System.out.println("Failed to build Operation with name: " + expression.getOperation());
        }
        return null;
    }

}
