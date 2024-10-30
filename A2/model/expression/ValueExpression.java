package model.expression;

import model.adt.IMyDictionary;
import model.adt.MyDictionary;
import model.type.IType;
import model.value.IValue;
import model.value.IntValue;

public class ValueExpression implements IExpression {
    private IValue value;
    public ValueExpression(IValue value) {
        this.value = value;
    }
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable) {
        return value;
    }
    public IType getType(MyDictionary<String, IType> symbolTable) {
        return value.getType();
    }
    public String toString() {
        return value.toString();
    }
}
