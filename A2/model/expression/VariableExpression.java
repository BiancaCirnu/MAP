package model.expression;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.type.IType;
import model.value.IValue;

public class VariableExpression implements IExpression {
    private final String variable;
    public VariableExpression(String variable) {
        this.variable = variable;
    }
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable, IMyHeap heap) throws MyException {
        return symbolTable.getValue(variable);
    }

    @Override
    public IType typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        return typeEnvironment.getValue(variable);
    }

    public String toString() {
        return variable;
    }
}
