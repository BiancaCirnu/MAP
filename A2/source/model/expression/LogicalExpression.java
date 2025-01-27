package source.model.expression;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.adt.IMyHeap;
import source.model.modelExceptions.InvalidOperatorException;
import source.model.modelExceptions.ValueHasWrongTypeException;
import source.model.type.BoolType;
import source.model.type.IType;
import source.model.value.BoolValue;
import source.model.value.IValue;

public class LogicalExpression implements IExpression {
    private IExpression left;
    private IExpression right;
    private LogicalOperator operator;
    public LogicalExpression(IExpression left, LogicalOperator operator, IExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable,IMyHeap heap) throws MyException {
        IValue leftValue = left.evaluate(symbolTable, heap);
        IValue rightValue = right.evaluate(symbolTable, heap);
        if(!leftValue.getType().equals(new BoolType()))
            throw new ValueHasWrongTypeException("left value is not a bool");
        if (!rightValue.getType().equals(new BoolType()))
            throw new ValueHasWrongTypeException("right is not a bool");
        boolean valLeft = ((BoolValue)leftValue).getValue();
        boolean valRight = ((BoolValue)rightValue).getValue();
        switch (operator) {
            case AND:
                return new BoolValue(valLeft && valRight);
            case OR:
                return new BoolValue(valLeft || valRight);
            default:
                throw new InvalidOperatorException();
        }
    }

    @Override
    public IType typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        if(!left.typeCheck(typeEnvironment).equals(new BoolType()))
            throw new ValueHasWrongTypeException("The left expression doesn't return a bool");
        if(!right.typeCheck(typeEnvironment).equals(new BoolType()))
            throw new ValueHasWrongTypeException("The right expression doesn't return a bool");
        return new BoolType();
    }

    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }



}
