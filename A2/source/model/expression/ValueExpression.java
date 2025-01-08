package source.model.expression;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.adt.IMyHeap;
import source.model.adt.MyDictionary;
import source.model.type.IType;
import source.model.value.IValue;

public class ValueExpression implements IExpression {
    private final IValue value;
    public ValueExpression(IValue value) {
        this.value = value;
    }
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable, IMyHeap heap) {
        return value;
    }

    @Override
    public IType typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        return value.getType();
    }


    public IType getType(MyDictionary<String, IType> symbolTable) {
        return value.getType();
    }
    public String toString() {
        return value.toString();
    }
}
