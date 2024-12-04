package model.expression;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.modelExceptions.InvalidOperatorException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.type.BoolType;
import model.type.IType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;

import java.beans.Expression;

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
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }



}
