package source.model.expression;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.adt.IMyHeap;
import source.model.modelExceptions.ValueHasWrongTypeException;
import source.model.type.BoolType;
import source.model.type.IType;
import source.model.type.IntType;
import source.model.value.BoolValue;
import source.model.value.IValue;
import source.model.value.IntValue;

public class RelationalExpression implements IExpression {
    IExpression left;
    IExpression right;
    RelationalOperator operator;
    public RelationalExpression(IExpression left, RelationalOperator operator, IExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable, IMyHeap heap) throws MyException {
        if(!left.evaluate(symbolTable, heap).getType().equals(new IntType()))
            throw new ValueHasWrongTypeException("Operand has to be of type int");
        if(!right.evaluate(symbolTable, heap).getType().equals(new IntType()))
            throw new ValueHasWrongTypeException("Operand has to be of type int");
        switch (operator) {
            case LESS:
                return new BoolValue(((IntValue)left.evaluate(symbolTable, heap)).getValue() < ((IntValue)right.evaluate(symbolTable, heap)).getValue());
            case LESS_OR_EQUAL:
                return new BoolValue(((IntValue)left.evaluate(symbolTable, heap)).getValue() <= ((IntValue)right.evaluate(symbolTable, heap)).getValue());
            case EQUAL:
                return new BoolValue(((IntValue)left.evaluate(symbolTable, heap)).getValue() == ((IntValue)right.evaluate(symbolTable, heap)).getValue());
            case GREATER_OR_EQUAL:
                return new BoolValue(((IntValue)left.evaluate(symbolTable, heap)).getValue() >= ((IntValue)right.evaluate(symbolTable, heap)).getValue());
            case GREATER:
                return new BoolValue(((IntValue)left.evaluate(symbolTable, heap)).getValue() > ((IntValue)right.evaluate(symbolTable, heap)).getValue());
            case NOT_EQUAL:
                return new BoolValue(((IntValue)left.evaluate(symbolTable, heap)).getValue() != ((IntValue)right.evaluate(symbolTable, heap)).getValue());
            default:
                throw new MyException("Operator not recognized");
        }
    }

    @Override
    public IType typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        if(!left.typeCheck(typeEnvironment).equals(new IntType()))
            throw new ValueHasWrongTypeException("Operand has to be of type int");
        if(!right.typeCheck(typeEnvironment).equals(new IntType()))
            throw new ValueHasWrongTypeException("Operand has to be of type int");
        return new BoolType();
    }


    public String toString(){
        String op;
        switch (operator)
        {
            case LESS:
                op = "<";
                break;
            case LESS_OR_EQUAL:
                op = "<=";
                break;
            case EQUAL:
                op = "==";
                break;
            case GREATER_OR_EQUAL:
                op = ">=";
                break;
            case GREATER:
                op = ">";
                break;
            default:
                op = "";
        }
        return left.toString()+ " "+op +" "+ right.toString();
    }
}
