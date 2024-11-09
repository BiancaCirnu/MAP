package model.expression;

import exception.MyException;
import model.adt.IMyDictionary;
import model.modelExceptions.ValueHasWrongTypeException;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IValue;
import model.value.IntValue;

public class RelationalExpression implements IExpression {
    IExpression left;
    IExpression right;
    RelationalOperator operator;
    public RelationalExpression(IExpression left, RelationalOperator operator, IExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable) throws MyException {
        if(left.evaluate(symbolTable).getType().equals(new IntType()))
            throw new ValueHasWrongTypeException("Operand has to be of type int");
        if(right.evaluate(symbolTable).getType().equals(new IntType()))
            throw new ValueHasWrongTypeException("Operand has to be of type int");
        switch (operator) {
            case LESS:
                return new BoolValue(((IntValue)left.evaluate(symbolTable)).getValue() < ((IntValue)right.evaluate(symbolTable)).getValue());
            case LESS_OR_EQUAL:
                return new BoolValue(((IntValue)left.evaluate(symbolTable)).getValue() <= ((IntValue)right.evaluate(symbolTable)).getValue());
            case EQUAL:
                return new BoolValue(((IntValue)left.evaluate(symbolTable)).getValue() == ((IntValue)right.evaluate(symbolTable)).getValue());
            case GREATER_OR_EQUAL:
                return new BoolValue(((IntValue)left.evaluate(symbolTable)).getValue() >= ((IntValue)right.evaluate(symbolTable)).getValue());
            case GREATER:
                return new BoolValue(((IntValue)left.evaluate(symbolTable)).getValue() > ((IntValue)right.evaluate(symbolTable)).getValue());
            case NOT_EQUAL:
                return new BoolValue(((IntValue)left.evaluate(symbolTable)).getValue() != ((IntValue)right.evaluate(symbolTable)).getValue());
            default:
                throw new MyException("Operator not recognized");
        }
    }


}
