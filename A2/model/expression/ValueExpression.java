package model.expression;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.adt.MyDictionary;
import model.type.IType;
import model.value.IValue;
import model.value.IntValue;

public class ValueExpression implements IExpression {
    private final IValue value;
    public ValueExpression(IValue value) {
        this.value = value;
    }
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable, IMyHeap heap) {
        return value;
    }

    @Override
    public IType typeCheck(IMyDictionary<String, IType> typeTable) throws MyException {
        return value.getType();
    }

    public IType getType(MyDictionary<String, IType> symbolTable) {
        return value.getType();
    }
    public String toString() {
        return value.toString();
    }
}
