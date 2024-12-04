package model.expression;

import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.modelExceptions.InvalidOperatorException;
import exception.MyException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.type.BoolType;
import model.type.IType;
import model.value.IValue;
import model.value.IntValue;

public class ArithmeticExpression implements IExpression {
    private IExpression first, second;
    private ArithmeticOperator operator;
    public ArithmeticExpression(String op, IExpression first, IExpression second) {
        this.first = first;
        this.second = second;
        switch (op) {
            case "+":
                this.operator = ArithmeticOperator.ADD;
                break;
                case "-":
                    this.operator = ArithmeticOperator.SUB;
                    break;
                    case "*":
                        this.operator = ArithmeticOperator.MUL;
                        break;
                        case "/":
                            this.operator = ArithmeticOperator.DIV;
                            break;
                            default:
                                operator = ArithmeticOperator.OTHER;
        }
    }

    @Override
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable, IMyHeap heap) throws MyException {
        IValue firstVal = first.evaluate(symbolTable, heap);
        IValue secondVal = second.evaluate(symbolTable, heap);
        if(!(firstVal instanceof IntValue))
            throw new ValueHasWrongTypeException("first value is not an integer");
        if(!(secondVal instanceof IntValue))
            throw new ValueHasWrongTypeException("second value is not an integer");
        int firstValInt = ((IntValue)firstVal).getValue();
        int secondValInt = ((IntValue)secondVal).getValue();
        switch (operator) {
            case ADD:
                return new IntValue(firstValInt + secondValInt);
            case SUB:
                return new IntValue(firstValInt - secondValInt);
            case MUL:
                return new IntValue(firstValInt * secondValInt);
            case DIV:
                if (secondValInt == 0)
                    throw new ValueHasWrongTypeException("division by zero");
                else return new IntValue(firstValInt / secondValInt);
            default:
                throw new InvalidOperatorException();
        }
    }

    public String toString(){
        switch (operator){
            case ADD:
                return first.toString() + " + " + second.toString();
            case SUB:
                return first.toString() + " - " + second.toString();
            case MUL:
                return first.toString() + " * " + second.toString();
            case DIV:
                return first.toString() + " / " + second.toString();
        }
        return first.toString() + " " + operator.toString() + " " + second.toString();
    }


}
