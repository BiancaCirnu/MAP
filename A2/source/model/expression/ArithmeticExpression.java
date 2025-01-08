package source.model.expression;

import source.model.adt.IMyDictionary;
import source.model.adt.IMyHeap;
import source.model.modelExceptions.InvalidOperatorException;
import source.exception.MyException;
import source.model.modelExceptions.ValueHasWrongTypeException;
import source.model.type.IType;
import source.model.type.IntType;
import source.model.value.IValue;
import source.model.value.IntValue;

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

    @Override
    public IType typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        IType type1, type2;
        type1 = first.typeCheck(typeEnvironment);
        type2 = second.typeCheck(typeEnvironment);
        if(!type1.equals(new IntType()))
            throw new ValueHasWrongTypeException("First expression doesn't return an integer");
        if(!type2.equals(new IntType()))
            throw new ValueHasWrongTypeException("Second expression doesn't return an integer");
        return new IntType();
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
